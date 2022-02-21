package com.example.AnkushDemo.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.AnkushDemo.userData.UserNotFoundException;

import java.util.*;
@ControllerAdvice
@RestController
public class CustomizedReponseEntityHandler 
extends ResponseEntityExceptionHandler {
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request){
		ExceptionModel exceptions=new ExceptionModel(new Date(),ex.getMessage(),request.getDescription(false));
		return new ResponseEntity(exceptions,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundExceptions(UserNotFoundException ex, WebRequest request){
		ExceptionModel exceptions=new ExceptionModel(new Date(),ex.getMessage(),request.getDescription(false));
		return new ResponseEntity(exceptions,HttpStatus.NOT_FOUND);
	}
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionModel exceptions=new ExceptionModel(new Date(),"Validations is not done properly",ex.getBindingResult().toString());

		return new ResponseEntity(exceptions,HttpStatus.BAD_REQUEST);
	}
	
}
