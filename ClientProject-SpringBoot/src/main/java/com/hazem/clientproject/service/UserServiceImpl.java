package com.hazem.clientproject.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hazem.clientproject.entity.User;
import com.hazem.clientproject.dao.IAuthorityDAO;
import com.hazem.clientproject.dao.IUserDAO;
import com.hazem.clientproject.entity.Authority;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private IAuthorityDAO authorityDAO;
    @Autowired
    private IUserDAO userDAO;

    @Override
    public synchronized boolean saveUser(User user) {
    	
    	List<Authority> auths= new ArrayList<Authority>();
    	auths.add(authorityDAO.getAuthorityById(2));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        user.setLastPasswordResetDate(new Date());
        user.setAuthorities(auths);
        if (userDAO.userExists(user.getUsername())) {
	           return false;
        } else {
	           userDAO.createUser(user);
	           return true;
        }
    }

}