package com.sidekick.pixogram.mediaplumbing.models;

import lombok.*;

import java.io.Serializable;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Follow {
	
	/**
	 * 
	 */



	private Integer id;


	private Integer followerId;
	

	private Integer followeeId;
}
