package com.sidekick.pixogram.mediaservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sidekick.pixogram.mediaservice.entity.Media;
import com.sidekick.pixogram.mediaservice.model.MediaData;
import com.sidekick.pixogram.mediaservice.repository.MediaRepository;


@Service
public class MediaService {
	
	@Autowired
	private MediaRepository mediaRepository;
	
	public List<Media> getall() {
		List<Media> records = new ArrayList<Media>();
		records = this.mediaRepository.findAll();
		return records;	
	}
	
	public void save(MediaData media) {
		Media data = new Media();
		data.setUserId(media.getUserId());
		data.setTitle(media.getTitle());
		data.setDescription(media.getDescription());
		data.setFileUrl(media.getFile());
		data.setTags(media.getTags());
		data.setMimeType(media.getType());
		
		this.mediaRepository.save(data);
		
	}
	
	public Optional<Media> getWithId(Integer id){
		Optional<Media> record= this.mediaRepository.findById(id);
		return record;
		
	}
	
	public void updateuser(MediaData media) {
		Media data = new Media();
		data.setUserId(media.getUserId());
		data.setId(media.getId());
		data.setTitle(media.getTitle());
		data.setDescription(media.getDescription());
		data.setTags(media.getTags());
		data.setMimeType(media.getType());
		data.setFileUrl(media.getFile());
		this.mediaRepository.save(data);
	}
}
