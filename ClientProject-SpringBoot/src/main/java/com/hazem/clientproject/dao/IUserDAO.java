package com.hazem.clientproject.dao;

import java.util.List;

import com.hazem.clientproject.entity.User;

public interface IUserDAO {
	List<User> getAllUsers();
    User getUserById(int userId);
    void createUser(User user);
    void updateUser(User user);
    void deleteUser(int idUser);
    boolean userExists(String username);
}
