package com.algaworks.contatos.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class ItensVenda extends Persistivel{
	
	private Integer quantidade;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "produto_id")
	private Produto produto;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "venda_id")
	private Venda venda;
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public Venda getVenda() {
		return venda;
	}
	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	
	

}
