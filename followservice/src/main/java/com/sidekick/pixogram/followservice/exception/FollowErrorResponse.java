package com.sidekick.pixogram.followservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FollowErrorResponse {

	private String message;
	private Integer errorCode;
	private Long timeStamp;
	
	
	
	
}
