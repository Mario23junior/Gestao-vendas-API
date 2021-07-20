package com.vendas.gestaovendas.Exceptions;

public class Errors {

	private String message;
	private String msgDesenvolvedor;

	public Errors(String message, String msgDesenvolvedor) {
		super();
		this.message = message;
		this.msgDesenvolvedor = msgDesenvolvedor;
	}

	public String getMessage() {
		return message;
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
