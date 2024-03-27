package com.example.cms.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.cms.util.ErrorStructure;

import lombok.AllArgsConstructor;
@RestControllerAdvice
@AllArgsConstructor

public class ValidateExceptionHandler extends ResponseEntityExceptionHandler
{

	private ErrorStructure  structure;

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		Map<String,String> map = new HashMap<>();

		ex.getAllErrors().forEach(error -> {
			map.put(((FieldError)error).getField(),error.getDefaultMessage());
		});

return	ResponseEntity.badRequest().body(structure.setErrorstatus(HttpStatus.BAD_REQUEST.value()).setErrormessage("Invalid input").setRootcause(map));
	}

}
