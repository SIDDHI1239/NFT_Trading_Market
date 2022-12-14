package edu.sjsu.cmpe275.nft.controllers;


import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.sjsu.cmpe275.nft.entities.Bid;
import edu.sjsu.cmpe275.nft.entities.Sale;
import edu.sjsu.cmpe275.nft.entities.enums.SalesType;
import edu.sjsu.cmpe275.nft.services.SaleService;


@Controller
@CrossOrigin
public class SystemController {
	
	@Autowired
	private SaleService saleService;

	@RequestMapping(value = "/systemDashboard", method = RequestMethod.GET)
	public String viewSystemDashboard(ModelMap modelMap) {
		return getActiveSales(modelMap);
	}
	
	@RequestMapping(value = "/systemDashboard", method = RequestMethod.POST)
	public String getActiveSales(ModelMap modelMap) {
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
					
			}else {
				countOfPricesNFT++;
			}
		}
		modelMap.addAttribute("countActiveSales", sales.size());
		modelMap.addAttribute("countOfAuctionWithBids",countOfAuctionWithBids);
		modelMap.addAttribute("countOfAuctionWithoutBids",countOfAuctionWithoutBids);
		modelMap.addAttribute("countActiveBids",countActiveBids);
		modelMap.addAttribute("countOfAuctionsNFT",countOfAuctionsNFT);
		modelMap.addAttribute("countOfPricesNFT",countOfPricesNFT);
		
		return "systemDashboard";
	}
		
}
