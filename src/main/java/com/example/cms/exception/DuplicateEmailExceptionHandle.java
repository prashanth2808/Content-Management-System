package com.example.cms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.cms.util.DuplicateEmailException;
import com.example.cms.util.ErrorStructure;

import lombok.AllArgsConstructor;


@RestControllerAdvice
@AllArgsConstructor
public class DuplicateEmailExceptionHandle {

	
	private ErrorStructure<String> structure;
	
	private ResponseEntity<ErrorStructure<String>>  handleException( HttpStatus status,String message, String rootcause )
	{
		return ResponseEntity.badRequest().body(structure.setErrorstatus(status.value()).setErrormessage(message).setRootcause(rootcause));
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>>  duplicateEmailExceptionHandler(DuplicateEmailException e  )
	{
		return handleException(HttpStatus.BAD_REQUEST, "User register failed", e.getMessage());
	}
}
