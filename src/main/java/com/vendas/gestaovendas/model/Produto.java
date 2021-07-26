package com.vendas.gestaovendas.model;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

 
@Entity
@Table(name = "produto")
public class Produto {
   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@NotBlank(message = "Descricao")
	@Length(min = 3, max = 100, message = "descricao")
	@Column(name = "descricao")
	private String descricao;
	
	@NotNull(message = "Quantidade")
  	@Column(name = "quantidade")
	private Integer quantidade;
	
	@NotNull(message = "Preço custo")
	@Column(name = "preco_curto")
	private BigDecimal precoCusto;
	
	@NotNull(message = "Preço venda")
	@Column(name = "preco_venda")
	private BigDecimal precoVenda;
	
	@Length(max = 500, message = "observacao")
	@Column(name = "observacao")
	private String observacao;
	
	@NotNull(message = "Codigo categoria ")
	@ManyToOne
	@JoinColumn(name = "codigo_categoria", referencedColumnName = "codigo")
 	private Categoria categoria;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public int hashCode() {
		return Objects.hash(categoria, codigo, descricao, observacao, precoCusto, precoVenda, quantidade);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		return Objects.equals(categoria, other.categoria) && Objects.equals(codigo, other.codigo)
				&& Objects.equals(descricao, other.descricao) && Objects.equals(observacao, other.observacao)
				&& Objects.equals(precoCusto, other.precoCusto) && Objects.equals(precoVenda, other.precoVenda)
				&& Objects.equals(quantidade, other.quantidade);
	}

}
 

