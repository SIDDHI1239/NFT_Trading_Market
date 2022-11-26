package edu.sjsu.cmpe275.nft.services;

import edu.sjsu.cmpe275.nft.entities.VerificationToken;

public interface VerificationTokenService {

	VerificationToken getByToken(String token);
}
