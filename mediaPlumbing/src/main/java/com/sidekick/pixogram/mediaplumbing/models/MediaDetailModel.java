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

	public static MediaDetailModel fromMedia(Media media) {
		MediaDetailModel mediaDetail = new MediaDetailModel();
		mediaDetail.id = media.getId();
		mediaDetail.userId = media.getUserId();
		mediaDetail.title = media.getTitle();
		mediaDetail.description = media.getDescription();
		mediaDetail.tags = media.getTags();
		//mediaDetailModel.type = media.getType();
		mediaDetail.fileURL = media.getFileURL();
		return mediaDetail;
	}
	public  void addComments(List<Object> comments)
	{
		//not implemented
	}


}
