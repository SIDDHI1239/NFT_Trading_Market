package edu.sjsu.cmpe275.nft.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import edu.sjsu.cmpe275.nft.entities.User;

// Service for UsernamePasswordAuthenticationToken for authentication and maintaining session by setting user in Security context until user logs out
@Service
public class SecurityServiceImpl implements SecurityService {

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;

	@Override
	public boolean login(String email, String password) {
		Set<GrantedAuthority> authorities = new HashSet<>();
		authorities.add(new SimpleGrantedAuthority("USER"));

		UserDetails userDetails = userDetailsService.loadUserByUsername(email);

		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, password,
				authorities);

		authenticationManager.authenticate(token);

		boolean isTokenAuthenticated = token.isAuthenticated();

		if (isTokenAuthenticated) {
			SecurityContextHolder.getContext().setAuthentication(token);
		}

		return isTokenAuthenticated;
	}
	
	@Override
	public User getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (authentication == null) return null;
		
		String email = authentication.getName();
		
		System.out.println(email);
		
		User user = userService.getUserByEmail(email);
		return user;
	}

}
