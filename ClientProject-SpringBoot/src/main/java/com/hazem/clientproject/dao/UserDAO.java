package com.hazem.clientproject.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hazem.clientproject.entity.User;

@Transactional
@Repository
public class UserDAO implements IUserDAO{
	@PersistenceContext	
	private EntityManager entityManager;	
	@Override
	public User getUserById(int idUser) {
		return entityManager.find(User.class, idUser);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUsers() {
		String hql = "FROM User as user ORDER BY user.idUser DESC";
		return (List<User>) entityManager.createQuery(hql).getResultList();
	}	
	@Override
	public void createUser(User user) {
		entityManager.persist(user);
	}
	@Override
	public void updateUser(User user) {
		User use = getUserById(user.getIdUser());
		use.setUsername(user.getUsername());
		use.setPassword(user.getPassword());
		use.setFirstname(user.getFirstname());
		use.setLastname(user.getLastname());
		use.setEmail(user.getEmail());
		use.setAuthorities(user.getAuthorities());
		entityManager.flush();
	}
	@Override
	public void deleteUser(int idUser) {
		entityManager.remove(getUserById(idUser));
	}
	@Override
	public boolean userExists(String username) {
		String hql = "FROM User as user WHERE user.username = ?";
		int count = entityManager.createQuery(hql).setParameter(1, username).getResultList().size();
		return count > 0 ? true : false;
	}
}
