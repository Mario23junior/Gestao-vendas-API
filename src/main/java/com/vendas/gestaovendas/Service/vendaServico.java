package com.vendas.gestaovendas.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.vendas.gestaovendas.Exceptions.RegraNegocioDuplicateDataException;
import com.vendas.gestaovendas.dto.Venda.ClienteVendaResponseDTO;
import com.vendas.gestaovendas.dto.Venda.VendaResponseDTO;
import com.vendas.gestaovendas.model.Cliente;
import com.vendas.gestaovendas.model.ItemVenda;
import com.vendas.gestaovendas.model.Venda;
import com.vendas.gestaovendas.repository.ItemVendaRepository;
import com.vendas.gestaovendas.repository.VendaRepository;

@Service
public class vendaServico extends AbstractVendaService {

	private ClienteService clienteService;
	private VendaRepository vendaRepository;
	private ItemVendaRepository itemVendaRepository;

	public vendaServico(ClienteService clienteService, VendaRepository vendaRepository,
			ItemVendaRepository itemVendaRepository) {
		this.clienteService = clienteService;
		this.vendaRepository = vendaRepository;
		this.itemVendaRepository = itemVendaRepository;
	}

	public ClienteVendaResponseDTO listVendaproCliente(Long codigoCliente) {
		Cliente cliente = validarObjetoVendaExiste(codigoCliente);
		List<VendaResponseDTO> vendaResponseDtoList = vendaRepository.findByClienteCodigo(codigoCliente)
				.stream()
				.map(venda -> criandoVendaResponseDTO(venda, itemVendaRepository.findByVendaCodigo(venda.getCodigo())))
				.collect(Collectors.toList());

		return new ClienteVendaResponseDTO(cliente.getNome(), vendaResponseDtoList);
	}

	public ClienteVendaResponseDTO listaVendaPorCodigo(Long codigoVenda) {
		Venda venda = validarVendaExiste(codigoVenda);
		List<ItemVenda> itensVendaList = itemVendaRepository.findByVendaCodigo(venda.getCodigo());
		return new ClienteVendaResponseDTO(venda.getCliente().getNome(),
				Arrays.asList(criandoVendaResponseDTO(venda, itensVendaList)));
	}

	private Cliente validarObjetoVendaExiste(Long codigoCliente) {
		Optional<Cliente> buscaCodigo = clienteService.listById(codigoCliente);
		if (buscaCodigo.isEmpty()) {
			throw new RegraNegocioDuplicateDataException(
					String.format("O cliente de codigo %s não existe", codigoCliente));
		}
		return buscaCodigo.get();
	}

	private Venda validarVendaExiste(Long CodigoVenda) {
		Optional<Venda> vendaId = vendaRepository.findById(CodigoVenda);
		if (vendaId.isEmpty()) {
			throw new RegraNegocioDuplicateDataException(
					String.format("Codigo da venda %s não encontrada.", CodigoVenda));
		}
		return vendaId.get();
	}

}
