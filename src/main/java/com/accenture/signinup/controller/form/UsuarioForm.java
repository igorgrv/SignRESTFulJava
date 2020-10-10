package com.accenture.signinup.controller.form;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class UsuarioForm {

	public String email, senha;

	public UsuarioForm() {
		super();
	}

	public UsuarioForm(String email, String senha) {
		super();
		this.email = email;
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public UsernamePasswordAuthenticationToken toUser() {
		return new UsernamePasswordAuthenticationToken(this.email, this.senha);
	}
}
