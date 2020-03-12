package com.sidekick.pixogram.miscplumbing.controller;

import com.sidekick.pixogram.miscplumbing.feignproxy.FollowServiceProxy;
import com.sidekick.pixogram.miscplumbing.feignproxy.UserServiceProxy;
import com.sidekick.pixogram.miscplumbing.model.SearchedUserModelList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sidekick.pixogram.miscplumbing.model.SearchedUserModel;

@CrossOrigin("*")
@RestController
public class MiscController {

	@Autowired
	private UserServiceProxy userServiceProxy;
	
	@Autowired
	private FollowServiceProxy followServiceProxy;
	
	@GetMapping("/searched-users/{searchText}/myid/{userId}")
	public ResponseEntity<SearchedUserModelList> searchUsers(@PathVariable String searchText, @PathVariable Integer userId) {
		// 1 retreived list of searched users
		SearchedUserModelList list =  
				this.userServiceProxy.getSearchedUsers(searchText).getBody();
		
		// 2. loop through all users and find if following
		for(SearchedUserModel user : list.getUserList()) {
			// user.getUserId()
			Boolean status = this.followServiceProxy.getFollowingStatus(userId, user.getUserId()).getBody();
			user.setFollowed(status);
		}
		ResponseEntity<SearchedUserModelList> response =
				new ResponseEntity<SearchedUserModelList>(list, HttpStatus.OK);
		return response;
	}
}
