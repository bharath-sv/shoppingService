package com.shopping.utility;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.shopping.exception.ValidationException;

@RestControllerAdvice
public class ExceptionControllerAdvice
{

	@ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorInfo> meetingSchedulerExceptionHandler(ValidationException exception)
    {

	ErrorInfo errorInfo = new ErrorInfo();
	errorInfo.setErrorCode(HttpStatus.BAD_REQUEST.value());
	errorInfo.setErrorMessage(exception.getMessage());
	return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }

	@ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorInfo> generalExceptionHandler(HttpMessageNotReadableException exception)
    {
		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setErrorCode(HttpStatus.BAD_REQUEST.value());
		errorInfo.setErrorMessage(exception.getMessage());
		return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }
	
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorInfo> generalExceptionHandler(Exception exception)
    {
		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorInfo.setErrorMessage("Internal Server Error");
		return new ResponseEntity<>(errorInfo,
					    HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


