package com.sidekick.pixogram.miscplumbing.feignproxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sidekick.pixogram.miscplumbing.model.SearchedUserModelList;



@FeignClient(name ="api-gateway")
@RibbonClient(name ="user-service")
public interface UserServiceProxy {
	@GetMapping("/user-service/search-users/{searchText}")
	public ResponseEntity<SearchedUserModelList> getSearchedUsers(@PathVariable String searchText);
}







