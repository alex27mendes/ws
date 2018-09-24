package com.algaworks.contatos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.contatos.model.Usuario;
import com.algaworks.contatos.model.Venda;

public interface VendaRepository extends JpaRepository<Venda, Long> {
	
	List<Venda> findByVendedor(Usuario usuario);
}
