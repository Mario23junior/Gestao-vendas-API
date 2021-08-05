package com.vendas.gestaovendas.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vendas.gestaovendas.Service.vendaServico;
import com.vendas.gestaovendas.dto.Venda.ClienteVendaResponseDTO;

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
}
