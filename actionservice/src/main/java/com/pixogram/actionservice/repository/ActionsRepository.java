package com.pixogram.actionservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pixogram.actionservice.entity.Actions;

public interface ActionsRepository extends JpaRepository<Actions, Integer> {

}
