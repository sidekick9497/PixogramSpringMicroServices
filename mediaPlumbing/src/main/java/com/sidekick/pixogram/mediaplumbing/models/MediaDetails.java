package com.sidekick.pixogram.mediaplumbing.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MediaDetails {
		
	private Integer id;
	private Integer userId;
	private String title;
	private String description;
	private String tags;
	private String type;
	private String fileUrl;

	public void addMedia(Media media)
	{
		this.id = media.getId();
		this.userId = media.getUserId();
		this.title = media.getTitle();
		this.description = media.getDescription();
		this.type = media.getMimeType();
		this.fileUrl = media.getFileUrl();
	}
}
