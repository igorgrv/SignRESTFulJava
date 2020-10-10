package com.accenture.signinup.controller.dto;

public class ErrorDTO {

	private String mensagem;

	public ErrorDTO(String mensagem) {
		this.mensagem = mensagem;
	}

	public ErrorDTO() {
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
