package com.sidekick.pixogram.followservice.repository;

import com.sidekick.pixogram.followservice.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
@Service
public interface IFollowRepository extends JpaRepository<Follow,Integer> {
    public Boolean existsByFollowerIdAndFolloweeId(Integer followerId, Integer followeeId);
    public int removeByFollowerIdAndFolloweeId(Integer followerId, Integer followeeId);
    public Follow getByFollowerIdAndFolloweeId(Integer followerId,Integer followeeId);

    public List<Follow> getByFollowerId(Integer followerId);
    public List<Follow> getByFolloweeId(Integer followeeId);
}

