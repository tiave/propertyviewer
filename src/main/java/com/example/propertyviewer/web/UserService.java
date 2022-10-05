package com.example.propertyviewer.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.propertyviewer.domain.User;
import com.example.propertyviewer.domain.UserRepository;

@Service
public class UserService implements UserDetailsService  {
	private final UserRepository urepo;

	@Autowired
	public UserService(UserRepository urepo) {
		this.urepo = urepo;
	}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {   
    	User currUser = urepo.findByUsername(username);
        UserDetails user = new org.springframework.security.core.userdetails.User(username, currUser.getPasswordHash(), 
        		AuthorityUtils.createAuthorityList(currUser.getRole()));
        return user;
    }   
} 