package com.marcos.punchclock.resource.exception.handler;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.marcos.punchclock.services.exceptions.EmployeeAlreadyExistException;
import com.marcos.punchclock.services.exceptions.InvalidPuchClockException;
import com.marcos.punchclock.services.exceptions.ObjectNotFoundException;

/**
 * 
 * This class handles exceptions throwned during api requests
 * 
 * Every exception handler return a ResponseEntity of StandardError type to give
 * a more friendly error message
 * 
 * @author Marcos André
 *
 */

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
	public ResponseEntity<StandardError> employeeAlreadyExistException(
			EmployeeAlreadyExistException employeeAlreadyExistException, HttpServletRequest request) {

		Date date = new Date();

		StandardError error = new StandardError(HttpStatus.FORBIDDEN.value(),
				employeeAlreadyExistException.getMessage(), date.getTime());

		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> methodArgumentNotValidException(
			MethodArgumentNotValidException methodArgumentNotValidException, HttpServletRequest request) {

		Date now = new Date();

		List<FieldError> allFieldErrors = methodArgumentNotValidException.getBindingResult().getFieldErrors();

		ValidationError validationError = new ValidationError(HttpStatus.BAD_REQUEST.value(),
				"Validation Error", now.getTime());

		for (FieldError fieldError : allFieldErrors) {

			FieldMessage fieldMessage = new FieldMessage(fieldError.getField(), fieldError.getDefaultMessage());
			validationError.addFieldMessage(fieldMessage );
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationError);
	}
}
