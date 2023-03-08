package com.fraga.avaliacao.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fraga.avaliacao.data.error.ErrorMessage;
import com.fraga.avaliacao.exception.NotSalvedException;
import com.fraga.avaliacao.exception.ResourceNotFoundException;
import com.fraga.avaliacao.exception.SQLPersistenceException;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(SQLPersistenceException.class)
	public final ResponseEntity<ErrorMessage> handleSQLExceptions(Exception ex) {
		ErrorMessage error = new ErrorMessage("Persistence Error!", HttpStatus.BAD_REQUEST.value(), ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NotSalvedException.class)
	public final ResponseEntity<ErrorMessage> handleNotSalvedExceptions(Exception ex) {
		ErrorMessage error = new ErrorMessage("Not Salved!", HttpStatus.EXPECTATION_FAILED.value(), ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.EXPECTATION_FAILED);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<ErrorMessage> handleBadRequestExceptions(Exception ex) {
		ErrorMessage error = new ErrorMessage("Not Found!", HttpStatus.BAD_REQUEST.value(), ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}
