package com.vendas.gestaovendas.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vendas.gestaovendas.Service.ClienteService;
import com.vendas.gestaovendas.model.Cliente;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Cliente")
@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

	private ClienteService clienteService;

	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@ApiOperation(value = "Listar todos os dados", nickname = "buscarTodos")
	@GetMapping
	public List<Cliente> listAll() {
		return clienteService.listAll();

	}

	@ApiOperation(value = "Listando dados por id", nickname = "buscarPorId")
	@GetMapping("/{codigo}")
	public ResponseEntity<Cliente> listById(@PathVariable Long codigo) {
		Optional<Cliente> cliente = clienteService.listById(codigo);
		return cliente.isPresent() 
				? ResponseEntity.ok(cliente.get()) 
				: ResponseEntity.notFound().build();
	}
}
