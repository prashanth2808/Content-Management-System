package com.example.cms.util;

import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor
public class TopicNotSpecifiedException extends RuntimeException {

private	String message;
	
}
