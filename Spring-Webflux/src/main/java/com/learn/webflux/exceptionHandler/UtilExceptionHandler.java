package com.learn.webflux.exceptionHandler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.learn.webflux.dto.UtilExceptionDto;

@ControllerAdvice
public class UtilExceptionHandler {

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<UtilExceptionDto> handleRuntimeException(RuntimeException exception) {
		return ResponseEntity.badRequest().body(new UtilExceptionDto("001", exception.getMessage()));
	}
}
