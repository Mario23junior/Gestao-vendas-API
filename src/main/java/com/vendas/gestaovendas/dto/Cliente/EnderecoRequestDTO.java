package com.vendas.gestaovendas.dto.Cliente;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Endereço retorno DTO")
public class EnderecoRequestDTO {
   
	@ApiModelProperty(value = "Logradouro")
	@NotBlank(message = "Logradouro")
	@Length(min = 3, max = 50, message = "Logradouro")
  	private String logradouro;

	@NotNull(message = "Numero")
	@ApiModelProperty(value = "Numero")
 	private Integer numero;

	@ApiModelProperty(value = "Complemento")
	@Length(min = 3, max = 50)
 	private String complemento;
 
	@ApiModelProperty(value = "Bairro")
	@NotBlank(message = "Logradouro")
	@Length(min = 3, max = 50, message = "Bairro")
  	private String bairro;

	@ApiModelProperty(value = "Cep")
	@NotBlank(message = "Cep")
	@Length(min = 3, max = 50, message = "Cep")
	@Pattern(regexp = "[\\d]{5}-[\\d]{3}", message = "Cep")
 	private String cep;

	
	@ApiModelProperty(value = "Cidade")
	@NotBlank(message = "cidade")
	@Length(min = 3, max = 30, message = "cidade")
 	private String cidade;

	@NotBlank(message = "Estado")
	@Length(min = 3, max = 50, message = "Estado")
	@ApiModelProperty(value = "Estado")
 	private String estado;

	public EnderecoRequestDTO(String logradouro, Integer numero, String complemento, String bairro, String cep,
			String cidade, String estado) {
 		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
		this.cidade = cidade;
		this.estado = estado;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
}
