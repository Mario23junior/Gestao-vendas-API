package com.vendas.gestaovendas.Controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vendas.gestaovendas.Service.ClienteService;
import com.vendas.gestaovendas.dto.Cliente.ClienteRequestDTO;
import com.vendas.gestaovendas.dto.Cliente.ClienteResponseDTO;
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
	public List<ClienteResponseDTO> listAll() {
		return clienteService.listAll()
				.stream()
				.map(cliente -> ClienteResponseDTO.converterPataClienteDTO(cliente))
				.collect(Collectors.toList());

	}

	@ApiOperation(value = "Listando dados por id", nickname = "buscarClientePorId")
	@GetMapping("/{codigo}")
	public ResponseEntity<ClienteResponseDTO> listById(@PathVariable Long codigo) {
		Optional<Cliente> cliente = clienteService.listById(codigo);
		return cliente.isPresent() 
				? ResponseEntity.ok(ClienteResponseDTO.converterPataClienteDTO(cliente.get())) 
				: ResponseEntity.notFound().build();
	}
	
	@ApiOperation(value = "Salvando clientes no banco de dados")
	@PostMapping
	public ResponseEntity<ClienteResponseDTO> salvar (@Valid @RequestBody ClienteRequestDTO clienteDto) {
		Cliente ClienteData = clienteService.salvar(clienteDto.converterParaEntidade());
		return ResponseEntity.status(HttpStatus.CREATED).body(ClienteResponseDTO.converterPataClienteDTO(ClienteData));
	}
	
	
}
