package com.vendas.gestaovendas.Controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vendas.gestaovendas.Service.vendaServico;
import com.vendas.gestaovendas.dto.Venda.ClienteVendaResponseDTO;
import com.vendas.gestaovendas.dto.Venda.VendaRequestDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Venda")
@RestController
@RequestMapping("/v1/api/venda")
public class VendaController {
    
	private vendaServico vendaServico;
	
	public VendaController(vendaServico vendaServico) {
		this.vendaServico = vendaServico;
 	}
	
	@ApiOperation(value = "Listar vendas por cliente ", nickname = "Listar venda por cliente")
	@GetMapping("/cliente/{codigoCliente}")
	public ResponseEntity<ClienteVendaResponseDTO> listarVendaPorCliente(@PathVariable Long codigoCliente) {
		return ResponseEntity.ok(vendaServico.listVendaproCliente(codigoCliente));
	}
	
	@ApiOperation(value = "Listar venda por codigo", nickname = "listando vendas por codigo")
	@GetMapping("/{codigoVenda}")
	public ResponseEntity<ClienteVendaResponseDTO> listarVendaPorCodigo(@PathVariable Long codigoVenda) {
		return ResponseEntity.ok(vendaServico.listaVendaPorCodigo(codigoVenda));
	}
	
	@ApiOperation(value = "Registrar venda na base", nickname = "Salvando uma venda")
	@PostMapping("/cliente/{codigoCliente}")
	public ResponseEntity<ClienteVendaResponseDTO> salvar(@PathVariable Long codigoCliente,@Valid @RequestBody VendaRequestDTO vendaDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(vendaServico.Salvar(codigoCliente, vendaDto));
	}
	
	@ApiOperation(value = "Deletando venda ", nickname = "Deletando venda")
	@DeleteMapping("/{codigoVenda}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete (@PathVariable Long codigoVenda) {
		vendaServico.deletar(codigoVenda);
	}
}
