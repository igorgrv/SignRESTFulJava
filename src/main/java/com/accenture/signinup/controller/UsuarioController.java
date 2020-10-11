package com.accenture.signinup.controller;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.accenture.signinup.controller.dto.ErrorDTO;
import com.accenture.signinup.controller.dto.UsuarioDTO;
import com.accenture.signinup.controller.form.UsuarioForm;
import com.accenture.signinup.model.Usuario;
import com.accenture.signinup.repository.UsuarioRepository;
import com.accenture.signinup.service.impl.UsuarioServiceImpl;

@RestController
public class UsuarioController {

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioServiceImpl usuarioService;

	@GetMapping("/health")
	public String health() {
		return LocalDateTime.now().toString();
	}
	
	@GetMapping("/usuarios")
	public List<UsuarioDTO> getUsuarios() {
		return usuarioService.getUsuarios();
	}

	@GetMapping("/usuarios/{id}")
	public ResponseEntity<Object> getUsuario(@PathVariable String id) {
		Optional<Usuario> usuario = usuarioService.getUsuario(id);
		if (usuario.isPresent()) {
			if (!usuario.get().tokenPassouTrintaMinutos()) {
				return new ResponseEntity<>("Sessão Invalida", HttpStatus.BAD_REQUEST);
			}
			return ResponseEntity.ok(new UsuarioDTO(usuario.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping("/signup")
	@Transactional
	public ResponseEntity<Object> signup(@RequestBody @Valid Usuario usuario, UriComponentsBuilder uriBuilder) {

		Optional<Usuario> usuarioEmail = usuarioService.getUsuarioByEmail(usuario);
		if (usuarioEmail.isPresent()) {
			ErrorDTO errorDTO = new ErrorDTO();
			errorDTO.setMensagem("E-mail já existente");
			return ResponseEntity.badRequest().body(errorDTO);
		}

		String senhaEncoded = new BCryptPasswordEncoder().encode(usuario.getSenha());
		usuario.setSenha(senhaEncoded);
		usuario.setDataCriacao(LocalDateTime.now());
		usuario.setDataUltimoLogin(LocalDateTime.now());
		usuarioService.insertUsuario(usuario);

		URI uri = uriBuilder.path("/signup/{id}").buildAndExpand(usuario.getId()).toUri();
		return ResponseEntity.created(uri).body(new UsuarioDTO(usuario));
	}

	@PostMapping("/signin")
	@Transactional
	public ResponseEntity<Object> signin(@RequestBody @Valid UsuarioForm form, UriComponentsBuilder uriBuilder) {

		UsernamePasswordAuthenticationToken login = form.toUser();

		try {
			authManager.authenticate(login);
			Optional<Usuario> usuario = usuarioRepository.findByEmail(form.getEmail());
			if (usuario.isPresent()) {
				usuario.get().setDataUltimoLogin(LocalDateTime.now());
				return ResponseEntity.ok(new UsuarioDTO(usuario.get()));
			}
			return new ResponseEntity<>("Usuario e/ou senha ínválidos", HttpStatus.UNAUTHORIZED);
		} catch (AuthenticationException e) {
			return new ResponseEntity<>("Usuario e/ou senha ínválidos", HttpStatus.UNAUTHORIZED);
		}
	}

}
