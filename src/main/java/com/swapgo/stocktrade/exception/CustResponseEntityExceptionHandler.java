package com.swapgo.stocktrade.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		ExceptionBody ExceptionBody = new ExceptionBody(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity(ExceptionBody, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
		ExceptionBody ExceptionBody = new ExceptionBody(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity(ExceptionBody, HttpStatus.NOT_FOUND);
	}	

	//method to handle validation errors and send it in response
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionBody exceptionResponse = new ExceptionBody(new Date(), "Validation Failed",
				ex.getBindingResult().toString());
		
		//ex.getBindingResult().toString() : validation message
		return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
	}	

}
