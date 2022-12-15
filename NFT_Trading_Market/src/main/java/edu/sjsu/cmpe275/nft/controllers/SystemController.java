package edu.sjsu.cmpe275.nft.controllers;


import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.sjsu.cmpe275.nft.entities.Bid;
import edu.sjsu.cmpe275.nft.entities.Sale;
import edu.sjsu.cmpe275.nft.entities.Transaction;
import edu.sjsu.cmpe275.nft.entities.enums.SalesType;
import edu.sjsu.cmpe275.nft.services.SaleService;
import edu.sjsu.cmpe275.nft.services.TransactionService;
import edu.sjsu.cmpe275.nft.services.WalletService;


@Controller
@CrossOrigin
public class SystemController {
	
	private static final Logger Log = LoggerFactory.getLogger(SystemController.class);
	
	@Autowired
	private SaleService saleService;
	
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private WalletService walletService;

	@RequestMapping(value = "/systemDashboard", method = RequestMethod.GET)
	public String viewSystemDashboard(ModelMap modelMap) {
		return getActiveSales( 1, "ALL", modelMap);
	}
	
	@RequestMapping(value = "/systemDashboard", method = RequestMethod.POST)
	public String getActiveSales( @RequestParam( name = "days", defaultValue = "1", required = false ) int days, 
                                  @RequestParam( name = "currency", defaultValue = "ALL", required = false ) String currency, ModelMap modelMap ) {
		
		int countOfAuctionWithBids = 0;
		int countOfAuctionWithoutBids =0;
		int countActiveBids = 0;
		int countOfAuctionsNFT = 0;
		int countOfPricesNFT = 0;
		
		List<Sale> sales = saleService.getActiveSalesForBid();
		
		for(Sale sale : sales) {
		
			if(sale.getType()== SalesType.Auction) {
			
				countOfAuctionsNFT++;
				
				if(sale.getBids().size()==0) {
				
					countOfAuctionWithoutBids++;
				
				}else {
				
					countOfAuctionWithBids++;
					
					for(Bid bid: sale.getBids()) {
					
						if(bid.getExpirationTime().after(new Timestamp(System.currentTimeMillis()))) {
						
							countActiveBids++;
						
						}
					
					}
				
				}
					
			} else {
				
				countOfPricesNFT++;
				
			}
			
		}
		
		
		List<String> currencies = new ArrayList<>();
		
		if( currency.equals("ALL") ) {
			
			currencies.add("BTC");
			currencies.add("ETH");
			
		} else {
			
			currencies.add(currency);
			
		}
		
		Calendar cal = Calendar.getInstance();
		
		cal.setTimeInMillis( System.currentTimeMillis() );
		
		cal.add( Calendar.DAY_OF_MONTH, - days );
		
		List<Transaction> transactions = transactionService.filterSystemTransactions( currencies, new Date( cal.getTime().getTime() ) );
		
		double btc[] = new double[8];
		double eth[] = new double[8];
		
		Log.info("Transactions size: " + transactions.size() );
		Log.info("Date: " + new Date( cal.getTime().getTime() ) );
		
		for( Transaction transaction : transactions ) {
			
			Log.info("Transaction: {}, {}, {}, {}", transaction.getTransactionDate(), transaction.getTransactionType(), transaction.getTransactionAmount(), transaction.getCryptocurrency().getSymbol()  );
			
			 if( transaction.getTransactionType().equals("Deposit") ) { 
				
				if( transaction.getCryptocurrency().getSymbol().equals("BTC") ) {
					
					btc[0]++;
					btc[1] += transaction.getTransactionAmount();
				
				} else {
					
					eth[0]++;
					eth[1] += transaction.getTransactionAmount();
					
				}
			
			} else if( transaction.getTransactionType().equals("Withdraw") ) { 
				
				if( transaction.getCryptocurrency().getSymbol().equals("BTC") ) {
					
					btc[2]++;
					btc[3] += transaction.getTransactionAmount();
				
				} else {
					
					eth[2]++;
					eth[3] += transaction.getTransactionAmount();
					
				}
				
			} else if( transaction.getTransactionType().equals("Sale") ) {
				
				if( transaction.getCryptocurrency().getSymbol().equals("BTC") ) {
				
					btc[6]++;
					btc[7] += transaction.getTransactionAmount();
				
				} else {
					
					eth[6]++;
					eth[7] += transaction.getTransactionAmount();
					
				}
				
			}
			
		}
		
		double btcBalance = walletService.totalBalanceBySymbol("BTC");
		double ethBalance = walletService.totalBalanceBySymbol("ETH");
		
		btc[4] = btcBalance - btc[1] + btc[3];
		btc[5] = btcBalance;
		
		eth[4] = ethBalance - eth[1] + eth[3];
		eth[5] = ethBalance;
		
		modelMap.addAttribute("days", days);
		modelMap.addAttribute("currency", currency);
		
		modelMap.addAttribute("eth", eth);
		modelMap.addAttribute("btc", btc);
		
		modelMap.addAttribute("countActiveSales", sales.size());
		modelMap.addAttribute("countOfAuctionWithBids",countOfAuctionWithBids);
		modelMap.addAttribute("countOfAuctionWithoutBids",countOfAuctionWithoutBids);
		modelMap.addAttribute("countActiveBids",countActiveBids);
		modelMap.addAttribute("countOfAuctionsNFT",countOfAuctionsNFT);
		modelMap.addAttribute("countOfPricesNFT",countOfPricesNFT);
		
		return "systemDashboard";
		
	}
		
}
