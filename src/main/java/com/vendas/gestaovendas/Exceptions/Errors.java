package com.vendas.gestaovendas.Exceptions;

public class Errors {

	private Integer status;
	private String message;
	private String msgDesenvolvedor;

	public Errors(String message, String msgDesenvolvedor, Integer status) {
		super();
		this.status = status;
		this.message = message;
		this.msgDesenvolvedor = msgDesenvolvedor;
	}

	public String getMessage() {
		return message;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMsgDesenvolvedor() {
		return msgDesenvolvedor;
	}

	public void setMsgDesenvolvedor(String msgDesenvolvedor) {
		this.msgDesenvolvedor = msgDesenvolvedor;
	}

}
