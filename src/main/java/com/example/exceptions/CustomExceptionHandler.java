package com.example.exceptions;

import com.example.errors.ApiError;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({BadRequestException.class
	})
	protected ResponseEntity<Object> handlePayloadException(
		BadRequestException ex) {
		List<String> errors = new ArrayList<String>();
//		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
//			errors.add(error.getField() + ": " + error.getDefaultMessage());
//		}
//		for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
//			errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
//		}

		errors.add(ex.getMessage());

		ApiError apiError =
			new ApiError(HttpStatus.BAD_REQUEST, "Name could not be null or empty", errors);
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

}
