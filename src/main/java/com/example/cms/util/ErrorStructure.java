package com.example.cms.util;

import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
@Getter
public class ErrorStructure<T> {

	private int errorstatus;
	private String errormessage;
	private T rootcause;



	public ErrorStructure<T> setErrorstatus(int errorstatus) {
		this.errorstatus = errorstatus;
		return this;
	}

	

	public ErrorStructure<T> setErrormessage(String errormessage) {
		this.errormessage = errormessage;
		return this;
	}

	

	public ErrorStructure<T>   setRootcause(T rootcause) {
		this.rootcause = rootcause;
		return this;
	}
}
