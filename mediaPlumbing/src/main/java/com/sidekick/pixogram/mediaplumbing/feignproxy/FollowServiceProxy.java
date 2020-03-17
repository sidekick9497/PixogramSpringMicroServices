package com.sidekick.pixogram.mediaplumbing.feignproxy;

import com.sidekick.pixogram.mediaplumbing.models.FollowList;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "follow-service",url = "http://localhost:7003/")
public interface FollowServiceProxy {

    @GetMapping("/check-followings/mine/{followerId}/other/{followeeId}")
    public ResponseEntity<Boolean> getFollowingStatus(@PathVariable Integer followerId, @PathVariable Integer followeeId);

    @PostMapping("/follow/mine/{followerId}/other/{followeeId}")
    public Boolean save(@PathVariable Integer followerId, @PathVariable Integer followeeId);

    @GetMapping("getFollowers/mineId/{followeeId}")
    public ResponseEntity<FollowList> getFollowerList(@PathVariable Integer followeeId);

    @GetMapping("getFollowees/mineId/{followerId}")
    public ResponseEntity<FollowList> getFolloweeList(@PathVariable Integer followerId);

}
