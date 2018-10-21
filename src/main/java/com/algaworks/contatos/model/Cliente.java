package com.algaworks.contatos.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name =  "cliente")

public class Cliente extends Persistivel {
	private String nome;
    private String telefone;
    private String endereco;
    private String bairro;
    private String cidade;
    private String complemento;
	
    public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	@Override
	public String toString() {
		return "Cliente [nome=" + nome + ", telefone=" + telefone + ", endereco=" + endereco + ", bairro=" + bairro
				+ ", cidade=" + cidade + ", complemento=" + complemento + "]";
	}
	
}
