package com.algaworks.contatos.repository;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.contatos.model.Produto;

public interface ProdutosRepository extends JpaRepository<Produto, Long> {

	@Query(value = " from Produto where Quantidade > :qtde")
	List<Produto> findByProdutosDisponiveis(@Param("qtde") int qtde);
	
	@Transactional(readOnly = true)
	Produto findById(Long Id);

}
