package com.sidekick.pixogram.followservice.entity;

import java.io.Serializable;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "follow")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Follow implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private Integer followerId;
	
	@Column
	private Integer followeeId;
}
