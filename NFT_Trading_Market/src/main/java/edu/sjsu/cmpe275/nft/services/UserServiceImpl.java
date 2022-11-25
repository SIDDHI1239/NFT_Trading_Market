package edu.sjsu.cmpe275.nft.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.sjsu.cmpe275.nft.entities.User;
import edu.sjsu.cmpe275.nft.repos.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	@Override
	@Transactional
	public User addUser(User user) {
		return userRepository.save(user);
	}
	
	@Override
	@Transactional
	public User getUserByNickName(String nickname) {
		return userRepository.findByNickName(nickname);
	}

}
