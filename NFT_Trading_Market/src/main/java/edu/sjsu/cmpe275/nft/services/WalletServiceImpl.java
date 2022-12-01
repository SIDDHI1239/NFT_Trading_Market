package edu.sjsu.cmpe275.nft.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.sjsu.cmpe275.nft.entities.Cryptocurrency;
import edu.sjsu.cmpe275.nft.entities.User;
import edu.sjsu.cmpe275.nft.entities.Wallet;
import edu.sjsu.cmpe275.nft.entities.WalletId;
import edu.sjsu.cmpe275.nft.entities.enums.Currency;
import edu.sjsu.cmpe275.nft.repos.WalletRepository;
import edu.sjsu.cmpe275.nft.util.Constants;

@Service
public class WalletServiceImpl implements WalletService {

	@Autowired
	private WalletRepository walletRepository;

	@Autowired
	private CryptocurrencyService cryptocurrencyService;

	@Override
	@Transactional
	public Wallet create(Wallet wallet) {
		return walletRepository.save(wallet);
	}

	// Creates wallets for all supported currencies to the given user
	@Override
	@Transactional
	public void createWallets(User user) {

		for (Currency currency : Currency.values()) {
			// create currency for each supported currency
			Cryptocurrency cryptocurrency = cryptocurrencyService.createCryptocurrency(currency.getKey(), currency.getValue());
			
			// create wallet ID for currency to the given user
			WalletId walletId = new WalletId(user, cryptocurrency);
			
			// assign wallet ID to wallet for that user
			Wallet wallet = new Wallet();
			wallet.setWalletId(walletId);
			
			// create wallet
			create(wallet);
		}
	}

}
