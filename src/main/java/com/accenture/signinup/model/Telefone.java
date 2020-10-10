package com.accenture.signinup.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;


public class Telefone{

	@Id
	private String id; 
	@NotNull @NotEmpty @Length(min = 5)
	private String numero;
	@NotNull @NotEmpty @Length(min = 2)
	private String ddd;
	private Usuario usuario;
	
	
	public Telefone(String numero, String ddd, Usuario usuario) {
		super();
		this.numero = numero;
		this.ddd = ddd;
		this.usuario = usuario;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getDdd() {
		return ddd;
	}
	public void setDdd(String ddd) {
		this.ddd = ddd;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Telefone [id=" + id + ", numero=" + numero + ", ddd=" + ddd + ", usuario=" + usuario + "]";
	}
	
}
