package com.sidekick.pixogram.miscplumbing.feignproxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name ="api-gateway")
@RibbonClient(name ="follow-service")
public interface FollowServiceProxy {
	@GetMapping("/follow-service/check-followings/mine/{mineId}/other/{otherId}")
	public ResponseEntity<Boolean> getFollowingStatus(@PathVariable Integer mineId, @PathVariable Integer otherId);
}
