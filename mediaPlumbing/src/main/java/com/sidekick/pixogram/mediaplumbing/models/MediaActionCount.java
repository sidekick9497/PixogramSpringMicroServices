package com.sidekick.pixogram.mediaplumbing.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MediaActionCount {

    private Integer likedCount;
    private Integer disLikedCount;
    private boolean liked;
    private boolean disliked;
}
