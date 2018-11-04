package com.argaworks.contatos.dtos;

import java.util.List;

import com.algaworks.contatos.model.Cliente;

public class ClienteVendaDto {	
	private Cliente cliente;
	private List<ItensVendaDto> itensVenda;
	private Long idVendedor;
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public List<ItensVendaDto> getItensVenda() {
		return itensVenda;
	}
	public void setItensVenda(List<ItensVendaDto> itensVenda) {
		this.itensVenda = itensVenda;
	}
	public Long getIdVendedor() {
		return idVendedor;
	}
	public void setIdVendedor(Long idVendedor) {
		this.idVendedor = idVendedor;
	}
	
}
