package com.vendas.gestaovendas.model;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Venda")
public class Venda {
   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo")
	private Long codigo;
	
	@NotNull
	@Column(name = "data")
	private LocalDate date;
	
	@ManyToOne
	@JoinColumn(name="codigo_cliente",referencedColumnName = "codigo")
	private Cliente cliente;

	public Venda() {

	}
	
	public Venda(Long codigo, LocalDate date, Cliente cliente) {
		this.codigo = codigo;
  		this.date = date;
		this.cliente = cliente;
	}
	
	public Venda(LocalDate date, Cliente cliente) {
		super();
 		this.date = date;
		this.cliente = cliente;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cliente, codigo, date);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Venda other = (Venda) obj;
		return Objects.equals(cliente, other.cliente) && Objects.equals(codigo, other.codigo)
				&& Objects.equals(date, other.date);
	}
	
	
	
}
