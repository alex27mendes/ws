package com.argaworks.contatos.dtos;

import java.util.List;

import com.algaworks.contatos.model.Produto;

public class ProdutoDto {
	private double valorTotalVenda;
	private double valorTotalCusto;
	private List<Produto> produtos;
	public double getValorTotalVenda() {
		return valorTotalVenda;
	}
	public void setValorTotalVenda(double valorTotalVenda) {
		this.valorTotalVenda = valorTotalVenda;
	}
	public double getValorTotalCusto() {
		return valorTotalCusto;
	}
	public void setValorTotalCusto(double valorTotalCusto) {
		this.valorTotalCusto = valorTotalCusto;
	}
	public List<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	

}
