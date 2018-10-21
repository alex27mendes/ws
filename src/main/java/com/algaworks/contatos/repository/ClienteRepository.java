package com.algaworks.contatos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.contatos.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	

}
