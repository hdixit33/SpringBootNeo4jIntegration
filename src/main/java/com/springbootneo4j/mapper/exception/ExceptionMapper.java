package com.springbootneo4j.mapper.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.springbootneo4j.constants.ErrorConstants;
import com.springbootneo4j.dto.ErrorMessage;
import com.springbootneo4j.exceptions.RequestValidationException;



@ControllerAdvice
public class ExceptionMapper {
	@ExceptionHandler(value = { RequestValidationException.class })
	protected ResponseEntity<ErrorMessage> requestValidationExceptionHandler(
			final RequestValidationException ex) {
		return response(HttpStatus.BAD_REQUEST,
				 ErrorConstants.BAD_REQUEST_CODE,
				 ex.getMessage());
	}
	
	/**
	 * Exception handler.
	 *
	 * @param ex the ex
	 * @return the response entity
	 */
	@ExceptionHandler(value = {Exception.class })
	protected ResponseEntity<ErrorMessage> exceptionHandler(
			final RequestValidationException ex) {
		return response(HttpStatus.INTERNAL_SERVER_ERROR,
				 ErrorConstants.INTERNAL_SERVER_ERROR_CODE,
				 ErrorConstants.INTERNAL_SERVER_ERROR_MSG);
	}

	/**
	 * Response.
	 *
	 * @param status the status
	 * @param code the code
	 * @param message the message
	 * @return the response entity
	 */
	public ResponseEntity<ErrorMessage> response(final HttpStatus status,
			final Integer code, final String message) {
		return new ResponseEntity<ErrorMessage>(new ErrorMessage(code, message), status);
	}
}
