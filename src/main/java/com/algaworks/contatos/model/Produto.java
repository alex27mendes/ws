package com.algaworks.contatos.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
@Entity
public class Produto extends Persistivel {
    private String nome;
    private double preco;
    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<Compra> compras;
    private String urlImage;
    private int Quantidade;
    private int estoqueMinimo;
    @Column(length=500)
    private String descricao;
    @OneToOne(cascade=CascadeType.ALL)
    private Categoria categoria;
    private Boolean ativo;
    
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

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public int getQuantidade() {
		return Quantidade;
	}

	public void setQuantidade(int quantidade) {
		Quantidade = quantidade;
	}

	public int getEstoqueMinimo() {
		return estoqueMinimo;
	}

	public void setEstoqueMinimo(int estoqueMinimo) {
		this.estoqueMinimo = estoqueMinimo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
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
