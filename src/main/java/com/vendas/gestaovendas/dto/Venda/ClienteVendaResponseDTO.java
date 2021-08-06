package com.vendas.gestaovendas.dto.Venda;

import java.util.List; 

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Cliente da venda retorno DTO")
public class ClienteVendaResponseDTO {

	@ApiModelProperty(value = "Nome cliente")
	private String nome;

	@ApiModelProperty(value = "Venda")
	private List<VendaResponseDTO> vendasResponse;

	public ClienteVendaResponseDTO(String nome, List<VendaResponseDTO> vendasResponse) {
		this.nome = nome;
		this.vendasResponse = vendasResponse;
	}
 
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<VendaResponseDTO> getVendasResponse() {
		return vendasResponse;
	}

	public void setVendasResponse(List<VendaResponseDTO> vendasResponse) {
		this.vendasResponse = vendasResponse;
	}

}
