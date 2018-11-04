package com.algaworks.contatos.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name =  "categorias")
public class Categoria extends Persistivel{
	
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	

}
