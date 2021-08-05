package com.vendas.gestaovendas.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.vendas.gestaovendas.dto.Venda.ItemVendaResponseDTO;
import com.vendas.gestaovendas.dto.Venda.VendaResponseDTO;
import com.vendas.gestaovendas.model.ItemVenda;
import com.vendas.gestaovendas.model.Venda;

@Service
public class AbstractVendaService {
	
     
	protected VendaResponseDTO criandoVendaResponseDTO(Venda venda, List<ItemVenda> itensVendaList) {
	      List<ItemVendaResponseDTO> itensVendaResponseDto = itensVendaList
	         .stream()
	         .map(this::criandoItensResponseDto)
	         .collect(Collectors.toList());
	   
	      return new VendaResponseDTO(venda.getCodigo(), venda.getDate(), itensVendaResponseDto);
		}
	
	protected ItemVendaResponseDTO criandoItensResponseDto(ItemVenda itemVenda) {
		return new ItemVendaResponseDTO(itemVenda.getCodigo(), itemVenda.getQuantidade(),
				itemVenda.getPrecoVendido(), itemVenda.getProduto().getCodigo(), itemVenda.getProduto().getDescricao());
	}
		

}
