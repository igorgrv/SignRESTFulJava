package com.accenture.signinup.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.accenture.signinup.controller.dto.UsuarioDTO;
import com.accenture.signinup.model.Usuario;
import com.accenture.signinup.repository.UsuarioRepository;
import com.accenture.signinup.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UserDetailsService, UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String email) {
		Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
		if (usuario.isPresent()) {
			return usuario.get();
		}
		throw new UsernameNotFoundException("Usuário e/ou senha inválidos");
	}

	@Override
	public List<UsuarioDTO> getUsuarios() {
		return UsuarioDTO.toUsuario(usuarioRepository.findAll());
	}

	@Override
	public Optional<Usuario> getUsuario(String id) {
		return usuarioRepository.findById(id);
	}

	@Override
	public Optional<Usuario> getUsuarioByEmail(Usuario usuario) {
		return usuarioRepository.findByEmail(usuario.getEmail());
	}

	@Override
	public void insertUsuario(Usuario usuario) {
		usuarioRepository.save(usuario);
	}

	@Override
	public Optional<Usuario> findUsuarioForm(String email) {
		return usuarioRepository.findByEmail(email);
	}

}
