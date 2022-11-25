package edu.sjsu.cmpe275.nft.service;

import org.springframework.transaction.annotation.Transactional;

import org.slf4j.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.sjsu.cmpe275.nft.Repository.NFTRepository;
import edu.sjsu.cmpe275.nft.entities.NFT;

@Service
public class NFTServiceImpl implements NFTService {

	private static final Logger log = LoggerFactory.getLogger(NFTServiceImpl.class);

	private final NFTRepository nftRepository;

	@Autowired
	public NFTServiceImpl(NFTRepository nftRepository) {
		this.nftRepository = nftRepository;
	}

	/**
	 * Returns newly saved NFT into database
	 * 
	 * @param nft NFT entity to be saved
	 * @return
	 */

	@Override
	@Transactional
	public NFT addNFT(NFT nft) {
		return nftRepository.save(nft);
	}
}
