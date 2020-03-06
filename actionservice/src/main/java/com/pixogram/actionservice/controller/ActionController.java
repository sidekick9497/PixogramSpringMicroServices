package com.pixogram.actionservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pixogram.actionservice.service.IActionService;
import com.pixogram.actionservice.exception.ActionErrorResponse;
import com.pixogram.actionservice.model.ActionData;

@RestController
public class ActionController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IActionService actionService;

	@GetMapping("")
	
	@ExceptionHandler  // ~catch
	public ResponseEntity<ActionErrorResponse> productOperationErrorHAndler(Exception ex) {
		// create error object
		ActionErrorResponse error = new ActionErrorResponse(ex.getMessage(), 
															  HttpStatus.BAD_REQUEST.value(), 
															  System.currentTimeMillis());
		ResponseEntity<ActionErrorResponse> response =
										new ResponseEntity<ActionErrorResponse>(error, HttpStatus.NOT_FOUND);
		logger.error("Exception :" + error);
		
		return response;
	}
	
}
