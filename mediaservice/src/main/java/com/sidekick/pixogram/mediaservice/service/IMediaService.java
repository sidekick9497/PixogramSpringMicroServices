package com.sidekick.pixogram.mediaservice.service;

import java.util.List;
import java.util.Optional;

import com.sidekick.pixogram.mediaservice.entity.Media;
import com.sidekick.pixogram.mediaservice.model.MediaData;

public interface IMediaService {
	
	public List<Media> getall();
	public void save(MediaData action);
	public Optional<Media> getWithId(Integer id);
	public void updateuser(MediaData action);
}
