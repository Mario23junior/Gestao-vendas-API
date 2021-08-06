package com.vendas.gestaovendas.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.vendas.gestaovendas.dto.Venda.ClienteVendaResponseDTO;
import com.vendas.gestaovendas.dto.Venda.ItemVendaRequestDTO;
import com.vendas.gestaovendas.dto.Venda.ItemVendaResponseDTO;
import com.vendas.gestaovendas.dto.Venda.VendaResponseDTO;
import com.vendas.gestaovendas.model.ItemVenda;
import com.vendas.gestaovendas.model.Produto;
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
	
	protected ClienteVendaResponseDTO refatorandoClienteVendaResponseDTO(Venda venda, List<ItemVenda> itensVendaList) {
		 return new ClienteVendaResponseDTO(venda.getCliente().getNome(), Arrays.asList(
			    criandoVendaResponseDTO(venda, itensVendaList)));
	}
	
	protected ItemVenda criandoItemVenda(ItemVendaRequestDTO itemVendaDto, Venda venda) {
		return new ItemVenda(itemVendaDto.getQuantidade(), itemVendaDto.getPrecoVendido(),
				new Produto(itemVendaDto.getCodigoProduto()), venda);
	}
		

}
