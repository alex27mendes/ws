package com.algaworks.contatos.model;



import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name =  "compra")
public class Compra extends Persistivel{
	
	private Date dataCriacao;
    private double precoCompra;
    private double precovenda;
    private int quantidade;
    @ManyToOne
    private Produto produto;
    
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public double getPrecoCompra() {
		return precoCompra;
	}
	public void setPrecoCompra(double precoCompra) {
		this.precoCompra = precoCompra;
	}
	public double getPrecovenda() {
		return precovenda;
	}
	public void setPrecovenda(double precovenda) {
		this.precovenda = precovenda;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	@PreUpdate
    public void preUpdate() {
        dataCriacao = new Date();
    }
     
    @PrePersist
    public void prePersist() {
        final Date atual = new Date();
        dataCriacao = atual;
    }
}
