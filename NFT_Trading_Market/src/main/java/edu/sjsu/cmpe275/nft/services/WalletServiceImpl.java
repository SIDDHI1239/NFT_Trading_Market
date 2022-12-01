package edu.sjsu.cmpe275.nft.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.sjsu.cmpe275.nft.entities.Cryptocurrency;
import edu.sjsu.cmpe275.nft.entities.User;
import edu.sjsu.cmpe275.nft.entities.Wallet;
import edu.sjsu.cmpe275.nft.entities.WalletId;
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

	@Override
	@Transactional
	public void createWallets(User user) {
		// Cryptocurrency bitcoinCryptocurrency = new Cryptocurrency(Constants.BTC,
		// Constants.BITCOIN);
		Cryptocurrency bitcoinCryptocurrency = cryptocurrencyService.createCryptocurrency(Constants.BTC,
				Constants.BITCOIN);
		Cryptocurrency ethereumCryptocurrency = cryptocurrencyService.createCryptocurrency(Constants.ETH,
				Constants.ETHEREUM);

		WalletId bitcoinWalletId = new WalletId(user, bitcoinCryptocurrency);
		WalletId ethereumWalletId = new WalletId(user, ethereumCryptocurrency);

		Wallet bitcoinWallet = new Wallet();
		bitcoinWallet.setWalletId(bitcoinWalletId);

		Wallet ethereumWallet = new Wallet();
		ethereumWallet.setWalletId(ethereumWalletId);

		create(bitcoinWallet);
		create(ethereumWallet);
	}

}
