package com.algaworks.contatos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import comalgaworks.enums.TipoUsuario;

@Entity
@Table(name = "usuario")
public class Usuario extends Persistivel{
	
    /**
	 * 
	 */
	private String nome;
	private TipoUsuario tipoUsuario;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
	@Enumerated(EnumType.STRING)
	@Column(name = "perfil", nullable = false)
	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	
}
