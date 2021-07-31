package com.vendas.gestaovendas.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vendas.gestaovendas.model.Cliente;
import com.vendas.gestaovendas.repository.ClienteRepository;

@Service
public class ClienteService {
   
	private ClienteRepository clienteRepository;
	
	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
 	}
	
	public List<Cliente> listAll() {
		return clienteRepository.findAll();
	}
	
	public Optional<Cliente> listById(Long codigo) {
		return clienteRepository.findById(codigo);
	}
}
