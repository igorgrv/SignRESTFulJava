package com.accenture.signinup.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.accenture.signinup.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	@Query("SELECT u FROM Usuario u WHERE u.id = :user_id")
	List<Usuario> getByUsuarioId(@Param("user_id") Long user_id);
}
