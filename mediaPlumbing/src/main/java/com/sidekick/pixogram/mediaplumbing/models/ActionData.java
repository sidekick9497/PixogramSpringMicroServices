package com.sidekick.pixogram.mediaplumbing.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ActionData {
	private Integer id;
	private Integer mediaId;
	private Boolean liked;
	private Integer userId;
}
