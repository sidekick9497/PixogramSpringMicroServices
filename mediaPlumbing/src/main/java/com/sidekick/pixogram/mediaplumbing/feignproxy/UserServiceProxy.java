package com.sidekick.pixogram.mediaplumbing.feignproxy;

import com.sidekick.pixogram.mediaplumbing.models.SearchedUserModelList;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service",url = "http://localhost:7006/")
public interface UserServiceProxy {
    @GetMapping("/search-users/{searchText}")
    public ResponseEntity<SearchedUserModelList> getSearchedUsers(@PathVariable String searchText);
}
