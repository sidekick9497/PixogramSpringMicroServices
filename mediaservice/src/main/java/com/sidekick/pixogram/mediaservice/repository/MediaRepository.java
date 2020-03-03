package com.sidekick.pixogram.mediaservice.repository;

import com.sidekick.pixogram.mediaservice.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<Media, Integer> {

}
