package com.algaworks.contatos.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.contatos.model.Login;

public interface LoginRespository extends JpaRepository<Login, Long> {
	
	

}
