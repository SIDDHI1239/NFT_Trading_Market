package edu.sjsu.cmpe275.nft.controllers;

import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.sjsu.cmpe275.nft.entities.Cryptocurrency;
import edu.sjsu.cmpe275.nft.entities.Provider;
import edu.sjsu.cmpe275.nft.entities.User;
import edu.sjsu.cmpe275.nft.entities.Wallet;
import edu.sjsu.cmpe275.nft.entities.WalletId;
import edu.sjsu.cmpe275.nft.services.CryptocurrencyService;
import edu.sjsu.cmpe275.nft.services.SecurityService;
import edu.sjsu.cmpe275.nft.services.UserService;
import edu.sjsu.cmpe275.nft.services.WalletService;
import edu.sjsu.cmpe275.nft.util.Constants;

@Controller
@CrossOrigin(origins = "*")
public class UserController {

	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

	// Regex which allows alphanumeric passwords only
	private static final String ALPHANUMERIC_PATTERN = "^[a-zA-Z0-9]+$";

	@Autowired
	private UserService userService;

	// Bean for password encryption
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	// Bean for security (login)
	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private WalletService walletService;
	
	@Autowired
	private CryptocurrencyService cryptocurrencyService;

	@RequestMapping("/registerUser")
	public String getRegister() {
		return "register";
	}

