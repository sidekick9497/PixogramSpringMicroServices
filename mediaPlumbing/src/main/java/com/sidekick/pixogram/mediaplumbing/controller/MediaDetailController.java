package com.sidekick.pixogram.mediaplumbing.controller;

import com.netflix.ribbon.proxy.annotation.Http;
import com.sidekick.pixogram.mediaplumbing.feignproxy.ActionServiceProxy;
import com.sidekick.pixogram.mediaplumbing.feignproxy.MediaServiceProxy;
import com.sidekick.pixogram.mediaplumbing.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@RestController
public class MediaDetailController {

    private final MediaServiceProxy mediaServiceProxy;
    @Autowired
    private final ActionServiceProxy actionServiceProxy;

    @Autowired
    public MediaDetailController(MediaServiceProxy mediaServiceProxy,ActionServiceProxy actionServiceProxy) {
        this.mediaServiceProxy = mediaServiceProxy;
        this.actionServiceProxy = actionServiceProxy ;
    }

    @GetMapping("/test")
    public String test(){
        return "test result";
    }

    @GetMapping("/media-feign/media")
    public ResponseEntity<MediaList> getMedia(HttpServletRequest request)
    {
        Integer userId = Integer.parseInt(request.getHeader("userId"));
      MediaList mediaList = new MediaList();
        List<MediaDetailModel> records = this.mediaServiceProxy.getAllMedia().getBody().getMedialist();
        for(MediaDetailModel record: records) {
            MediaActionCount mediaActionCount = this.actionServiceProxy.getLikes(record.getId(),userId);
            record.addActions(mediaActionCount);
        }
        mediaList.setMedialist(records);
        return new ResponseEntity<MediaList>(mediaList,HttpStatus.OK);
     // return this.mediaServiceProxy.getAllMedia();

    }
    @GetMapping("/media-feign/oneMedia/{mediaId}")
    public ResponseEntity<MediaDetailModel> getMediaDetail(@PathVariable Integer mediaId,HttpServletRequest request)
    {
        Integer userId = Integer.parseInt(request.getHeader("userId"));
        Media media = mediaServiceProxy.mediaDetailsById(mediaId).getBody();
        MediaDetailModel mediaDetailModel = MediaDetailModel.fromMedia(media);
        MediaActionCount mediaActionCount = this.actionServiceProxy.getLikes(mediaId,userId);
        mediaDetailModel.addActions(mediaActionCount);
        ResponseEntity<MediaDetailModel> response = new ResponseEntity<>(mediaDetailModel, HttpStatus.OK);
        return  response;
    }
    @PostMapping("media-fiegn/media/saveMediaAndDetails")
    public ResponseEntity<Boolean> saveMediaDetails(@RequestParam("title") String title,@RequestParam("description") String description ,
                                                    @RequestParam("imageType") String imageType, @RequestParam("mediaFile") MultipartFile mediaFile,
                                                    @RequestParam("tags") String tags,@RequestParam("userId") String userId)
    {

        MediaDetails media = new MediaDetails();
        media.setTitle(title);
        media.setDescription(description);
        media.setType(imageType);
        media.setTags(tags);
        System.out.println("userId is " + userId +" type is " + imageType);
        media.setUserId(Integer.parseInt(userId));

        //media.setUserId();
        media.setFileUrl(mediaFile.getOriginalFilename());
        ResponseEntity<Boolean> mediaResult = this.mediaServiceProxy.saveMedia(mediaFile);
        System.out.println("called save Media Details");
        ResponseEntity<Media> result =  this.mediaServiceProxy.saveMediaDetails(media);



        return new ResponseEntity<Boolean>(true,HttpStatus.OK);
    }
    @PostMapping(value = "/media-feign/media/save",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Boolean> saveMedia( MultipartFile mediaFile)
    {
        System.out.println("in Save method");
        System.out.println(mediaFile.getOriginalFilename());
        ResponseEntity<Boolean> result = this.mediaServiceProxy.saveMedia(mediaFile);
        return result;

    }

}
