package com.algaworks.contatos.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.algaworks.contatos.model.Login;

public interface LoginRespository extends JpaRepository<Login, Long> {
	
	@Query(value = "  from Login  where login = :login and senha = :senha")
	Login findByLogin(@Param("login") String login , @Param("senha") String senha);	

}
