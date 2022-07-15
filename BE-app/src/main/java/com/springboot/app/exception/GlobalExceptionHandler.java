package com.springboot.app.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.springboot.app.dto.ResponseObject;



@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ ResourceNotFoundException.class })
	protected ResponseEntity<ResponseObject> handleExceptionInternal(RuntimeException exception, WebRequest request) {
		ResponseObject error = new ResponseObject("404", exception.getMessage(), "");
		return new ResponseEntity<ResponseObject>(error, HttpStatus.NOT_FOUND);

	}
	
	@ExceptionHandler({ IllegalArgumentException.class })
	protected ResponseEntity<ResponseObject> handleIllegalArgumentException(RuntimeException exception,
			WebRequest request) {
		ResponseObject error = new ResponseObject("400", exception.getMessage(),"");
		return new ResponseEntity<ResponseObject>(error, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			org.springframework.http.HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		ResponseObject error = new ResponseObject("400", "Validation Error", errors);
		return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
	}
	

}