package com.hazem.clientproject;

import java.util.List;
import java.util.stream.Collectors;

import com.hazem.clientproject.entity.Authority;
import com.hazem.clientproject.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public final class JwtUserFactory {

	 private JwtUserFactory() {
	    }

	    public static JwtUser create(User user) {
	        return new JwtUser(
	                user.getIdUser(),
	                user.getUsername(),
	                user.getFirstname(),
	                user.getLastname(),
	                user.getEmail(),
	                user.getPassword(),
	                mapToGrantedAuthorities(user.getAuthorities()),
	                user.getEnabled(),
	                user.getLastPasswordResetDate()
	        );
	    }

	    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Authority> authorities) {
	        return authorities.stream()
	                .map(authority -> new SimpleGrantedAuthority(authority.getName().name()))
	                .collect(Collectors.toList());
	    }
}
