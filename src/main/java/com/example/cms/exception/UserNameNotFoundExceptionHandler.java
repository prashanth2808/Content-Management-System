package com.example.cms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.cms.util.ErrorStructure;

import lombok.AllArgsConstructor;


@RestControllerAdvice
@AllArgsConstructor
public class UserNameNotFoundExceptionHandler {

	
	
	private ErrorStructure<String>  errorStructure;
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handelUserNameNotFound(UsernameNotFoundException e)
	{
		
		return ResponseEntity.badRequest().body(errorStructure.setErrorstatus(HttpStatus.BAD_REQUEST.value()).setErrormessage("User name notfound").setRootcause( e.getMessage()));

	}

}
