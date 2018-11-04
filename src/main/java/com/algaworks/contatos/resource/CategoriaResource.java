package com.algaworks.contatos.resource;

import java.util.List;

import javax.validation.Valid;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.contatos.model.Categoria;
import com.algaworks.contatos.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
//	private static final Logger log = LoggerFactory.getLogger(Categoria.class);
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping
	public  List<Categoria> listar(){
		return categoriaRepository.findAll();
	}
	@PostMapping
	public Categoria cadastrar(@Valid @RequestBody Categoria categoria) {		
		categoriaRepository.save(categoria);
		return categoria;
	}
	
	
	
	

}
