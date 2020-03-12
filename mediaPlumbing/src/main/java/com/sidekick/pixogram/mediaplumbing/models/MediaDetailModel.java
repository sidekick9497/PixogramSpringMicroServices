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
	private String type;
	private String fileUrl;
	private String[] comments = {"good","nice"};
	private Integer likedCount = 0;
	private Integer dislikedCount = 0;
	private boolean liked;
	private boolean disliked;

	public static MediaDetailModel fromMedia(Media media) {
		MediaDetailModel mediaDetail = new MediaDetailModel();
		mediaDetail.id = media.getId();
		mediaDetail.userId = media.getUserId();
		mediaDetail.title = media.getTitle();
		mediaDetail.description = media.getDescription();
		mediaDetail.tags = media.getTags();
		mediaDetail.type = media.getMimeType();
		mediaDetail.fileUrl = media.getFileUrl();
		return mediaDetail;
	}
	public  void addActions(MediaActionCount mediaActionCount)
	{
		this.setLikedCount(mediaActionCount.getLikedCount());
		this.setDislikedCount(mediaActionCount.getDisLikedCount());
		this.setLiked(mediaActionCount.isLiked());
		this.setDisliked(mediaActionCount.isDisliked());
	}


}
