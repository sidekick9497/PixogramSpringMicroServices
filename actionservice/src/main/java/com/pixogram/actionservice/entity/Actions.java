package com.pixogram.actionservice.entity;

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
@Table(name = "media-actions")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Actions {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private Integer mediaId;

	@Column
	private Integer userId;

	@Column
	private Boolean liked;


}
