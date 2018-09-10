package com.algaworks.contatos.model;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Venda extends Persistivel {
	
    private Date data;
    private double valorTotal;
	@OneToMany(mappedBy="venda", cascade=CascadeType.ALL)
	private Set<ItensVenda> itens = new HashSet<ItensVenda>();
    @OneToOne(cascade=CascadeType.ALL)
    private Usuario vendedor;
    
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public double getvalorTotal() {
		return valorTotal;
	}
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public Usuario getVendedor() {
		return vendedor;
	}
	public void setVendedor(Usuario vendedor) {
		this.vendedor = vendedor;
	}
	public Set<ItensVenda> getItens() {
		return itens;
	}
	public void setItens(Set<ItensVenda> itens) {
		this.itens = itens;
	}
    
}
