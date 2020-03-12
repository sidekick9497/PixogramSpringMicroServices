package com.sidekick.pixogram.actionservice.service;

import java.util.Optional;

import com.sidekick.pixogram.actionservice.entity.Actions;
import com.sidekick.pixogram.actionservice.model.ActionData;
import com.sidekick.pixogram.actionservice.model.ActionList;
import org.springframework.stereotype.Service;

@Service
public interface IActionService {
	
	public ActionList getall();
	public Actions save(Integer mediaId,Integer userId, boolean liked);
	public Optional<Actions> getWithId(Integer id);
	public void updateuser(ActionData action);

}
