package edu.sjsu.cmpe275.nft.services;

import edu.sjsu.cmpe275.nft.entities.User;
import edu.sjsu.cmpe275.nft.entities.Wallet;

public interface WalletService {

	Wallet create(Wallet wallet);
	
	void createWallets(User user);
}
