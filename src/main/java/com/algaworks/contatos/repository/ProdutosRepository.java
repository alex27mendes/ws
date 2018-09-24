package com.algaworks.contatos.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.contatos.model.Produto;

public interface ProdutosRepository extends JpaRepository<Produto, Long> {
	
	
	@Transactional(readOnly = true)
	Produto findById(Long Id);

}
