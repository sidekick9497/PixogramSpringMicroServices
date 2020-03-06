package com.pixogram.actionservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.pixogram.actionservice.model.ActionList;
import org.springframework.beans.factory.annotation.Autowired;

import com.pixogram.actionservice.entity.Actions;
import com.pixogram.actionservice.repository.ActionsRepository;
import com.pixogram.actionservice.model.ActionData;

public class ActionService implements IActionService {
	
	@Autowired
	private ActionsRepository actionRepository;
	
	public ActionList getall(){
		List<Actions> records =this.actionRepository.findAll();
		List<ActionData> actionData = new ArrayList<>();
		actionData = records.stream().map((actions -> ActionData.fromActions(actions))).collect(Collectors.toList());
		ActionList actionList = new ActionList();
		actionList.setActionList(actionData);
		return actionList;

	}
	
	public void save(ActionData action) {
		Actions actions = ActionData.toActions(action);
		this.actionRepository.save(actions);
		
	}
	
	public Optional<Actions> getWithId(Integer id){
		Optional<Actions> record= this.actionRepository.findById(id);
		return record;
		
	}
	
	public void updateuser(ActionData action) {
		Actions data = ActionData.toActions(action);
		this.actionRepository.save(data);
	}

}
