package com.accenture.signinup.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Telefone {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	private String numero;
	private String ddd;
	@ManyToOne
	private Usuario usuario;
	
	public Telefone() {}
	
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
