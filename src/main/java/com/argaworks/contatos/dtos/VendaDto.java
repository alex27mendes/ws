package com.argaworks.contatos.dtos;

import com.algaworks.contatos.model.Cliente;
import java.util.Date;
import com.algaworks.contatos.model.Usuario;

public class VendaDto {
    private Long id;
    private Date data;
    private double valorTotal;
    private Usuario vendedor;
    private Cliente cliente;
    public Long getId() {
            return id;
    }
    public void setId(Long id) {
            this.id = id;
    }
    public Date getData() {
            return data;
    }
    public void setData(Date data) {
            this.data = data;
    }
    public double getValorTotal() {
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
    public Cliente getCliente() {
       return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }  
}
