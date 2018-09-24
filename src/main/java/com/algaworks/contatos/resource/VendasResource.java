package com.algaworks.contatos.resource;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.contatos.model.ItensVenda; 
import com.algaworks.contatos.model.Produto;
import com.algaworks.contatos.model.Usuario;
import com.algaworks.contatos.model.Venda;
import com.algaworks.contatos.repository.ProdutosRepository;
import com.algaworks.contatos.repository.UsuarioRepository;
import com.algaworks.contatos.repository.VendaRepository;
import com.argaworks.contatos.dtos.ItensVendaDto;
import com.argaworks.contatos.dtos.VendaDto;

@RestController
@RequestMapping("/vendas")
public class VendasResource {
	
	@Autowired
	private VendaRepository vendaRepository;
	@Autowired
	private ProdutosRepository produtosRepository;
	@Autowired
	private UsuarioRepository UsuarioRepository;
	
	@PostMapping
	public List<ItensVendaDto> cadastrar(@Valid @RequestBody List<ItensVendaDto> vendas) {
		
		vendaRepository.save(this.converterDtoParaVenda(vendas));
		
		return vendas;
		
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
			itensVenda.setProduto(produto);
			valorTotal +=  v.getValorProduto() * v.getQuantidade();			
			item.add(itensVenda);
		}
		venda.setValorTotal(valorTotal);
		venda.setItens(item);
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

}
