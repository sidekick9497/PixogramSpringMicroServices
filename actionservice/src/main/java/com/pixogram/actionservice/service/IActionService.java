package com.pixogram.actionservice.service;

import java.util.List;
import java.util.Optional;

import com.pixogram.actionservice.entity.Actions;
import com.pixogram.actionservice.model.ActionData;
import com.pixogram.actionservice.model.ActionList;
import org.springframework.stereotype.Service;

@Service
public interface IActionService {
	
	public ActionList getall();
	public void save(ActionData action);
	public Optional<Actions> getWithId(Integer id);
	public void updateuser(ActionData action);
	
}
