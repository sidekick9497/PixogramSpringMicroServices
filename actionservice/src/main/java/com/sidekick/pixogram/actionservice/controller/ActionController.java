package com.sidekick.pixogram.actionservice.controller;

import com.sidekick.pixogram.actionservice.entity.Actions;
import com.sidekick.pixogram.actionservice.model.ActionData;
import com.sidekick.pixogram.actionservice.model.MediaActionCount;
import com.sidekick.pixogram.actionservice.service.ActionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sidekick.pixogram.actionservice.exception.ActionErrorResponse;

import javax.swing.*;

@RestController
public class ActionController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ActionService actionService;

	@GetMapping("/getCounts/{mediaId}/userId/{userId}")
	public  ResponseEntity<MediaActionCount> getLikes(@PathVariable  Integer mediaId,@PathVariable Integer userId){
		//to be implemented
		//ActionList actionList = new ActionList();
		//MediaActionCount count = this.actionService.getWithId(mediaId);
		System.out.println(mediaId);
		MediaActionCount mediaActionCount = this.actionService.getCountWithMediaId(mediaId,userId);
		return new  ResponseEntity<MediaActionCount>(mediaActionCount,HttpStatus.OK);
	}

	@PostMapping("like/mediaId/{mediaId}/userId/{userId}")
	public ResponseEntity<ActionData> likeMedia(@PathVariable Integer mediaId, @PathVariable Integer userId)
	{
		Actions actions = this.actionService.save(mediaId,userId,true);
		ActionData actionData = ActionData.fromActions(actions);
		return new ResponseEntity<>(actionData,HttpStatus.OK);

	}
	@PostMapping("dislike/mediaId/{mediaId}/userId/{userId}")
	public ResponseEntity<ActionData> unlikeMedia(@PathVariable Integer mediaId, @PathVariable Integer userId)
	{
		Actions actions = this.actionService.save(mediaId,userId,false);
		ActionData actionData = ActionData.fromActions(actions);
		return new ResponseEntity<>(actionData,HttpStatus.OK);
	}
	@ExceptionHandler  // ~catch
	public ResponseEntity<ActionErrorResponse> productOperationErrorHAndler(Exception ex) {
		// create error object
		ActionErrorResponse error = new ActionErrorResponse(ex.getMessage(), 
															  HttpStatus.BAD_REQUEST.value(), 
															  System.currentTimeMillis());
		ResponseEntity<ActionErrorResponse> response =
										new ResponseEntity<ActionErrorResponse>(error, HttpStatus.NOT_FOUND);
		logger.error("Exception :" + error);
		
		return response;
	}
	
}
