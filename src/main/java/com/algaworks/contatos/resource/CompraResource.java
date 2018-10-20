package com.algaworks.contatos.resource;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.contatos.model.Compra;
import com.algaworks.contatos.model.Login;
import com.algaworks.contatos.model.Produto;
import com.algaworks.contatos.repository.CompraRepository;
import com.algaworks.contatos.repository.ProdutosRepository;

@RestController
@RequestMapping("/compras")
public class CompraResource {
	private static final Logger log = LoggerFactory.getLogger(CompraResource.class);
	
	@Autowired
	private CompraRepository compraRepository;
	@Autowired
	private ProdutosRepository produtoRepository;
	
	@PostMapping
	public Compra adicionar(@Valid @RequestBody Compra compra) {
		int quantidade = compra.getQuantidade() + compra.getProduto().getQuantidade();
		log.info("Quantidade para atualizar:  {}", quantidade);		
		compra.getProduto().setQuantidade(quantidade);	
		compraRepository.save(compra);
		produtoRepository.save(compra.getProduto());
		log.info("Produto atualizado");	
		return compra;
	}
}
