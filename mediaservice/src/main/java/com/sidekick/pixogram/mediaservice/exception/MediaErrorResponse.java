package com.sidekick.pixogram.mediaservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MediaErrorResponse {

	private String message;
	private Integer errorCode;
	private Long timeStamp;
	
	
	
	
}
