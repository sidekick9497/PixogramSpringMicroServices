package com.sidekick.pixogram.mediaplumbing.controller;

import com.sidekick.pixogram.mediaplumbing.feignproxy.FollowServiceProxy;
import com.sidekick.pixogram.mediaplumbing.feignproxy.UserServiceProxy;
import com.sidekick.pixogram.mediaplumbing.models.SearchedUserModel;
import com.sidekick.pixogram.mediaplumbing.models.SearchedUserModelList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class miscController {
    @Autowired
    private FollowServiceProxy followServiceProxy;
    @Autowired
    private UserServiceProxy userServiceProxy;

    @GetMapping("/search/{userText}")
    public ResponseEntity<SearchedUserModelList> getSearchedUser(HttpServletRequest request, @PathVariable String userText)
    {
        Integer userId = Integer.parseInt(request.getHeader("userId"));
        List<SearchedUserModel> searchedUserModelList =  this.userServiceProxy.getSearchedUsers(userText).getBody().getUserList();
        for(SearchedUserModel user: searchedUserModelList)
        {
           boolean check =  this.followServiceProxy.getFollowingStatus(userId,user.getUserId()).getBody();
           user.setFollowed(check);
           user.setProfileUrl(user.getProfileUrl());
        }
        SearchedUserModelList searchedUserModelListResponse = new SearchedUserModelList();
        searchedUserModelListResponse.setUserList(searchedUserModelList);
        return new ResponseEntity<>(searchedUserModelListResponse, HttpStatus.OK);
    }

}
