package edu.sjsu.cmpe275.nft.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.sjsu.cmpe275.nft.entities.User;
import edu.sjsu.cmpe275.nft.repos.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	@Override
	public User addUser(User user) {
		return userRepository.save(user);
	}

}
