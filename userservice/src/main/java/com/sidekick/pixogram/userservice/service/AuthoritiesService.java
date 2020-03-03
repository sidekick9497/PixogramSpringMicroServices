package com.sidekick.pixogram.userservice.service;

import com.sidekick.pixogram.userservice.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sidekick.pixogram.userservice.entity.Authorities;

@Service
public class AuthoritiesService {
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
	public void saveauthority(Authorities role) {
		this.authorityRepository.save(role);
	}

}
