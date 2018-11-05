package com.algaworks.contatos.resource;

import java.text.DecimalFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.contatos.model.Cliente;
import com.algaworks.contatos.model.ItensVenda; 
import com.algaworks.contatos.model.Produto;
import com.algaworks.contatos.model.Usuario;
import com.algaworks.contatos.model.Venda;
import com.algaworks.contatos.repository.ClienteRepository;
import com.algaworks.contatos.repository.ProdutosRepository;
import com.algaworks.contatos.repository.UsuarioRepository;
import com.algaworks.contatos.repository.VendaRepository;
import com.argaworks.contatos.dtos.ClienteVendaDto;
import com.argaworks.contatos.dtos.ItensVendaDto;
import com.argaworks.contatos.dtos.VendaDto;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter.FixedSpaceIndenter;

import comalgaworks.enums.StatusVenda;

@RestController
@RequestMapping("/vendas")
public class VendasResource {
	private static final Logger log = LoggerFactory.getLogger(VendasResource.class);
	
	@Autowired
	private VendaRepository vendaRepository;
	@Autowired
	private ProdutosRepository produtosRepository;
	@Autowired
	private UsuarioRepository UsuarioRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	
	@PostMapping("/itens")
	public List<ItensVendaDto> cadastrar(@Valid @RequestBody List<ItensVendaDto> vendas) {
		
		vendaRepository.save(this.converterDtoParaVenda(vendas));
		
		return vendas;
		
	}
	@PostMapping
	public ResponseEntity<String> cadastrar(@Valid @RequestBody ClienteVendaDto vendas) {
		
		Venda venda = vendaRepository.save(this.converterDtoParaVenda(vendas));
		if(venda == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(venda.getId().toString());
		
	}
	@GetMapping
	private List<Venda> listar(){
		return vendaRepository.findAll();
	}
	@GetMapping("/itens/{id}")
	private List<ItensVendaDto> getItensVendas(@PathVariable Long id) {
		return convertVendaToListDto(id);
	}
	
	@GetMapping("/{id}")
	private List<VendaDto> getVendasByVendedor(@PathVariable Long id) {
		return convertVendaToDto(id);
	}
	@GetMapping("{periodo}/{id}")
	private List<VendaDto> getVendasByPeriodo(@PathVariable String periodo,@PathVariable int id) {
		System.out.println("LOG.: " + periodo + " - " + id);
		return convertVendaToDto(periodo, id);
	}
	@GetMapping("/status/{status}/{id}")
	private List<VendaDto> getVendasByStatus(@PathVariable StatusVenda status,@PathVariable Long id) {
		return convertVendaToDto(status, id);
	}
	@GetMapping("/confirmacao/{status}/{id}")
	private VendaDto confirmacao(@PathVariable String status, @PathVariable Long id) {
	    Venda venda  = vendaRepository.findOne(id);
	    log.info("testes   " + venda.toString());
//		if(venda == null) {
//			return ResponseEntity.notFound().build();
//		}
		switch (status) {
		case "CONCLUIDA":
			venda.setStatus(StatusVenda.CONCLUIDA);
			break;
		case "CANCELADA":
			venda.setStatus(StatusVenda.CANCELADA);
			// Volta quantidade p estoque
			for(ItensVenda i :venda.getItens()){
				Produto produto = produtosRepository.findById(i.getProduto().getId());
				int qtde = produto.getQuantidade() + i.getQuantidade();
				produto.setQuantidade(qtde);
				produtosRepository.save(produto);	
			}
			break;
		default:
			venda.setStatus(StatusVenda.PENDENTE);
			break;
		}
	    vendaRepository.save(venda);
	    
	    //	return ResponseEntity.notFound().build();
	    //}
	    log.info(venda.toString());
		return converterVendaToDTO(venda);
	}
	private Venda converterDtoParaVenda(List<ItensVendaDto> vendaDto) {
			
		Venda venda = new Venda();
	    Usuario vendedor = new Usuario();
	    Long i = vendaDto.get(0).getIdVendedor();
	    vendedor = UsuarioRepository.findOne(i);
		venda.setVendedor(vendedor);
		Set<ItensVenda> item = new HashSet<>();
		double valorTotal = 0.00;
		
		for (ItensVendaDto v : vendaDto) {
			ItensVenda itensVenda =  new ItensVenda();
			itensVenda.setVenda(venda);
			itensVenda.setQuantidade(v.getQuantidade());
			Produto produto = new Produto();
		    produto = produtosRepository.findOne(v.getIdProduto());
		    int qtde = produto.getQuantidade() - v.getQuantidade();
		    log.info("Quantidade do Produto" + qtde);
		    produto.setQuantidade(qtde);;
		    produtosRepository.save(produto);
			itensVenda.setProduto(produto);
			valorTotal +=  v.getValorProduto() * v.getQuantidade();			
			item.add(itensVenda);
		}
		venda.setValorTotal(valorTotal);
		venda.setItens(item);
		venda.setStatus(StatusVenda.PENDENTE);
		return venda;
	}
	private Venda converterDtoParaVenda(ClienteVendaDto pedido) {
		Venda venda = new Venda();
	    Usuario vendedor = new Usuario();
	   
	    Long i =    pedido.getIdVendedor();
	    vendedor = UsuarioRepository.findOne(i);
		venda.setVendedor(vendedor);
		Set<ItensVenda> item = new HashSet<>();
		double valorTotal = 0.00;
		
		for (ItensVendaDto v : pedido.getItensVenda()) {
			ItensVenda itensVenda =  new ItensVenda();
			itensVenda.setVenda(venda);
			itensVenda.setQuantidade(v.getQuantidade());
			Produto produto = new Produto();
		    produto = produtosRepository.findOne(v.getIdProduto());
		    int qtde = produto.getQuantidade() - v.getQuantidade();
		    log.info("Quantidade do Produto" + qtde);
		    produto.setQuantidade(qtde);;
		//    produtosRepository.save(produto);
			itensVenda.setProduto(produto);
			valorTotal +=  v.getValorProduto() * v.getQuantidade();			
			item.add(itensVenda);
		}
		venda.setValorTotal(valorTotal);
		venda.setItens(item);
		if (pedido.getCliente() != null) {
			log.info(pedido.getCliente().toString());
			Cliente cliente = clienteRepository.save(pedido.getCliente());		
			venda.setCliente(cliente);
		}
		venda.setStatus(StatusVenda.PENDENTE);
		return venda;
	}
	private List<ItensVendaDto> convertVendaToListDto(Long id){
	    Venda venda = vendaRepository.getOne(id);
		List<ItensVendaDto> vendasDto = new ArrayList<ItensVendaDto>();
		for (ItensVenda itens : venda.getItens()) {
			ItensVendaDto vendaDto = new ItensVendaDto();
			vendaDto.setId(itens.getId());
			vendaDto.setIdProduto(itens.getProduto().getId());
			vendaDto.setNomeProduto(itens.getProduto().getNome());
			vendaDto.setQuantidade(itens.getQuantidade());
			vendaDto.setValorProduto(itens.getProduto().getPreco());
			vendaDto.setIdVendedor(venda.getVendedor().getId());
			vendasDto.add(vendaDto);			
		}
		return vendasDto;
	}
	private List<VendaDto> convertVendaToDto(Long id){
		Usuario usuario = UsuarioRepository.findOne(id);
		List<Venda> vendas = vendaRepository.findByVendedor(usuario);
		List<VendaDto> vendasDto =  new ArrayList<VendaDto>();
		for (Venda venda : vendas) {
			VendaDto vendaDto = new VendaDto();
			vendaDto.setId(venda.getId());
			vendaDto.setValorTotal(venda.getvalorTotal());
			vendaDto.setVendedor(venda.getVendedor());
			vendaDto.setData(venda.getData());
			vendasDto.add(vendaDto);
		}
		return vendasDto;
	}
	private List<VendaDto> convertVendaToDto(String periodo, int id){
	    int mes  = Integer.parseInt(periodo.substring(0, 2));
		int ano  = Integer.parseInt(periodo.substring(2, 6));
		List<Venda> vendas = new ArrayList<Venda>();
		if(id == 0) {			
			 vendas = vendaRepository.findByPeriodo(mes, ano);
		}else {
			Long id_vendedor = Long.valueOf(id);
			vendas = vendaRepository.findByPeriodoVendedor(id_vendedor, mes, ano);		
		}
	

		List<VendaDto> vendasDto =  new ArrayList<VendaDto>();
		for (Venda venda : vendas) {
			VendaDto vendaDto = new VendaDto();
			vendaDto.setId(venda.getId());
			vendaDto.setValorTotal(venda.getvalorTotal());
			vendaDto.setVendedor(venda.getVendedor());
			vendaDto.setData(venda.getData());
			vendasDto.add(vendaDto);
		}
		return vendasDto;
	}
	private List<VendaDto> convertVendaToDto(StatusVenda status, Long id){
		Usuario usuario = UsuarioRepository.findOne(id);
		List<Venda> vendas = vendaRepository.findByStatus(status);
		List<VendaDto> vendasDto =  new ArrayList<VendaDto>();
		for (Venda venda : vendas) {
			VendaDto vendaDto = new VendaDto();
			vendaDto.setId(venda.getId());
			vendaDto.setValorTotal(venda.getvalorTotal());
			vendaDto.setVendedor(venda.getVendedor());
			vendaDto.setData(venda.getData());
			vendasDto.add(vendaDto);
		}
		return vendasDto;
	}
	private VendaDto converterVendaToDTO(Venda venda) {
		 VendaDto vendaDto = new VendaDto();
		 vendaDto.setId(venda.getId());
		 vendaDto.setData(venda.getData());
		 vendaDto.setValorTotal(venda.getvalorTotal());
		 vendaDto.setVendedor(venda.getVendedor());
		 return vendaDto;
	}

}
