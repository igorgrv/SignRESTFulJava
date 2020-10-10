package com.accenture.signinup.controller.dto;

import com.accenture.signinup.model.Telefone;

public class TelefoneDTO {
	
	private String numero;
	private String ddd;
	
	public TelefoneDTO(Telefone telefone) {
		this.numero = telefone.getNumero();
		this.ddd = telefone.getDdd();
	}
	
	public String getNumero() {
		return numero;
	}
	public String getDdd() {
		return ddd;
	}

}
