package com.sidekick.pixogram.userservice.controller;


import java.util.Optional;

import com.sidekick.pixogram.userservice.exception.UserErrorResponse;
import com.sidekick.pixogram.userservice.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sidekick.pixogram.userservice.service.AuthoritiesService;
import com.sidekick.pixogram.userservice.service.IUserServices;
import com.sidekick.pixogram.userservice.entity.Users;
import com.sidekick.pixogram.userservice.exception.UserNotFoundException;
import com.sidekick.pixogram.userservice.model.DataModel;
import com.sidekick.pixogram.userservice.model.UserModel;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UsersController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IUserServices userServices;
	
	@Autowired
	private AuthoritiesService authoritiesService;
	@Autowired
	private StorageService storageService;
	
	@GetMapping("/users")
	public ResponseEntity<DataModel> getall(){
		DataModel data = new DataModel();
		data.setUsers(this.userServices.getall());
		ResponseEntity<DataModel> result = new ResponseEntity<DataModel>(data, HttpStatus.OK);
		return result;
	}
	
	@GetMapping("/users/{userId}")
	public ResponseEntity<UserModel> getById(@PathVariable Integer userId){
		UserModel user = new UserModel();
		Users record = new Users();
		Optional<Users> users = this.userServices.getWithId(userId);
		if(users.isPresent())
			record = users.get();
		else {
			throw new UserNotFoundException("User not found");
		}
	
		user.setUsername(record.getUserName());
		user.setDob(record.getDob());
		user.setFirstName(record.getFirstName());
		user.setLastName(record.getLastName());
		user.setPassword(record.getPassword());
		user.setEmail(record.getEmail());
		user.setProfilePicUrl(record.getProfile());
		ResponseEntity<UserModel> result = new ResponseEntity<UserModel>(user, HttpStatus.OK);
		return result;
	}
	
	//save new user
	@PostMapping("/users/signup")
	public ResponseEntity<UserModel> save(@RequestBody UserModel user) {
		
		this.userServices.saveuser(user);
		ResponseEntity<UserModel> response = new ResponseEntity<UserModel>(user,HttpStatus.OK);
		System.out.println("called signup");
		return response;
		
		
	}
	
	//update user
	@PutMapping("/users")
	public boolean update(@RequestBody UserModel user) {
		
		this.userServices.updateuser(user);
		return true;
		
	}

	@PutMapping("/users/profile/updateProfilePic")
	public boolean updateProfilePic(@RequestParam("file")MultipartFile profilePic)
	{
		// incomplete implementation, add fields to update data in user Entitry also.
		this.storageService.store(profilePic);
		return true;

	}
	
	@ExceptionHandler  // ~catch
	public ResponseEntity<UserErrorResponse> productOperationErrorHandler(Exception ex) {
		// create error object
		UserErrorResponse error = new UserErrorResponse(ex.getMessage(), 
															  HttpStatus.BAD_REQUEST.value(), 
															  System.currentTimeMillis());
		ResponseEntity<UserErrorResponse> response =
										new ResponseEntity<UserErrorResponse>(error, HttpStatus.NOT_FOUND);
		logger.error("Exception :" + error);
		
		return response;
	}


}
	/*@PostMapping("/products") // POST HTTP VERB
	// public ResponseEntity<Product> save(@RequestBody Product product)
	public ResponseEntity<Product> save(@RequestParam("file") MultipartFile file,
										@RequestParam("userId") Long userId, @RequestParam("url") String url, @RequestParam("name") String name,
										@RequestParam("category") String category, @RequestParam("cost") String cost) {

		// explicitly need to create product object
		Product product = new Product(name, category, Integer.parseInt(cost), url);

		if(!this.productService.addProduct(product))
			throw new RuntimeException("Could not add new record!!!");

		// string file in static folder
		this.storageService.store(file);

		logger.info("Media is uploaded successfully " + file.getOriginalFilename() + "!");


		ResponseEntity<Product> response =
				new ResponseEntity<Product>(product, HttpStatus.OK);

		return response;
	}*/