	@RequestMapping("/localLogin")
	public String getLogin() {
		return "login";
	}

	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("user") User user,
			@RequestParam("confirmPassword") String confirmPassword, ModelMap modelMap) {
		String email = user.getEmail();
		User userWithEmail = userService.getUserByEmail(email);

		if (userWithEmail != null) {
			modelMap.addAttribute("msg",
					"A user already exists with the given email. Please try with different email.");
			return "register";
		}

		String nickName = user.getNickName();
		User userWithNickName = userService.getUserByNickName(nickName);

		if (userWithNickName != null) {
			modelMap.addAttribute("msg", "Nickname :" + nickName
					+ " is already taken and is not available. Please try with a different unique nickname.");
			return "register";
		}

		String password = user.getPassword();

		if (!password.equals(confirmPassword)) {
			modelMap.addAttribute("msg", "Passwords don't match. Please try again.");
			return "register";
		}

		boolean isAlphaNumeric = nickName.matches(ALPHANUMERIC_PATTERN);

		if (!isAlphaNumeric) {
			modelMap.addAttribute("msg",
					"Nickname must contain alphanumeric text only. No character other than alphanumeric is allowed.");
			return "register";
		}

		// Encoding password
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

		String token = UUID.randomUUID().toString();
		user.setToken(token);
		user.setProvider(Provider.LOCAL.toString());
		
		userService.addUser(user);
		
//		Cryptocurrency bitcoinCryptocurrency = new Cryptocurrency(Constants.BTC, Constants.BITCOIN);
//		
//		cryptocurrencyService.addCryptocurrency(bitcoinCryptocurrency);
//		
//		WalletId bitcoinWalletId = new WalletId(user, bitcoinCryptocurrency);
//		
//		Wallet bitcoinWallet = new Wallet();
//		bitcoinWallet.setWalletId(bitcoinWalletId);
//		
//		Cryptocurrency ethereumCryptocurrency = new Cryptocurrency(Constants.ETH, Constants.ETHEREUM);
//		
//		cryptocurrencyService.addCryptocurrency(ethereumCryptocurrency);
//		
//		WalletId ethereumWalletId = new WalletId(user, ethereumCryptocurrency);
//		
//		Wallet ethereumWallet = new Wallet();
//		ethereumWallet.setWalletId(ethereumWalletId);
//		
//		walletService.addWallet(bitcoinWallet);
//		walletService.addWallet(ethereumWallet);
		
		walletService.createWallets(user);
		
		try {
			userService.sendEmailForVerification(user);
		} catch (Exception e) {
			modelMap.addAttribute("msg", "Email could not be sent. Please enter valid email address and try again.");
			return "register";
		}

		return "registrationVerification";
	}

	@RequestMapping(value = "/confirmAccount", method = RequestMethod.GET)
	public String confirmAccount(@RequestParam("token") String token, ModelMap modelMap) {
		User user = userService.getByToken(token);

		if (user == null) {
			modelMap.addAttribute("msg", "Invalid verification/confirmation link.");
			return "verificationFailure";
		}

		if (user.isVerified()) {
			modelMap.addAttribute("msg", user.getEmail() + " already verified. Please login to access your account.");
			return "login";
		}

		user.setVerified(true);
		userService.addUser(user);

		return "registrationSuccess";
	}

	@RequestMapping(value = "/localLogin", method = RequestMethod.POST)
	public String login(@RequestParam("email") String email, @RequestParam("password") String password,
			ModelMap modelMap) {
		// verifying login with security service
		User user = userService.getUserByEmail(email);

		if (user == null) {
			modelMap.addAttribute("msg", "User not found with email" + email);
			return "login";
		} else {
			if (!user.isVerified()) {
				modelMap.addAttribute("msg", "Email address not verified. Please verify email first.");
				return "login";
			}
		}

		boolean isSuccess = securityService.login(email, password);

		if (!isSuccess) {
			modelMap.addAttribute("msg", "Invalid email or password. Please try again.");
			return "login";
		}

		return "displayProfile";
	}

	@RequestMapping("/googleLogin")
	public String googleLogin(ModelMap modelMap) {
		Map<String, Object> attributes = securityService.getCurrentLoggedInUserAttibutesFromOAuth();
		
		String googleEmail = (String) attributes.get("email");
		
		User user = userService.getUserByEmail(googleEmail);
		
		if (user != null) {
			if (!user.getProvider().equals(Provider.GOOGLE.toString())) {
				modelMap.addAttribute("msg", "A local user already exists with the same email. Please try local login with the same email.");
				securityService.removeCurrentLoggedInUserFromOAuth();
				return "login";
			}
			
			if (!user.isVerified()) {
				//modelMap.addAttribute("msg", "Please verify email to login.");
				return "registrationVerification";
			}
			
			return "profile";
		}
		
		String firstName = (String) attributes.get("given_name");
		String lastName = (String) attributes.get("family_name");
		
		User newGoogleUser = new User();
		
		newGoogleUser.setEmail(googleEmail);
		newGoogleUser.setFirstName(firstName);
		newGoogleUser.setLastName(lastName);
		newGoogleUser.setNickName(googleEmail);
		newGoogleUser.setProvider(Provider.GOOGLE.toString());
		
		String token = UUID.randomUUID().toString();
		newGoogleUser.setToken(token);
		
		userService.addUser(newGoogleUser);
		
		try {
			userService.sendEmailForVerification(newGoogleUser);
		} catch (Exception e) {
			modelMap.addAttribute("msg", "Failed to send email. Please try again.");
			return "verificationFailure";
		}

		/*
		 * if (!newGoogleUser.isVerified()) { return "registrationVerification"; }
		 */
		
		return "registrationVerification";
		
		/*
		 * if (googleEmail == null) { try { User user = new User();
		 * user.setEmail(currentUser.getEmail());
		 * user.setProvider(Provider.GOOGLE.toString()); String token =
		 * UUID.randomUUID().toString(); user.setToken(token);
		 * 
		 * userService.addUser(user);
		 * 
		 * userService.sendEmailForVerification(currentUser); } catch (Exception e) {
		 * modelMap.addAttribute("msg", "Failed to send email. Please try again.");
		 * return "redirect:googleLogin"; }
		 * 
		 * return "registrationVerification"; }
		 * 
		 * if (!currentUser.getProvider().equals(Provider.GOOGLE.toString())) {
		 * modelMap.addAttribute("msg",
		 * "A local user already exists with the same email. Please try local login with the same email."
		 * ); return "login"; }
		 * 
		 * if (!currentUser.isVerified()) { modelMap.addAttribute("msg",
		 * "Email address not verified. Please verify email first."); return
		 * "registrationVerification"; }
		 * 
		 * return "profile";
		 */
	}

	@GetMapping("/profile")
	public String getProfile() {
		return "profile";

	}

}
