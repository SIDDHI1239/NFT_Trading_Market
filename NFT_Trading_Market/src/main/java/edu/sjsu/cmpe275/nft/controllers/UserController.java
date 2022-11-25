package edu.sjsu.cmpe275.nft.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.sjsu.cmpe275.nft.entities.User;
import edu.sjsu.cmpe275.nft.services.UserService;

@Controller
public class UserController {

	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	private static final String ALPHANUMERIC_PATTERN = "^[a-zA-Z0-9]+$";
	
	@RequestMapping("/register")
	public String register() {
		return "register";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public String login(@ModelAttribute("user") User user, @RequestParam("confirmPassword") String confirmPassword, ModelMap modelMap) {
		String email = user.getEmail();
		User userWithEmail = userService.getUserByEmail(email);
		
		if (userWithEmail != null) {
			modelMap.addAttribute("msg", "A user already exists with the given email. Please try with different email.");
			return "register";
		}
		
		String nickName = user.getNickName();
		User userWithNickName = userService.getUserByNickName(nickName);
		
		if (userWithNickName != null) {
			modelMap.addAttribute("msg", "Nickname :" + nickName + " is already taken and is not available. Please try with a different unique nickname.");
			return "register";
		}
		
		String password = user.getPassword();
		
		if (!password.equals(confirmPassword)) {
			modelMap.addAttribute("msg", "Passwords don't match. Please try again.");
			return "register";
		}
		
		boolean isAlphaNumeric = nickName.matches(ALPHANUMERIC_PATTERN);
		
		if (!isAlphaNumeric) {
			modelMap.addAttribute("msg", "Nickname must contain alphanumeric text only. No character other than alphanumeric is allowed.");
			return "register";
		}
		
		userService.addUser(user);
		
		return "login";
	}
	
	@GetMapping("/Profile")
	public String getProfile() {
		
		String userName = "Mithra";
		System.out.println("In getProfile");
		return "Profile";
		
	}
	
	@GetMapping("/SellNFT")
	public String sellNFT() {
		System.out.println("In SellNFT");
		return "SellNFT";
		
	}
}
