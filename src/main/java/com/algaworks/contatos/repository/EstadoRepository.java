/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.contatos.repository;

import com.algaworks.contatos.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Alex
 */
public interface EstadoRepository  extends JpaRepository<Estado, Long> {
    
}
