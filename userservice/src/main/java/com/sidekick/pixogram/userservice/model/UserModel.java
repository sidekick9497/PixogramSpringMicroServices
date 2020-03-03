package com.sidekick.pixogram.userservice.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
	
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private LocalDate dob;
	private String profilePicUrl;
	
}