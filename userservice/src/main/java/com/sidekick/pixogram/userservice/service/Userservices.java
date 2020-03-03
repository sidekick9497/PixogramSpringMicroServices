package com.sidekick.pixogram.userservice.service;

import java.util.List;
import java.util.Optional;

import com.sidekick.pixogram.userservice.repository.AuthorityRepository;
import com.sidekick.pixogram.userservice.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sidekick.pixogram.userservice.entity.Authorities;
import com.sidekick.pixogram.userservice.entity.Users;
import com.sidekick.pixogram.userservice.model.UserModel;

@Service
public class Userservices implements IUserServices{

	@Autowired
	private UsersRepository userRepository;
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
	public List<Users> getall(){
		List<Users> records=this.userRepository.findAll();
		return records;
		
	}
	public Optional<Users> getWithId(Integer id){
		Optional<Users> record= this.userRepository.findById(id);
		return record;
		
	}
	
	public void  saveuser(UserModel user) {
		Users data = new Users();
		//Authorities auth = new Authorities();
		//auth.setUsername(user.getUsername());
		//auth.setAuthority("ROLE_USER");
		// data.setId(user.getId());
		data.setUserName(user.getUsername());
		data.setFirstName(user.getFirstName());
		data.setLastName(user.getLastName());
		data.setEmail(user.getEmail());
		data.setDob(user.getDob());
		data.setPassword("{noop}" + user.getPassword());
		//data.setProfile(user.getProfile());
		data.setEnabled(true);
		this.userRepository.save(data);
		// add authority
		Authorities role = new Authorities(user.getUsername(), "ROLE_USER");
		this.authorityRepository.save(role);
	}
	
	public void updateuser(UserModel user) {
		Users data = new Users();
		//Authorities auth = new Authorities();
		//auth.setUsername(user.getUsername());
		//auth.setAuthority("ROLE_USER");
		
		data.setUserName(user.getUsername());
		data.setFirstName(user.getFirstName());
		data.setLastName(user.getLastName());
		data.setEmail(user.getEmail());
		data.setDob(user.getDob());
		data.setPassword(user.getPassword());
		data.setProfile(user.getProfilePicUrl());
		data.setEnabled(true);
		this.userRepository.save(data);
	}
}
