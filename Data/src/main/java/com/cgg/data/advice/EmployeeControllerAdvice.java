package com.cgg.data.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cgg.data.exception.EmployeeNotFoundException;
import com.cgg.data.exception.NoRecordFoundException;
import com.cgg.data.exception.ServiceException;

@ControllerAdvice
public class EmployeeControllerAdvice {

	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<String> handleEmployeeNotFoundException(EmployeeNotFoundException employeeNotFoundException){
		return new ResponseEntity<String>("Employee not found with given id", HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(NoRecordFoundException.class)
	public ResponseEntity<String> handleNoRecordFoundException	(NoRecordFoundException noRecordFoundException){
		return new ResponseEntity<String>("No records found",HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(ServiceException.class)
	public ResponseEntity<String> handleServiceException	(ServiceException serviceException){
		return new ResponseEntity<String>("No records found",HttpStatus.BAD_REQUEST);
	}
}
