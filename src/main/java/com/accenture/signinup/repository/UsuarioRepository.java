package com.accenture.signinup.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accenture.signinup.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
