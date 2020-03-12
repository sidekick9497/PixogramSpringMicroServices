package com.sidekick.pixogram.mediaplumbing.feignproxy;


import com.sidekick.pixogram.mediaplumbing.models.MediaActionCount;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "action-service",url = "http://localhost:7000/")
public interface ActionServiceProxy {

    @GetMapping("getCounts/{mediaId}/userId/{userId}")
    public MediaActionCount getLikes(@PathVariable Integer mediaId,@PathVariable Integer userId);

}
