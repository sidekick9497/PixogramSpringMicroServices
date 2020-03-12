package com.sidekick.pixogram.actionservice.model;
import com.sidekick.pixogram.actionservice.entity.Actions;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ActionData {
	private Integer id;
	private Integer mediaId;
	private Boolean liked;
	private Integer userId;

	public static ActionData fromActions(Actions action)
	{
		ActionData actionData  = new ActionData();
		actionData.setId(action.getId());
		actionData.setMediaId(action.getMediaId());
		actionData.setLiked(action.getLiked());
		actionData.setUserId(action.getUserId());
		return actionData;
	}
	public static Actions toActions(ActionData actionData)
	{
		Actions action = new Actions();
		action.setMediaId(actionData.getMediaId());
		action.setLiked(actionData.getLiked());
		action.setUserId(actionData.getUserId());
		return action;
	}
}
