package com.sidekick.pixogram.mediaplumbing.feignproxy;


import com.sidekick.pixogram.mediaplumbing.models.ActionData;
import com.sidekick.pixogram.mediaplumbing.models.MediaActionCount;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "action-service",url = "http://localhost:7000/")
public interface ActionServiceProxy {

    @GetMapping("getCounts/{mediaId}/userId/{userId}")
    public MediaActionCount getLikes(@PathVariable Integer mediaId,@PathVariable Integer userId);

    @PostMapping("like/mediaId/{mediaId}/userId/{userId}")
    public ResponseEntity<ActionData> likeMedia(@PathVariable Integer mediaId, @PathVariable Integer userId);

    @PostMapping("dislike/mediaId/{mediaId}/userId/{userId}")
    public ResponseEntity<ActionData> unlikeMedia(@PathVariable Integer mediaId, @PathVariable Integer userId);
}
