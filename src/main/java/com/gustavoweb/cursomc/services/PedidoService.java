package com.gustavoweb.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gustavoweb.cursomc.domain.Pedido;
import com.gustavoweb.cursomc.repositories.PedidoRepository;
import com.gustavoweb.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	public Pedido find(Integer id) {     
		Optional<Pedido> obj = repo.findById(id); 
		return obj.orElseThrow(() -> new ObjectNotFoundException( 
		"Objeto não encontrado! Id: " 
		+ id 
		+ ", Tipo: " 
		+ Pedido.class.getName())); } 
	
}

