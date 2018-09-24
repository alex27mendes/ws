package com.algaworks.contatos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.contatos.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
