package edu.sjsu.cmpe275.nft.controllers;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.sjsu.cmpe275.nft.entities.Bid;
import edu.sjsu.cmpe275.nft.entities.NFT;
import edu.sjsu.cmpe275.nft.entities.Sale;
import edu.sjsu.cmpe275.nft.entities.User;
import edu.sjsu.cmpe275.nft.entities.Wallet;
import edu.sjsu.cmpe275.nft.entities.enums.SalesType;
import edu.sjsu.cmpe275.nft.services.CryptocurrencyService;
import edu.sjsu.cmpe275.nft.services.NFTService;
import edu.sjsu.cmpe275.nft.services.SaleService;
import edu.sjsu.cmpe275.nft.services.SecurityService;

@Controller
@RequestMapping("/sale")
@CrossOrigin(origins = "*")
public class SaleController {

	private static final Logger LOG = LoggerFactory.getLogger(SaleController.class);
	
	@Autowired
	private SaleService saleService;
	
	@Autowired
	private CryptocurrencyService cryptocurrencyService;
	
	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private NFTService nftService;
	
	@GetMapping("/new/{token}")
	public String newSale( @PathVariable("token") String token, ModelMap model ) {
		
		model.addAttribute( "cryptos", cryptocurrencyService.getAll( ) );
		model.addAttribute( "saleTypes", Arrays.asList( SalesType.values() ) );

		User seller = securityService.getCurrentLoggedInUser();
		
		NFT nft = nftService.getNFTById( token );
		
		if( nft == null || !nft.getUser().equals(seller) ) {
			
			model.addAttribute( "message", "NFT doesn't exist or doesn't belong to user " + seller.getNickName() );
			
			return "saleMessage";
			
		}
		
		model.addAttribute( "sale", new Sale( seller, nft ) );
		
		return "saleForm";
		
	}
	
	@PostMapping("/save")
	public ModelAndView createSale( @ModelAttribute("sale") Sale sale, ModelMap model ) {
		
		sale.setCreationTime( new Timestamp ( System.currentTimeMillis() ) );
		
		saleService.save(sale);
		
		model.addAttribute("msg", "Your sale was successfully created");
		
        return new ModelAndView("redirect:/sale", model );
		
	}
	
	
	
	@GetMapping("/makeOffer/{saleId}")
	public ModelAndView makeOffer( @PathVariable("saleId") Long saleId, ModelMap model ) {
		
		String forward = null;
		
		Sale sale = saleService.getById( saleId );
		
		if( sale.getType() == SalesType.PRICED  ) {
			
			forward = "forward:/sale/buy/" + sale.getId();
			
		} else {
			
			forward = "forward:/sale/bid/" + sale.getId();
			
		}
		
		return new ModelAndView( forward, model );
		
    }
	
	@GetMapping("/buy/{saleId}")
	public String buyNft( @PathVariable("saleId") Long saleId, ModelMap model ) {
		
		String message = null;
		
		User buyer = securityService.getCurrentLoggedInUser();

		Sale sale = saleService.getById( saleId );
		
		if( sale.getSeller().getId() == buyer.getId() ) {
			
			message = "Seller cannot buy his own NFT.";
			
		} else if( sale.getType() == SalesType.AUCTION ) {
			
			message = "It is not possible to buy a NFT from an Auction sale.";
			
		} else if( sale.getClosingTime() != null ) {

			message = "It is not possible to buy NFT from a closed sale.";
			
		} else if( ! hasEnoughtBalance( sale, buyer ) ) {
			
			message = "You do not have enough " + sale.getCryptocurrency().getName() + " balance for this purchase."; 
			
		}
		
		if( message != null ) {
			
			model.addAttribute( "msg", message );
			
			return "redirect:/sale/listOpened";
			
		} 
		
		model.addAttribute( "sale", sale );
			
		return "salePurchaseConfirm";
	
	}
	
	@GetMapping("/bid/{saleId}")
	public String bidOnAuction( @PathVariable("saleId") Long saleId, ModelMap model ) {
		
		String message = null;
		
		Bid bid = new Bid();
		
		User bidder = securityService.getCurrentLoggedInUser();

		Sale sale = saleService.getById( saleId );
		
		if( sale.getSeller().getId() == bidder.getId() ) {
			
			message = "Seller cannot bid on his own NFT auction.";
			
		} else if( sale.getType() == SalesType.PRICED ) {
			
			message = "It is not possible to bid in a Priced sale.";
			
		} else if( sale.getClosingTime() != null ) {

			message = "It is not possible to bid in a closed auction.";
			
		}
		
		if( message != null ) {
			
			model.addAttribute( "message", message );
			
			return "saleMessage";
			
		}
		
		bid.setSale(sale);
		bid.setUser(bidder);
		
		model.addAttribute( "bid", bid );
		
		return "bidForm";
		
	}
	
