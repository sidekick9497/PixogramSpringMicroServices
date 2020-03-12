package com.sidekick.pixogram.actionservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Primary;

@Entity
@Table(name = "mediaActions")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Actions {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "media")
	private Integer mediaId;

	@Column(name="user")
	private Integer userId;

	@Column(name = "liked")
	private Boolean liked;


}
