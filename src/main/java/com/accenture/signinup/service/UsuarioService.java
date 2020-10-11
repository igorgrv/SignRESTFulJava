package com.accenture.signinup.service;

import java.util.List;
import java.util.Optional;

import com.accenture.signinup.controller.dto.UsuarioDTO;
import com.accenture.signinup.model.Usuario;

public interface UsuarioService {

	List<UsuarioDTO> getUsuarios();
	Optional<Usuario> getUsuario(String id);
	Optional<Usuario> getUsuarioByEmail(Usuario usuario);
	void insertUsuario(Usuario usuario);
	Optional<Usuario> findUsuarioForm(String email);
}
