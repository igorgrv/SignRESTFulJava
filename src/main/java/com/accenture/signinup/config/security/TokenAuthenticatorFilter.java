package com.accenture.signinup.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.accenture.signinup.model.Usuario;
import com.accenture.signinup.repository.UsuarioRepository;
import com.accenture.signinup.service.TokenService;

public class TokenAuthenticatorFilter extends OncePerRequestFilter{

	private TokenService tokenService;
	private UsuarioRepository usuarioRepository;
	
	public TokenAuthenticatorFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
		this.tokenService = tokenService;
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		

		String token = getToken(request);
		boolean tokenIsValid = tokenService.isValid(token);
		if (tokenIsValid) {
			authUser(token);
		}		
		filterChain.doFilter(request, response);
	}

	private void authUser(String token) {
		String idUser = tokenService.getUserId(token);
		Usuario usuario = usuarioRepository.findById(idUser).get();
		UsernamePasswordAuthenticationToken authentication= new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private String getToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		return token.substring(7, token.length());
	}

}
