package com.accenture.signinup.controller.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.accenture.signinup.model.Usuario;

public class UsuarioDTO {

	private String nome;
	private String email;
	private String senha;
	private List<TelefoneDTO> telefones;

	public UsuarioDTO(Usuario usuario) {
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
		this.senha = usuario.getSenha();
		this.telefones = new ArrayList<>();
		this.telefones.addAll(usuario.getTelefones().stream().map(TelefoneDTO::new).collect(Collectors.toList()));
	}

	public static List<UsuarioDTO> toUsuario(List<Usuario> usuarios) {
		return usuarios.stream().map(UsuarioDTO::new).collect(Collectors.toList());
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

	public List<TelefoneDTO> getTelefones() {
		return telefones;
	}

}
