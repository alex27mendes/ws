package com.algaworks.contatos.model;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import comalgaworks.enums.StatusVenda;

@Entity
public class Venda extends Persistivel {
	
    private Date data;
    private double valorTotal;
	@OneToMany(mappedBy="venda", cascade=CascadeType.ALL)
	private Set<ItensVenda> itens = new HashSet<ItensVenda>();
    @OneToOne(cascade=CascadeType.ALL)
    private Usuario vendedor;
    @OneToOne
    private Cliente cliente;
    private StatusVenda status;
    
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
	@PreUpdate
    public void preUpdate() {
        data = new Date();
    }
     
    @PrePersist
    public void prePersist() {
        final Date atual = new Date();
        data = atual;
    }
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public StatusVenda getStatus() {
		return status;
	}
	public void setStatus(StatusVenda status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Venda [data=" + data + ", valorTotal=" + valorTotal + ", itens=" + itens + ", vendedor=" + vendedor
				+ ", cliente=" + cliente + ", status=" + status + "]";
	}
}
