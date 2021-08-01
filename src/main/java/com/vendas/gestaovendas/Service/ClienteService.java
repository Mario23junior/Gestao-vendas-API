package com.vendas.gestaovendas.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vendas.gestaovendas.Exceptions.RegraNegocioDuplicateDataException;
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
	
	public Cliente salvar(Cliente cliente) {
		validarClienteDuplicado(cliente);
		return clienteRepository.save(cliente);
	}
	
	private void validarClienteDuplicado(Cliente cliente ) {
		Cliente findCliente = clienteRepository.findByNome(cliente.getNome());
		if(findCliente != null && findCliente.getCodigo() != cliente.getCodigo()) {
			throw new RegraNegocioDuplicateDataException(String.format("O cliente %s  já esta cadastrado", cliente.getNome().toUpperCase()));
		}
	}
}
