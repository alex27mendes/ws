package com.algaworks.contatos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.contatos.model.Produto;

public interface ProdutosRepository extends JpaRepository<Produto, Long> {

}
