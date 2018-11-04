package com.algaworks.contatos.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.contatos.model.Categoria;
import com.algaworks.contatos.model.Produto;
import com.algaworks.contatos.repository.CategoriaRepository;
import com.algaworks.contatos.repository.ProdutosRepository;
import com.argaworks.contatos.dtos.ProdutoDto;

@RestController
@RequestMapping("/produtos")
public class ProdutosResource {
	
	@Autowired
	private ProdutosRepository produtosRepository;
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@PostMapping
	public Produto adicionar(@Valid @RequestBody Produto produto) {
		if (produto.getCategoria().getNome()!= null) {
			Categoria categoria = categoriaRepository.findByNome(produto.getCategoria().getNome());
			produto.setCategoria(categoria);
		}
		return produtosRepository.save(produto);
	}
	
	@GetMapping
	public List<Produto> listar(){
		return produtosRepository.findAll();
	}
	@GetMapping("/estoque")
	public ProdutoDto listarEstoque(){
		return this.converterDtoParaProduto();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> buscar(@PathVariable Long id){
		Produto produto = produtosRepository.findOne(id);
		
		if(produto == null) {
			return ResponseEntity.notFound().build();
		}
		
		
		return ResponseEntity.ok(produto);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id){
		Produto produto = produtosRepository.findOne(id);
		
		if (produto  ==  null) {
			return ResponseEntity.notFound().build();
		}
		produtosRepository.delete(produto);
		return ResponseEntity.noContent().build();
	}
	@PutMapping("/{id}")
	public ResponseEntity<Produto> atualizar(@PathVariable Long id, @Valid @RequestBody Produto produto){
			
			Produto existente  = produtosRepository.findOne(id);
			
			if( existente == null) {
				return ResponseEntity.notFound().build();
			}
			
			BeanUtils.copyProperties(produto, existente, "id");
			
			existente = produtosRepository.save(existente);
			
			return ResponseEntity.ok(existente);	
	}
	private ProdutoDto converterDtoParaProduto() {
		ProdutoDto dtos = new ProdutoDto();
		List<Produto> produtos = produtosRepository.findByProdutosDisponiveis(0);
		
		double totalVenda  = 0;
		for(Produto produto : produtos) {
		     totalVenda +=  (produto.getQuantidade() * produto.getPreco());
		     
		}
		dtos.setProdutos(produtos);
		dtos.setValorTotalVenda(totalVenda);
		dtos.setValorTotalCusto(totalVenda * 0.40);
		return dtos;
	}
}
