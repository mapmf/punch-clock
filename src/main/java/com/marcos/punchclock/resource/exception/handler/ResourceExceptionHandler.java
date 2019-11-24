package com.marcos.punchclock.resource.exception.handler;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.marcos.punchclock.services.exceptions.EmployeeAlreadyExistException;
import com.marcos.punchclock.services.exceptions.InvalidPuchClockException;
import com.marcos.punchclock.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException objectNotFoundException,
			HttpServletRequest request) {

		Date date = new Date();

		StandardError error = new StandardError(HttpStatus.NOT_FOUND.value(), objectNotFoundException.getMessage(),
				date.getTime());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(InvalidPuchClockException.class)
	public ResponseEntity<StandardError> invalidPuchClockException(InvalidPuchClockException invalidPuchClockException,
			HttpServletRequest request) {

		Date date = new Date();

		StandardError error = new StandardError(HttpStatus.FORBIDDEN.value(), invalidPuchClockException.getMessage(),
				date.getTime());

		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
	}
	
	@ExceptionHandler(EmployeeAlreadyExistException.class)
	public ResponseEntity<StandardError> employeeAlreadyExistException(EmployeeAlreadyExistException employeeAlreadyExistException,
			HttpServletRequest request) {

		Date date = new Date();

		StandardError error = new StandardError(HttpStatus.FORBIDDEN.value(), employeeAlreadyExistException.getMessage(),
				date.getTime());

		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
	}
}
