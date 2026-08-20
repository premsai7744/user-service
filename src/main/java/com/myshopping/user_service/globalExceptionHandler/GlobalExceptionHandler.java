package com.myshopping.user_service.globalExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
		logger.info("Handling MethodArgumentNotValidException.");
		
		List<String> errorMessagesList = methodArgumentNotValidException
									     .getFieldErrors()
									     .stream()
									     .map(fe->fe.getDefaultMessage())
									     .collect(Collectors.toList());
		
		logger.info("error messages list {}",errorMessagesList);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				             .body(errorMessagesList);
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception exception){
		logger.info("Handling Exception.");
		
		String exceptionMessage = exception.getMessage();
		
		logger.info("exceptionMessage {}",exceptionMessage);
		
		return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("Something went wrong.");
	}
}





















