/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.contatos.resource;

import com.algaworks.contatos.model.Estado;
import com.algaworks.contatos.repository.EstadoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Alex
 */
@RestController
@RequestMapping("/estados")
public class EstadoResouce {
    	@Autowired
	private EstadoRepository estadoRepository;
        @GetMapping
	public  List<Estado> listar(){
		return estadoRepository.findAll();
	}
    
}
