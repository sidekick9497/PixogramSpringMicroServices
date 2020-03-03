package com.sidekick.pixogram.userservice.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.sidekick.pixogram.userservice.entity.Authorities;

@Repository
@Transactional
public class AuthorityRepoImp implements AuthorityRepository {
	
	@PersistenceContext
	private EntityManager em;
	
		
	@Override
	public void save(Authorities auth) {
		// TODO Auto-generated method stub
		this.em.persist(auth);
	}

}
