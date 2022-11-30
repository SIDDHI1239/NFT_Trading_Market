package edu.sjsu.cmpe275.nft.controllers;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.sjsu.cmpe275.nft.entities.NFT;
import edu.sjsu.cmpe275.nft.entities.User;
import edu.sjsu.cmpe275.nft.services.NFTService;
import edu.sjsu.cmpe275.nft.services.SecurityService;

@Controller
@RequestMapping("/nft")
@CrossOrigin(origins = "*")
public class NFTController {

	private static final Logger Log = LoggerFactory.getLogger(NFTController.class);

	@Autowired
	private NFTService nftService;
	
	@Autowired
	private SecurityService securityService;

	@PostMapping(value = "/addnft")
	public String addNFT(@RequestParam("name") String name, @RequestParam("type") String type,
			@RequestParam("description") String description, @RequestParam("imageUrl") String imageUrl,
			@RequestParam("assetUrl") String assetUrl) {

		Log.info("Executing addNFT() {}, {}, {},{},{} ", name, type, description, imageUrl, assetUrl);

		String tokenId = UUID.randomUUID().toString();

		Timestamp lastRecordedData = new Timestamp(System.currentTimeMillis());

		String smartContactAddress = UUID.randomUUID().toString();

		NFT nft = new NFT();
		
		nft.setTokenId(tokenId);
		nft.setSmartContractAddress(smartContactAddress);
		nft.setName(name);
		nft.setType(type);
		nft.setDescription(description);
		nft.setImageUrl(imageUrl);
		nft.setAssetUrl(assetUrl);
		nft.setLastRecordedDate(lastRecordedData);

		NFT createdNFT = nftService.addNFT(nft);

		Log.info("Exiting addNFT() >> {}", tokenId);

		return "sellNFT";
	}
	
	@RequestMapping("/sellnft")
	public String sellNFT(ModelMap modelMap) {
		User currentUser = securityService.getCurrentUser();
		
		if (currentUser == null) return "/";
		
		List<NFT> currentUserNfts = nftService.getAllNFTs(currentUser);
		modelMap.addAttribute("nfts", currentUserNfts);
		
		return "displayNfts";
	}
}
