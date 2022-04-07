package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
	
	
	@ExceptionHandler(DemoException.class)
	public ResponseEntity<String> overLimit(DemoException  demo){
		
		return new ResponseEntity<String>("Record is not found",HttpStatus.BAD_REQUEST);
	}

}
