package edu.sjsu.cmpe275.nft.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.sjsu.cmpe275.nft.entities.Bid;
import edu.sjsu.cmpe275.nft.entities.Sale;
import edu.sjsu.cmpe275.nft.repos.BidRepository;
import edu.sjsu.cmpe275.nft.repos.SaleRepository;

@Service
public class SaleService {

	private static final Logger log = LoggerFactory.getLogger(SaleService.class);

	private final SaleRepository saleRepository;
	
	private final BidRepository bidRepository;
	
	@Autowired
	public SaleService(SaleRepository saleRepository, BidRepository bidRepository ) {
		
		this.saleRepository = saleRepository;
		this.bidRepository = bidRepository;
	
	}
	
	public Sale getById( long saleId ) {
		
		return saleRepository.getReferenceById( saleId );
	}
	
	public Sale save(Sale sale) {
		return saleRepository.save( sale );
	}
	
	public Bid getBidById( Long bidId ) {
		return bidRepository.getReferenceById( bidId );
	}
	
	public Bid saveBid( Bid bid ) {
		return bidRepository.save( bid );
	}
	
	public Bid getPreviousActiveBid( Bid newBid ) {
		return bidRepository.findTopBySaleIdAndUserIdOrderByIdDesc( newBid.getSale().getId(), newBid.getUser().getId() );
	}
	
	public Bid getHighestBid( Long saleId ) {
		return bidRepository.findTopBySaleIdOrderByBidValueDesc( saleId );
	}

}