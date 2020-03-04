package com.sidekick.pixogram.mediaservice.controller;

import java.util.Optional;

import com.sidekick.pixogram.mediaservice.exception.MediaNotFoundException;
import com.sidekick.pixogram.mediaservice.service.MediaService;
import com.sidekick.pixogram.mediaservice.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sidekick.pixogram.mediaservice.entity.Media;
import com.sidekick.pixogram.mediaservice.exception.MediaErrorResponse;
import com.sidekick.pixogram.mediaservice.model.MediaData;
import com.sidekick.pixogram.mediaservice.model.MediaModel;
import com.sidekick.pixogram.mediaservice.service.IMediaService;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class MediaController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MediaService mediaService;

	@Autowired
	private StorageService storageService;
	@GetMapping("/media")
	public ResponseEntity<MediaModel> getall(){
		MediaModel medialist = new MediaModel();
		medialist.setMedialist(this.mediaService.getall());
		ResponseEntity<MediaModel> result = new ResponseEntity<MediaModel>(medialist, HttpStatus.OK);
		return result;

	}


	@PostMapping("/media")
	public ResponseEntity<Boolean> save(@RequestParam("file")MultipartFile mediaFile) {
		//incomplete imelementation need to add  more fields to update data for media file
		//this.mediaService.save(media);
		this.storageService.store(mediaFile);
		return new ResponseEntity<>(true, HttpStatus.OK);
	}
	@GetMapping("/media/{mediaId}")
	public ResponseEntity<MediaData> getById(@PathVariable Integer mediaId){
		MediaData data = new MediaData();
		Media record = new Media();
		Optional<Media> media = this.mediaService.getWithId(mediaId);
		if(media.isPresent())
			record = media.get();
		else {
			throw new MediaNotFoundException("Media not found");
		}
		data.setId(record.getId());
		data.setUserId(record.getUserId());
		data.setTitle(record.getTitle());
		data.setTags(record.getTags());
		data.setDescription(record.getDescription());
		data.setType(record.getMimeType());
		data.setFile(record.getFileUrl());
		ResponseEntity<MediaData> result = new ResponseEntity<MediaData>(data, HttpStatus.OK);
		return result;
	}
	
	
	//update user
	@PutMapping("/media")
	public boolean update(@RequestBody MediaData media ) {
		
		this.mediaService.updateuser(media);
		return true;
		
	}
	@ExceptionHandler  // ~catch
	public ResponseEntity<MediaErrorResponse> productOperationErrorHAndler(Exception ex) {
		// create error object
		MediaErrorResponse error = new MediaErrorResponse(ex.getMessage(), 
															  HttpStatus.BAD_REQUEST.value(), 
															  System.currentTimeMillis());
		ResponseEntity<MediaErrorResponse> response =
										new ResponseEntity<MediaErrorResponse>(error, HttpStatus.NOT_FOUND);
		logger.error("Exception :" + error);
		
		return response;
	}
}