package com.sidekick.pixogram.actionservice.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.sidekick.pixogram.actionservice.model.ActionList;
import com.sidekick.pixogram.actionservice.model.MediaActionCount;
import com.sidekick.pixogram.actionservice.entity.Actions;
import org.springframework.beans.factory.annotation.Autowired;

import com.sidekick.pixogram.actionservice.repository.ActionsRepository;
import com.sidekick.pixogram.actionservice.model.ActionData;
import org.springframework.stereotype.Service;

@Service
public class ActionService implements IActionService {
	
	@Autowired
	private ActionsRepository actionRepository;
	
	public ActionList getall(){
		List<Actions> records =this.actionRepository.findAll();
		//List<ActionData> actionData;
		List<ActionData> actionData = records.stream().map((actions -> ActionData.fromActions(actions))).collect(Collectors.toList());
		ActionList actionList = new ActionList();
		actionList.setActionList(actionData);
		return actionList;

	}
	@Override
	public Actions save(Integer mediaId, Integer userId,boolean liked) {
		Actions actions = new Actions();
		// check whether the user has already liked or disliked
		if(this.actionRepository.existsByMediaIdAndUserId(mediaId,userId))
		{
			//if liked/Disliked get the action from database and update
			actions = this.actionRepository.findByMediaIdAndUserId(mediaId,userId);
			//if user has liked the already liked media /or/ disliked the already disliked media remove entry
			if(actions.getLiked() == liked)
			{
				this.actionRepository.removeByMediaIdAndUserId(mediaId,userId);
			}
			else
			{	// update the like/disliked only
				actions.setLiked(liked);
				this.actionRepository.save(actions);
			}


		}
		else
		{
			actions = new Actions();
			actions.setMediaId(mediaId);
			actions.setUserId(userId);
			actions.setLiked(liked);
			this.actionRepository.save(actions);

		}
		return  actions;

	}
	
	public Optional<Actions> getWithId(Integer id){
		Optional<Actions> record= this.actionRepository.findById(id);
		return record;
		
	}
	public MediaActionCount getCountWithMediaId(Integer mediaId, Integer userId)
	{
		Integer likedCount = this.actionRepository.findAllByMediaIdAndLikedIsTrue(mediaId).size();
		Integer disLikedCount = this.actionRepository.findAllByMediaIdAndLikedIsFalse(mediaId).size();
		MediaActionCount mediaActionCount = new MediaActionCount();
		if(this.actionRepository.existsByMediaIdAndUserId(mediaId,userId))
		{	Actions actions = this.actionRepository.findByMediaIdAndUserId(mediaId,userId);
			mediaActionCount.setLiked(actions.getLiked());
			mediaActionCount.setDisliked(!actions.getLiked());
		}

		mediaActionCount.setLikedCount(likedCount);
		mediaActionCount.setDisLikedCount(disLikedCount);

		return  mediaActionCount;


	}
	public void updateuser(ActionData action) {
		Actions data = ActionData.toActions(action);
		this.actionRepository.save(data);
	}

}
