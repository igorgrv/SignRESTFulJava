package com.accenture.signinup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.signinup.controller.dto.UsuarioDTO;
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
	public List<UsuarioDTO> list(){
		return UsuarioDTO.toUsuario(usuarioRepository.findAll());
	}

}
