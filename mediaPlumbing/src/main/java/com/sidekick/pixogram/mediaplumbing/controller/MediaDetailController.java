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
    public List<Media> getMedia()
    {
       /* MediaList mediaList = new MediaList();
        List<Media> records = this.mediaServiceProxy.getAllMedia().getBody().getMediaList();
        mediaList.setMediaList(records);
        return new ResponseEntity<MediaList>(mediaList,HttpStatus.OK);*/
       return this.mediaServiceProxy.getAllMedia().getBody().getMediaList();
    }
    @GetMapping("/media-feign/oneMedia/{mediaId}")
    public ResponseEntity<MediaDetailModel> getMediaDetail(@PathVariable Integer mediaId)
    {
        Media media = mediaServiceProxy.mediaDetailsById(mediaId).getBody();
        MediaDetailModel mediaDetailModel = new MediaDetailModel();
        mediaDetailModel.addMedia(media);
        ResponseEntity<MediaDetailModel> response = new ResponseEntity<>(mediaDetailModel, HttpStatus.OK);
        return  response;
    }
    @PostMapping("/media-feign/media/save")
    public ResponseEntity<Boolean> saveMedia(@RequestParam("file")MultipartFile file)
    {
        return mediaServiceProxy.saveMedia(file);
    }

}
