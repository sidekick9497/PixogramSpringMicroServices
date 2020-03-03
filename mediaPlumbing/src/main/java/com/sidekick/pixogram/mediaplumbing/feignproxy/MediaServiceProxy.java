package com.sidekick.pixogram.mediaplumbing.feignproxy;

import com.sidekick.pixogram.mediaplumbing.models.Media;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClientName;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "api-gateway")
@RibbonClient(name = "media-service")
public interface MediaServiceProxy
{
    @GetMapping("media-service/media/{movieId}")
    public ResponseEntity<Media> mediaDetail(@PathVariable Integer movieId);

    @GetMapping("media-service/media/all")
    public ResponseEntity<List<Media>> getAllMedia();
}