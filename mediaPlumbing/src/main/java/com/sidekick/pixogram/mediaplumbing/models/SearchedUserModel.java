package com.sidekick.pixogram.mediaplumbing.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchedUserModel {
	private Integer userId;
	private String name;
	private String profileUrl;
	private Boolean followed;
}
