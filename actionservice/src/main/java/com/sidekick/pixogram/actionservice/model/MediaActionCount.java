package com.sidekick.pixogram.actionservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MediaActionCount {

    private Integer likedCount;
    private Integer disLikedCount;
    private boolean liked;
    private boolean disliked;
}
