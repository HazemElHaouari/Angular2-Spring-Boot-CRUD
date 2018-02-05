package com.hazem.clientproject.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hazem.clientproject.entity.Authority;

@Transactional
@Repository
public class AuthorityDAO implements IAuthorityDAO{

	@PersistenceContext	
	private EntityManager entityManager;
	@Override
	public Authority getAuthorityById(int idAuthority) {
		return entityManager.find(Authority.class, idAuthority);
	}
}
