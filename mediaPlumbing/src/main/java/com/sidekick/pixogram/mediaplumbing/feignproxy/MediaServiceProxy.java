package com.sidekick.pixogram.mediaplumbing.feignproxy;

import com.sidekick.pixogram.mediaplumbing.models.Media;
import com.sidekick.pixogram.mediaplumbing.models.MediaList;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClientName;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.MimeType;
import java.util.List;

@FeignClient(name = "api-gateway",url = "http://localhost:8765/")
@Service
public interface MediaServiceProxy
{

    @GetMapping("media-service/media")
    public ResponseEntity<MediaList> getAllMedia();

    @PostMapping("media-service/media/saveDetails")
    ResponseEntity<Media>   saveMediaDetails(@RequestBody Media mediaDetails);

    @PutMapping("media-service/media/saveDetails")
    boolean  updateMediaDetails(@RequestBody Media mediaDetails);

    @PostMapping(value = "media-service/media/saveImage", headers = "Content-Type= multipart/form-data",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<Boolean> saveMedia(@RequestParam("file")MultipartFile mediaFile);

    @GetMapping("media-service/media/{mediaId}")
    ResponseEntity<Media> mediaDetailsById(@PathVariable Integer mediaId);

    @GetMapping("media-service/media/userName/{userId")
    ResponseEntity<MediaList> mediaDetailsByUserId(@PathVariable Integer userId);








}