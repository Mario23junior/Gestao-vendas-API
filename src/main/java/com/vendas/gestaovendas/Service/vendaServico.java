package com.vendas.gestaovendas.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vendas.gestaovendas.Exceptions.RegraNegocioDuplicateDataException;
import com.vendas.gestaovendas.dto.Venda.ClienteVendaResponseDTO;
import com.vendas.gestaovendas.dto.Venda.ItemVendaRequestDTO;
import com.vendas.gestaovendas.dto.Venda.VendaRequestDTO;
import com.vendas.gestaovendas.dto.Venda.VendaResponseDTO;
import com.vendas.gestaovendas.model.Cliente;
import com.vendas.gestaovendas.model.ItemVenda;
import com.vendas.gestaovendas.model.Produto;
import com.vendas.gestaovendas.model.Venda;
import com.vendas.gestaovendas.repository.ItemVendaRepository;
import com.vendas.gestaovendas.repository.VendaRepository;

@Service
public class vendaServico extends AbstractVendaService {

	private ClienteService clienteService;
	private VendaRepository vendaRepository;
	private ItemVendaRepository itemVendaRepository;
	private ProdutoService produtoService;

	public vendaServico(ClienteService clienteService, VendaRepository vendaRepository,
			ItemVendaRepository itemVendaRepository, ProdutoService produtoService) {
		this.clienteService = clienteService;
		this.vendaRepository = vendaRepository;
		this.itemVendaRepository = itemVendaRepository;
		this.produtoService = produtoService;
	}

	public ClienteVendaResponseDTO listVendaproCliente(Long codigoCliente) {
		Cliente cliente = validarObjetoVendaExiste(codigoCliente);
		List<VendaResponseDTO> vendaResponseDtoList = vendaRepository.findByClienteCodigo(codigoCliente).stream()
				.map(venda -> criandoVendaResponseDTO(venda, itemVendaRepository.findByVendaPorCodigo(venda.getCodigo())))
				.collect(Collectors.toList());

		return new ClienteVendaResponseDTO(cliente.getNome(), vendaResponseDtoList);
	}

	public ClienteVendaResponseDTO listaVendaPorCodigo(Long codigoVenda) {
		Venda venda = validarVendaExiste(codigoVenda);
		List<ItemVenda> itensVendaList = itemVendaRepository.findByVendaPorCodigo(venda.getCodigo());
		return refatorandoClienteVendaResponseDTO(venda, itensVendaList);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public ClienteVendaResponseDTO Salvar(Long codigoCliente, VendaRequestDTO vendaDto) {
		Cliente cliente = validarObjetoVendaExiste(codigoCliente);
		validarProdutoExisteEatualizarQuantidade(vendaDto.getItensVendaDto());
		Venda vendaSalva = salvarVenda(cliente, vendaDto);
		List<ItemVenda> saveItems = itemVendaRepository.findByVendaPorCodigo(vendaSalva.getCodigo());

		return refatorandoClienteVendaResponseDTO(vendaSalva, saveItems);
	}
	

	private Venda salvarVenda(Cliente cliente, VendaRequestDTO vendaDto) {
		Venda salvaVenda = vendaRepository.save(new Venda(vendaDto.getDate(), cliente));
		vendaDto.getItensVendaDto().stream().map(ItemVendaDTO -> criandoItemVenda(ItemVendaDTO, salvaVenda))
				.forEach(itemVendaRepository::save);
		return salvaVenda;
	}

	private void validarProdutoExisteEatualizarQuantidade(List<ItemVendaRequestDTO> itensVendaDto) {
		itensVendaDto.forEach(item -> {
		  Produto produto = produtoService.validarProdutoExist(item.getCodigoProduto());
		   validarProdutoQuantidadeProdutoExiste(produto, item.getQuantidade());
		   produto.setQuantidade(produto.getQuantidade() - item.getQuantidade());
		   produtoService.atualizarQuantidadeAposVenda(produto);
		   
		});
	}
	
	private void validarProdutoQuantidadeProdutoExiste(Produto produto, Integer qtdeVendaDto) {
		if(!(produto.getQuantidade() >= qtdeVendaDto)) {
			throw new RegraNegocioDuplicateDataException(String.format("A quantidade %s solicitada para o produto %s não esta disponivel em estoque", 
					qtdeVendaDto, produto.getDescricao()));
		}
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
