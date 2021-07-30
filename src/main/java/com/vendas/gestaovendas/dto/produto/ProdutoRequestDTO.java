package com.vendas.gestaovendas.dto.produto;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.vendas.gestaovendas.model.Categoria;
import com.vendas.gestaovendas.model.Produto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Produto requisição")
public class ProdutoRequestDTO {

	@ApiModelProperty(value = "Descrição")
	@NotBlank(message = "Descricao")
	@Length(min = 3, max = 100, message = "descricao")
	private String descricao;

	@NotNull(message = "Quantidade")
	@ApiModelProperty(value = "Preço custo")
	private Integer quantidade;

	@NotNull(message = "Preço venda")
	@ApiModelProperty(value = "Preco Custo")
	private BigDecimal precoCusto;

	@Column(name = "preco_venda")
	@ApiModelProperty(value = "Preco Venda")
	private BigDecimal precoVenda;

	@Length(max = 500, message = "observacao")
	@ApiModelProperty(value = "Observação")
	private String observacao;

	public Produto converterParaEntidade(Long codigoCategoria) {
		return new Produto(descricao, quantidade, precoCusto, precoVenda, observacao, new Categoria(codigoCategoria));
	}
	
	public Produto converterParaEntidade(Long codigoCategoria, Long codigoProduto) {
		return new Produto(codigoProduto ,descricao, quantidade, precoCusto, precoVenda, observacao, new Categoria(codigoCategoria));
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPrecoCusto() {
		return precoCusto;
	}

	public void setPrecoCusto(BigDecimal precoCusto) {
		this.precoCusto = precoCusto;
	}

	public BigDecimal getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(BigDecimal precoVenda) {
		this.precoVenda = precoVenda;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

}
