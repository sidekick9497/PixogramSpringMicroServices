package com.sidekick.pixogram.userservice.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Value;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name ="username")
	private String userName;

	@Column
	private String password;

	@Column
	private String email;

	@Column(name ="firstname")
	private String firstName;

	@Column(name ="lastname")
	private String lastName;

	@Column
	private LocalDate dob;
	
	@Column
	private String profile;

	@Column(name = "createdon")
	@CreationTimestamp
	private LocalDateTime createdOn;

	@Column(name = "updatedon")
	@UpdateTimestamp
	private LocalDateTime updatedOn;

	@Column
	private Boolean enabled;
}
