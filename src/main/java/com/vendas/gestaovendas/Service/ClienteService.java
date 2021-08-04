package com.vendas.gestaovendas.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
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
	
	
	public Cliente atualizar(Long Codigo, Cliente cliente) {
	   Cliente clienteAtualizar = validarCliente(Codigo);
	   validarClienteDuplicado(cliente);
	   BeanUtils.copyProperties(cliente, clienteAtualizar, "codigo");
	   return clienteRepository.save(clienteAtualizar);
	}
	
	private Cliente validarCliente(Long codigo) {
		Optional<Cliente> BuscaCliente = listById(codigo);
		if(BuscaCliente.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		return BuscaCliente.get();
	}
	
	public void deletar (Long codigo) {
		clienteRepository.deleteById(codigo);
	}
	
}

 

