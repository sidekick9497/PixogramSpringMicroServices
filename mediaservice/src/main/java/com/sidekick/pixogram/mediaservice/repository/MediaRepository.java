package com.sidekick.pixogram.mediaservice.repository;

import com.sidekick.pixogram.mediaservice.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MediaRepository extends JpaRepository<Media, Integer> {

    public List<Media> findMediaByUserId(Integer userId);

}
