package com.example.cms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.cms.util.ErrorStructure;
import com.example.cms.util.UserNotFoundByIdException;

import lombok.AllArgsConstructor;

@RestControllerAdvice
@AllArgsConstructor
public class UserNotFoundByIdExceptionHandler {

	private ErrorStructure<String> errorStructure;
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handelUserNotFoundByIdException(UserNotFoundByIdException e )

	{
		return ResponseEntity.badRequest().body(errorStructure.setErrorstatus(HttpStatus.BAD_REQUEST.value()).setErrormessage("please enter correct id").setRootcause(e.getMessage()));
	}
}
 