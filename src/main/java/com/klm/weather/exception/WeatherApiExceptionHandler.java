package com.klm.weather.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;



@ControllerAdvice
public class WeatherApiExceptionHandler {
	
	@ExceptionHandler(InvalidInputException.class)
	public ResponseEntity<ApiError> handleInputData(InvalidInputException ex, WebRequest request) {

		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST.value(), new Date(), ex.getLocalizedMessage(),
				request.getDescription(false), "Invalid input data");

		return new ResponseEntity<ApiError>(apiError, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ApiError> handleInputData(HttpMessageNotReadableException ex, WebRequest request) {

		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST.value(), new Date(), ex.getLocalizedMessage(),
				request.getDescription(false), "Invalid input data");

		return new ResponseEntity<ApiError>(apiError, HttpStatus.BAD_REQUEST);
	}
	
	

}
