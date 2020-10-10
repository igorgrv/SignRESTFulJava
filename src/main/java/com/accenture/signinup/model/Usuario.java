package com.accenture.signinup.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String email;
	private String senha;
	private LocalDateTime dataCriacao = LocalDateTime.now();
	private LocalDateTime dataAtualizacao;
	private LocalDateTime dataUltimoLogin = LocalDateTime.now();
	@OneToMany(cascade = CascadeType.ALL)
	private List<Telefone> telefones = new ArrayList<Telefone>();
	
	public Usuario() {}
	
	public Usuario(String nome, String email, String password, List<Telefone> telefones) {
		super();
		this.nome = nome;
		this.email = email;
		this.senha = password;
		this.telefones = telefones;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
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
	public List<Telefone> getTelefones() {
		return telefones;
	}
	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public LocalDateTime getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public LocalDateTime getDataUltimoLogin() {
		return dataUltimoLogin;
	}

	public void setDataUltimoLogin(LocalDateTime dataUltimoLogin) {
		this.dataUltimoLogin = dataUltimoLogin;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", dataCriacao="
				+ dataCriacao + ", dataAtualizacao=" + dataAtualizacao + ", dataUltimoLogin=" + dataUltimoLogin
				+ ", telefones=" + telefones + "]";
	}
	
}
