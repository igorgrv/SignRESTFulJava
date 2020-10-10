package com.accenture.signinup.config.validation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.accenture.signinup.controller.dto.ErrorDTO;

@RestControllerAdvice
public class ErrorHandler {


	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ErrorDTO handle(MethodArgumentNotValidException exception) {
		ErrorDTO error = new ErrorDTO();
		error.setMensagem("mensagem de Erro");
		return error;
	}
}
