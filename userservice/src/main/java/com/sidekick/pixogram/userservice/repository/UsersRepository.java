package com.sidekick.pixogram.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sidekick.pixogram.userservice.entity.Users;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
             List<Users> findByUserName(String username);
             List<Users> findByUserNameLike(String userName);
             List<Users> findByUserNameContaining(String userName);
                Users     getUsersByUserName(String UserName);
}
