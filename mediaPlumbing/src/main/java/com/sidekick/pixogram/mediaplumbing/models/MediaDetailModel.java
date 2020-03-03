package com.sidekick.pixogram.mediaplumbing.models;

import com.sun.xml.internal.bind.v2.TODO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class MediaDetailModel {

	private Integer id;
	private Integer userId;
	private String title;
	private String description;
	private String tags;
	//private String type;
	private String fileURL;
	private String[] comments = {"good","nice"};

	public void addMedia(Media media) {

		this.id = media.getId();
		this.userId = media.getUserId();
		this.title = media.getTitle();
		this.description = media.getDescription();
		this.tags = media.getTags();
		//mediaDetailModel.type = media.getType();
		this.fileURL = media.getFileURL();
	}
	public  void addComments(List<Object> comments)
	{
		//not implemented
	}


}
