package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ResourceNotModifiedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6829855673303648604L;

	public ResourceNotModifiedException(String message) {
		super(message);
	}

}
