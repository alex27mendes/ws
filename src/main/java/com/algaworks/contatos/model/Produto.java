package com.algaworks.contatos.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
@Entity
public class Produto extends Persistivel {
    private String nome;
    private double preco;
    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<Compra> compras;
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

//	public List<Compra> getCompras() {
//		return compras;
//	}
//
//	public void setCompras(List<Compra> compras) {
//		this.compras = compras;
//	}
//	
	

}
