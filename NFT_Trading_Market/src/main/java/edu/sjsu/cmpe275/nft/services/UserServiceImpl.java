package edu.sjsu.cmpe275.nft.services;

import java.util.Date;
import java.util.UUID;

import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import edu.sjsu.cmpe275.nft.entities.User;
import edu.sjsu.cmpe275.nft.entities.VerificationToken;
import edu.sjsu.cmpe275.nft.repos.UserRepository;
import edu.sjsu.cmpe275.nft.repos.VerificationTokenRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private VerificationTokenRepository verificationTokenRepository;

	@Value("${spring.mail.username}")
	private String fromEmail;

	@Override
	@Transactional
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	@Transactional
	public User addUser(User user) {
		return userRepository.save(user);
	}

	@Override
	@Transactional
	public User getUserByNickName(String nickname) {
		return userRepository.findByNickName(nickname);
	}

	@Async
	@Override
	public void sendEmailForVerification(User user) throws Exception {
		VerificationToken verificationToken = new VerificationToken();
		
		String token = UUID.randomUUID().toString();
		
		verificationToken.setToken(token);
		verificationToken.setCreatedDate(new Date());
		verificationToken.setUser(user);
		
		verificationTokenRepository.save(verificationToken);

		MimeMessage message = mailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setTo(user.getEmail());
			helper.setSubject("Registration Verification");
			helper.setText("Please click here http://localhost:8080/confirmAccount?token=" + token + " to verify your account.");
			mailSender.send(message);
		} catch (Exception ex) {
			throw ex;
		}
	}

}
