package br.com.desafio.resources.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javassist.NotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validationError(MethodArgumentNotValidException exception){
		StandardError error = new StandardError(HttpStatus.BAD_REQUEST.value(), "Erro na validação de campos!");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<StandardError> NotFoundError(NotFoundException exception){
		StandardError error = new StandardError(HttpStatus.NOT_FOUND.value(), "Produto não encontrado!!");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

}
