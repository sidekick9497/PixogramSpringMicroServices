package com.sidekick.pixogram.mediaplumbing.controller;

import com.sidekick.pixogram.mediaplumbing.feignproxy.MediaServiceProxy;
import com.sidekick.pixogram.mediaplumbing.models.Media;
import com.sidekick.pixogram.mediaplumbing.models.MediaDetailModel;
import com.sidekick.pixogram.mediaplumbing.models.MediaList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class MediaDetailController {

    private final MediaServiceProxy mediaServiceProxy;

    @Autowired
    public MediaDetailController(MediaServiceProxy mediaServiceProxy) {
        this.mediaServiceProxy = mediaServiceProxy;
    }

    @GetMapping("/test")
    public String test(){
        return "test result";
    }

    @GetMapping("/media-feign/media")
    public ResponseEntity<MediaList> getMedia()
    {
      MediaList mediaList = new MediaList();
        List<Media> records = this.mediaServiceProxy.getAllMedia().getBody().getMedialist();
        mediaList.setMedialist(records);
        return new ResponseEntity<MediaList>(mediaList,HttpStatus.OK);
     // return this.mediaServiceProxy.getAllMedia();

    }
    @GetMapping("/media-feign/oneMedia/{mediaId}")
    public ResponseEntity<MediaDetailModel> getMediaDetail(@PathVariable Integer mediaId)
    {
        Media media = mediaServiceProxy.mediaDetailsById(mediaId).getBody();
        MediaDetailModel mediaDetailModel = MediaDetailModel.fromMedia(media);
        ResponseEntity<MediaDetailModel> response = new ResponseEntity<>(mediaDetailModel, HttpStatus.OK);
        return  response;
    }
    @PostMapping(value = "/media-feign/media/save")
    public ResponseEntity<Boolean> saveMedia(MultipartFile mediaFile)
    {
        System.out.println(mediaFile.getOriginalFilename());
        ResponseEntity<Boolean> result = this.mediaServiceProxy.saveMedia(mediaFile);
        return result;

    }

}
