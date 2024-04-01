package com.example.cms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.cms.util.ErrorStructure;
import com.example.cms.util.IllegalAccessRequestException;

import lombok.AllArgsConstructor;

@RestControllerAdvice
@AllArgsConstructor
public class IllegalAccessRequestExceptionHandler {

	
	private ErrorStructure<String>  errorStructure;

	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handelexception(IllegalAccessRequestException e)
	{
		return ResponseEntity.badRequest().body(errorStructure.setErrorstatus(HttpStatus.BAD_REQUEST.value()).setErrormessage("User already present").setRootcause( e.getMessage()));


	}
}
