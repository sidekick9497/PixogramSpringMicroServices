package com.sidekick.pixogram.userservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sidekick.pixogram.userservice.model.ResponseData;
import com.sidekick.pixogram.userservice.service.IUserServices;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin("*")
@RestController
public class LoginController {
	
	// dependency
	@Autowired
	private IUserServices userService;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	// testing end-point
	@GetMapping("/login")
	public ResponseEntity<Integer> login(HttpServletRequest request) {
		// if called then credentials are valid
		logger.info("Logged In...");
		String userName  = request.getHeader("userName");
		System.out.println(userName);
		 Integer data = this.userService.getUserByUserName(userName).getId();
		 System.out.println(data);


				return	new ResponseEntity<Integer>(data, HttpStatus.OK);


}

}
