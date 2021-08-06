package com.vendas.gestaovendas.dto.Venda;

import java.time.LocalDate;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Venda requicição DTO")
public class VendaRequestDTO {
   
	@ApiModelProperty(value = "Data")
	private LocalDate date;
	@ApiModelProperty(value = "Itens da Venda")
	private List<ItemVendaRequestDTO> itensVendaDto;
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public List<ItemVendaRequestDTO> getItensVendaDto() {
		return itensVendaDto;
	}
	public void setItensVendaDto(List<ItemVendaRequestDTO> itensVendaDto) {
		this.itensVendaDto = itensVendaDto;
	}
}
