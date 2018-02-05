package com.hazem.clientproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hazem.clientproject.JwtUserFactory;
import com.hazem.clientproject.entity.User;
import com.hazem.clientproject.repository.UserRepository;

@Service
@EnableJpaRepositories(basePackages = "com.hazem.clientproject.repository")
public class JwtUserDetailsServiceImpl implements UserDetailsService {

		@Autowired
		private UserRepository userRepository;
		
	 @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        User user = userRepository.findByUsername(username);

	        if (user == null) {
	            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
	        } else {
	            return JwtUserFactory.create(user);
	        }
	 	}
	
}
