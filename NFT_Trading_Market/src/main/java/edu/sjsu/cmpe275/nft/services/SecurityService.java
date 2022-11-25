package edu.sjsu.cmpe275.nft.services;

// Interface for handling login and maintaining session
public interface SecurityService {

	boolean login(String email, String password);
}
