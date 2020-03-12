// AUTHOR :- SIDEKICK9497
package com.sidekick.pixogram.actionservice.repository;

import com.sidekick.pixogram.actionservice.entity.Actions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
@Transactional
@Repository
public interface ActionsRepository extends JpaRepository<Actions, Integer> {
        public List<Actions> findAllByMediaIdAndLikedIsTrue(Integer mediaId);
       public List<Actions> findAllByMediaIdAndLikedIsFalse(Integer mediaId);
       public Actions findByMediaIdAndUserId(Integer mediaId, Integer userId);
       public boolean existsByMediaIdAndUserId(Integer mediaId, Integer userId);
       public int removeByMediaIdAndUserId(Integer mediaId,Integer userId);
}
