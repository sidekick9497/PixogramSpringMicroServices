package com.sidekick.pixogram.followservice.controller;

import com.sidekick.pixogram.followservice.entity.Follow;
import com.sidekick.pixogram.followservice.model.FollowData;
import com.sidekick.pixogram.followservice.model.FollowList;
import com.sidekick.pixogram.followservice.repository.IFollowRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class FollowController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IFollowRepository followRepository;

	/*@Autowired
	private IFollowService followService;
	*/
	@GetMapping("/check-followings/mine/{followerId}/other/{followeeId}")
	public ResponseEntity<Boolean> getFollowingStatus(@PathVariable Integer followerId, @PathVariable Integer followeeId) {

		Boolean status = this.followRepository.existsByFollowerIdAndFolloweeId(followerId, followeeId);
		ResponseEntity<Boolean> result = new ResponseEntity<Boolean>(status, HttpStatus.OK);
		return result;

	}
	/*
	@GetMapping("/follow")
	public ResponseEntity<FollowModel> getallfollows(){
		FollowModel data = new FollowModel();
		data.setFollowlist(this.followService.getall());
		ResponseEntity<FollowModel> result = new ResponseEntity<FollowModel>(data,HttpStatus.OK);
		return result;
		
	}*/

	@PostMapping("/follow/mine/{followerId}/other/{followeeId}")
	public Boolean save(@PathVariable Integer followerId, @PathVariable Integer followeeId) {

		if(this.followRepository.existsByFollowerIdAndFolloweeId(followerId,followeeId))
		{
			this.followRepository.removeByFollowerIdAndFolloweeId(followerId,followeeId);


		}
		else
		{
			Follow follow = new Follow();
			follow.setFollowerId(followerId);
			follow.setFolloweeId(followeeId);
			this.followRepository.save(follow);
		}

		return true;
	}
	@GetMapping("getFollowers/mineId/{followeeId}")
	public ResponseEntity<FollowList> getFollowerList(@PathVariable Integer followeeId)
	{
		List<Follow> followList = this.followRepository.getByFolloweeId(followeeId);

		FollowList followListResponse = new FollowList();
		followListResponse.setFollowlist(followList);
		return new ResponseEntity<>(followListResponse, HttpStatus.OK);

	}
	@GetMapping("getFollowees/mineId/{followerId}")
	public ResponseEntity<FollowList> getFolloweeList(@PathVariable Integer followerId)
	{
		List<Follow> followList = this.followRepository.getByFollowerId(followerId);

		FollowList followListResponse = new FollowList();
		followListResponse.setFollowlist(followList);
		return  new ResponseEntity<>(followListResponse,HttpStatus.OK);

	}
}
