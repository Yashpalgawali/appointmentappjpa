package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotExistsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6829855673303648604L;

	public ResourceNotExistsException(String message) {
		super(message);
	}

}
