package com.sidekick.pixogram.userservice.service;

import java.util.List;
import java.util.Optional;

import com.sidekick.pixogram.userservice.entity.Users;
import com.sidekick.pixogram.userservice.model.UserModel;


public interface IUserServices {
	
	public List<Users> getall();
	public void saveuser(UserModel userInput);
	public Optional<Users> getWithId(Integer id);
	public void updateuser(UserModel action);
}
