package com.vendas.gestaovendas.dto.Venda;

import java.time.LocalDate;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

 
@ApiModel("Venda retorno DTO")
public class VendaResponseDTO {

	@ApiModelProperty(value = "Codigo")
	private Long codigo;
	
	@ApiModelProperty(value = "Data")
	private LocalDate data;
	
	@ApiModelProperty(value = "Itens da venda")
	private List<ItemVendaResponseDTO> itemVendaDTOs;
	
	public VendaResponseDTO(Long codigo, LocalDate data, List<ItemVendaResponseDTO> itemVendaDTOs) {
		super();
		this.codigo = codigo;
		this.data = data;
		this.itemVendaDTOs = itemVendaDTOs;
	}

	public LocalDate getData() {
		return data;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public List<ItemVendaResponseDTO> getItemVendaDTOs() {
		return itemVendaDTOs;
	}

	public void setItemVendaDTOs(List<ItemVendaResponseDTO> itemVendaDTOs) {
		this.itemVendaDTOs = itemVendaDTOs;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	
	
}
