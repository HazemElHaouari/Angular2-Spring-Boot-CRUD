package com.hazem.clientproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.hazem.clientproject.entity.Authority;

@RepositoryRestResource(exported = false)
public interface AuthorityRepository extends JpaRepository<Authority, Long>{
	Authority findByIdAuthority(int idAuthority);
}
