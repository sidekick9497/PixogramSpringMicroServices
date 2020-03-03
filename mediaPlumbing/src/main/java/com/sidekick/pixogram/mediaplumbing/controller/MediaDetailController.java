package com.sidekick.pixogram.mediaplumbing.controller;

import com.sidekick.pixogram.mediaplumbing.feignproxy.MediaServiceProxy;
import com.sidekick.pixogram.mediaplumbing.models.Media;
import com.sidekick.pixogram.mediaplumbing.models.MediaDetailModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MediaDetailController {

    @Autowired
    private MediaServiceProxy mediaServiceProxy;

    @GetMapping("/media-feign/oneMedia/{mediaId}")
    public ResponseEntity<MediaDetailModel> getMediaDetail(@PathVariable Integer mediaId)
    {
        Media media = mediaServiceProxy.mediaDetail(mediaId).getBody();
        MediaDetailModel mediaDetailModel = new MediaDetailModel();
        mediaDetailModel.addMedia(media);
        ResponseEntity<MediaDetailModel> response = new ResponseEntity<>(mediaDetailModel, HttpStatus.OK);
        return  response;
    }

}
