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

import com.algaworks.contatos.model.Login;
import com.algaworks.contatos.repository.LoginRespository;

@RestController
@RequestMapping("/login")
public class LoginResource {
	@Autowired
	private LoginRespository loginRepository;
	
	@GetMapping
	private List<Login> listar(){
		return loginRepository.findAll();
	}
	
	@PostMapping
	public Login adicionar(@Valid @RequestBody Login login) {
		return loginRepository.save(login);	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Login> buscar(@PathVariable Long id){
		Login login = loginRepository.findOne(id);
		
		if( login == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(login);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Login> atualizar (@PathVariable Long id,
			@Valid @RequestBody Login login){
		Login existente = loginRepository.findOne(id);
		
		if (existente == null ) {
			return ResponseEntity.notFound().build();
		}
		
		BeanUtils.copyProperties(login, existente, "id");
		existente = loginRepository.save(existente);
		return ResponseEntity.ok(existente);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id){
		Login login = loginRepository.findOne(id);
		
		if (login == null) {
			return ResponseEntity.notFound().build();
		}
		loginRepository.delete(login);
		return ResponseEntity.noContent().build();
	}
	
	

}
