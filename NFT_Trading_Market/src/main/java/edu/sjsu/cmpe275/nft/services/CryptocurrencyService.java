package edu.sjsu.cmpe275.nft.services;

import java.util.List;

import org.slf4j.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.sjsu.cmpe275.nft.entities.Cryptocurrency;

import edu.sjsu.cmpe275.nft.repos.CryptocurrencyRepository;

@Service
public class CryptocurrencyService {

	private static final Logger log = LoggerFactory.getLogger(CryptocurrencyService.class);

	private final CryptocurrencyRepository cryptocurrencyRepository;

	@Autowired
	public CryptocurrencyService(CryptocurrencyRepository cryptocurrencyRepository) {
		this.cryptocurrencyRepository = cryptocurrencyRepository;
	}
	
	public Cryptocurrency getBySymbol( String symbol ) {
		return cryptocurrencyRepository.getReferenceById( symbol );
	}
	
	public List<Cryptocurrency> getAll() {
		
		return cryptocurrencyRepository.findAll();
		
	}

}