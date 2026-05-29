package com.example.demo.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.example.demo.dto.ErrorResponseDto;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = GlobalException.class)
	public ResponseEntity<ErrorResponseDto> handleGlobalException(GlobalException exception,
			WebRequest request) {

		ErrorResponseDto error = new ErrorResponseDto(request.getDescription(false), exception.getMessage(),
				HttpStatus.NOT_FOUND.toString(), LocalDateTime.now());

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);

	}
	
	@ExceptionHandler(value = ResourceNotExistsException.class)
	public ResponseEntity<ErrorResponseDto> handleResourceNotExistsException(ResourceNotExistsException exception,
			WebRequest request) {

		ErrorResponseDto error = new ErrorResponseDto(request.getDescription(false), exception.getMessage(),
				HttpStatus.NOT_FOUND.toString(), LocalDateTime.now());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

	}
	
	@ExceptionHandler(value = ResourceAlreadyExistsException.class)
	public ResponseEntity<ErrorResponseDto> handleResourceAlreadyExistsException(ResourceAlreadyExistsException exception,
			WebRequest request) {

		ErrorResponseDto error = new ErrorResponseDto(request.getDescription(false), exception.getMessage(),
				HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

	}
	
	
}
