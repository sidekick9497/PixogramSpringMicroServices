package com.sidekick.pixogram.mediaplumbing.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class Media {
			
		private Integer id;
		private Integer userId;
		private String title;
		private String description;
		private String tags;
		private String mimeType;
		private String fileUrl;
	}

