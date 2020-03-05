package com.sidekick.pixogram.mediaservice.controller;

import com.sidekick.pixogram.mediaservice.entity.Media;
import com.sidekick.pixogram.mediaservice.exception.MediaNotFoundException;
import com.sidekick.pixogram.mediaservice.service.MediaService;
import com.sidekick.pixogram.mediaservice.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sidekick.pixogram.mediaservice.exception.MediaErrorResponse;
import com.sidekick.pixogram.mediaservice.model.MediaDetails;
import com.sidekick.pixogram.mediaservice.model.MediaList;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;


@RestController
public class MediaController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MediaService mediaService;

	@Autowired
	private StorageService storageService;

	@GetMapping("/media")
	public ResponseEntity<MediaList> getAllMedia(){
		List<Media> allMedia = this.mediaService.getAll();
		MediaList medialist = new MediaList();
		medialist.setMedialist(allMedia);
		ResponseEntity<MediaList> result = new ResponseEntity<MediaList>(medialist, HttpStatus.OK);
		return result;

	}

	@PostMapping("/media/saveDetails")
	public  ResponseEntity<MediaDetails> saveMediaDetails(@RequestBody MediaDetails mediaDetails)
	{
		this.mediaService.save(mediaDetails);
		return new ResponseEntity<MediaDetails>(mediaDetails,HttpStatus.OK);


	}
	@PutMapping("/media/saveDetails")
	public boolean updateMediaDetails(@RequestBody MediaDetails mediaDetails ) {

		this.mediaService.save(mediaDetails);
		return true;

	}
	@PostMapping("/media/saveImage")
	public ResponseEntity<Boolean> save(@RequestParam("file")MultipartFile mediaFile) {

		this.storageService.store(mediaFile);
		return new ResponseEntity<>(true, HttpStatus.OK);
	}
	@GetMapping("/media/{mediaId}")
	public ResponseEntity<MediaDetails> mediaDetailsById(@PathVariable Integer mediaId){
		MediaDetails mediaDetails= new MediaDetails();
		Media media = new Media();
		Optional<Media>  record = this.mediaService.getWithId(mediaId);
		if(record.isPresent())
		{
			media = record.get();
		}
		else
		{
			throw new MediaNotFoundException("media not found");
		}
		mediaDetails.addMedia(media);

		ResponseEntity<MediaDetails> result = new ResponseEntity<MediaDetails>(mediaDetails, HttpStatus.OK);
		return result;
	}

	@GetMapping("/media/userName/{userId}")
	public ResponseEntity<MediaList> getMediaByUserId(@PathVariable Integer userId)
	{
		MediaList mediaList = this.mediaService.getMediaByUserId(userId);
		ResponseEntity<MediaList> response = new ResponseEntity<>(mediaList, HttpStatus.OK);
		return response;
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