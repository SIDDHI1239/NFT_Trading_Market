package edu.sjsu.cmpe275.nft.services;

import edu.sjsu.cmpe275.nft.entities.User;

// Interface for handling login and maintaining session
public interface SecurityService {

	boolean login(String email, String password);
	
	User getCurrentUser();
}
