package com.accenture.signinup.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.accenture.signinup.controller.dto.ErrorDTO;
import com.accenture.signinup.controller.dto.UsuarioDTO;
import com.accenture.signinup.model.Usuario;
import com.accenture.signinup.repository.UsuarioRepository;

@RestController
public class UsuarioController {


	@Autowired
	private UsuarioRepository usuarioRepository;

	@RequestMapping("/")
	public String helloWorld() {
		return "hello";
	}

	@GetMapping("/usuarios")
	public List<UsuarioDTO> list(Long user_id) {
		if (user_id == null) {
			return UsuarioDTO.toUsuario(usuarioRepository.findAll());
		}
		return UsuarioDTO.toUsuario(usuarioRepository.getByUsuarioId(user_id));
	}

	@GetMapping("/usuarios/{id}")
	public ResponseEntity<UsuarioDTO> getUsuario(@PathVariable Long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		if (usuario.isPresent()) {
			return ResponseEntity.ok(new UsuarioDTO(usuario.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping("/signup")
	@Transactional
	public ResponseEntity<Object> signup(@RequestBody @Valid Usuario usuario, UriComponentsBuilder uriBuilder) {

		Optional<Usuario> usuarioEmail = usuarioRepository.findByEmail(usuario.getEmail());
		if (usuarioEmail.isPresent()) {
			ErrorDTO errorDTO = new ErrorDTO();
			errorDTO.setMensagem("E-mail já existente");
			return ResponseEntity.badRequest().body(errorDTO);
		}
		usuarioRepository.save(usuario);

		URI uri = uriBuilder.path("/signup/{id}").buildAndExpand(usuario.getId()).toUri();
		return ResponseEntity.created(uri).body(new UsuarioDTO(usuario));
	}


}
