package edu.sjsu.cmpe275.nft.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.sjsu.cmpe275.nft.entities.VerificationToken;
import edu.sjsu.cmpe275.nft.repos.VerificationTokenRepository;

@Service
public class VerificationTokenServiceImpl implements VerificationTokenService {

	@Autowired
	private VerificationTokenRepository verificationTokenRepository;
	
	@Override
	@Transactional
	public VerificationToken getByToken(String token) {
		return verificationTokenRepository.findByToken(token);
	}

}