	@GetMapping("/purchase/{saleId}")
	public ModelAndView purchaseNft( @PathVariable("saleId") Long saleId, ModelMap model ) {
		
		Sale sale = saleService.getById( saleId );
		
		sale.setClosingTime( new Timestamp ( System.currentTimeMillis() ) );
		
		sale.setBuyer( securityService.getCurrentLoggedInUser() );
		
		saleService.save( sale );
		
		// TODO Change the ownership of the NFT
		
		model.addAttribute("msg", "Your purchase was successfully closed.");
		
        return new ModelAndView("redirect:/listNFT", model );
		
	}
	
	@PostMapping("/bid/makeBid")
	public String makeBid( @ModelAttribute("bid") Bid bid, ModelMap model ) {
		
		Bid highestBid = saleService.getHighestBid( bid.getSale().getId() );
		
		if( highestBid != null ) {
			
			if( bid.getBidValue() <= highestBid.getBidValue() ) {
				
				model.addAttribute( "message", "Bid has to be greater then " + highestBid.getBidValue() + " " + highestBid.getSale().getCryptocurrency().getSymbol() );
				
				return "saleMessage";
				
			}
			
			highestBid.setExpirationTime( new Timestamp ( System.currentTimeMillis() ) );
			
			saleService.saveBid( highestBid );
			
		}
		
		Bid previousBid = saleService.getPreviousActiveBid( bid );
		
		if( previousBid != null ) {
			
			previousBid.setExpirationTime( new Timestamp ( System.currentTimeMillis() ) );
			
			saleService.saveBid( previousBid );
			
		}
		
		bid.setBidTime( new Timestamp ( System.currentTimeMillis() ) );
		
		saleService.saveBid( bid );
		
		return "saleSuccess";
		
	}
	
	@GetMapping("/bid/acceptOffer/{bidId}")
	public String acceptOffer( @PathVariable("bidId") Long bidId, ModelMap model ) {
		
		Bid currentBid = saleService.getBidById( bidId );
		
		String message = null;
		
		if( currentBid == null ) {
			
			message = "Bid with ID " + bidId + " does not exist";
			
		} else if( !currentBid.getSale().getSeller().equals( securityService.getCurrentLoggedInUser() ) ) {
		
			message = "You can only accept offers from your own sales.";
			
		} else if( currentBid.getSale().getClosingTime() != null ) {
			
			message = "It is not possible to accept offers for sales that have already been closed.";
			
		} else if( currentBid.getExpirationTime() != null && currentBid.getExpirationTime().before( new Timestamp( System.currentTimeMillis() ) ) ) {
			
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "mm/dd/yyyy hh:mm:ss" );
			
			message = "This offer has expired at " + simpleDateFormat.format( currentBid.getExpirationTime() );
			
		}
		
		if( message != null ) {
			
			model.addAttribute( "message", message );
			
			return "saleMessage";
			
		}

		// TODO Check if the bid is the highest offer?
		// TODO Change the ownership of the NFT
		
		currentBid.getSale().setBuyer( currentBid.getUser() );
		currentBid.getSale().setReceivedValue( currentBid.getBidValue() );
		currentBid.getSale().setClosingTime( new Timestamp ( System.currentTimeMillis() ) );
		
		currentBid.setWasAccepted( true );
		
		saleService.saveBid( currentBid );
		
		return "saleSuccess";
		
	}
	
	@RequestMapping( value = "/", method = RequestMethod.GET)
	public String browse(ModelMap modelMap) {
		
		User currentLoggedInUser = securityService.getCurrentLoggedInUser();
		
		if (currentLoggedInUser == null) return "/";
		
		List<Sale> sales = saleService.getAllSalesListedBy(currentLoggedInUser);
		List<NFT> nfts = new ArrayList<>();

		for (Sale sale : sales) {
			String tokenId = sale.getNft().getTokenId();
			NFT nft = nftService.getNFTById(tokenId);
			
			if (nft != null) {
				nfts.add(nft);
			}
		}
		
		modelMap.addAttribute("nfts", nfts);
		
		return "browseNftsListedForSale";
	}
	
	@GetMapping("/listOpened")
	public String openedSale( ModelMap modelMap) {
		
		modelMap.addAttribute( "sales", saleService.getOpened( ) );
		
		return "listSaleOpened";
		
	}

	@GetMapping( )
	public String mySales( ModelMap modelMap) {
		
		List<Sale> sales = saleService.getAllSalesListedBy( securityService.getCurrentLoggedInUser() );
		
		List<Sale> priced = new ArrayList<>();
		List<Sale> auction = new ArrayList<>();
		
		for ( Sale sale : sales ) {
			
			if( sale.getType() == SalesType.PRICED ) {
				
				priced.add( sale );
				
			} else {
				
				auction.add( sale );
				
			}
			
		}
		
		modelMap.addAttribute( "priced", priced );
		modelMap.addAttribute( "auction", auction );
		
		return "browseNftsListedForSale";
		
	}
	
	private boolean hasEnoughtBalance( Sale sale, User buyer ) {
		
		List<Wallet> wallets = buyer.getWallets();
		
			for( Wallet wallet : wallets ) {
					
				if( wallet.getWalletId().getCryptocurrency().equals(sale.getCryptocurrency()) ) {
						 
					if( wallet.getBalance() < sale.getExpectedValue() ) { return false; }
					
					break;
						 
				 }
					
			}
		
		return true;
		
	}
	
}