package com.spring.rest.example1.exception;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(EntityNotFoundException.class)
	public final ResponseEntity<Object> handleEntityNotFoundException(Exception ex, WebRequest request)
			throws Exception {
		ExceptionResponseEntity exceptionResponseEntity = new ExceptionResponseEntity(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<Object>(exceptionResponseEntity, HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> errors = new ArrayList<>();
		for (final FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
			errors.add("Validation Error found in " + fieldError.getField() + " : " + fieldError.getDefaultMessage());
		}

		for (final ObjectError objectError : ex.getBindingResult().getGlobalErrors()) {
			errors.add(objectError.getObjectName() + " : " + objectError.getDefaultMessage());
		}
		ExceptionResponseEntity exceptionResponseEntity = new ExceptionResponseEntity(new Date(), "Validation Failed",
				errors.toString());
		return new ResponseEntity<Object>(exceptionResponseEntity, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public void constraintViolationException(HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.BAD_REQUEST.value());
	}

	@ExceptionHandler(Exception.class)
	public void GenericException(HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value());
	}

	@ExceptionHandler(AccessDeniedException.class)
	public void unauthorizedException(HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.UNAUTHORIZED.value());
	}
}
