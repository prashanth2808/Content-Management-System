package com.example.cms.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.cms.entity.User;
import com.example.cms.repository.UserRepository;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class CustomUserDetailService  implements  UserDetailsService{

	private UserRepository urepo;
	
	
	
	
	@Override 
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		return urepo.findByEmail(username).map(t -> new CustomUserDetails(t)  ).orElseThrow(()  -> new UsernameNotFoundException("User name notfound ") );
		
	
	}

}
