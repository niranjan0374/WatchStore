package com.itvedant.Watch.Service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.itvedant.Watch.DAO.CheckOTPDAO;
import com.itvedant.Watch.DAO.SendEmailDAO;
import com.itvedant.Watch.DAO.UpdatePasswordDAO;
import com.itvedant.Watch.Entity.User;
import com.itvedant.Watch.Repository.UserRepository;

@Service
public class UpdateUserPasswordService {
	
	@Autowired
	private JavaMailSender emailSender;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	
	
	Random random = new Random();
	
	int otp = 0;
	
	
	public User updatePassword(UpdatePasswordDAO updatePasswordDAO, String email ) {
		User user = new User();
		
		user = this.userRepository.findByEmail(email).orElse(null);
		
		if(updatePasswordDAO.getPassword().equals(updatePasswordDAO.getConfirmPassword())) {
			
			user.setPassword(passwordEncoder.encode(updatePasswordDAO.getPassword()));
			
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password and Confirm Password does" + "not matched");
			
			
		}
		
		this.userRepository.save(user);
		
		return user;
	}
	
	public String sendEmail(SendEmailDAO sendEmailDAO) {
		
		if(this.userRepository.findByEmail(sendEmailDAO.getEmail()).isPresent()) {
		
        SimpleMailMessage message = new SimpleMailMessage();
		
		otp = random.nextInt(1000);
		
		message.setFrom("niranjantarlekar0374@gmail.com");
		message.setTo(sendEmailDAO.getEmail());
		message.setSubject("OTP Verification");
		message.setText(" OTP is " + this.otp);
		
		emailSender.send(message);
		
		return "Email Send";
	} else {
		
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This email is not registered");
		
		}
	
	}
	
	public String checkOTP(CheckOTPDAO checkOTPDAO) {
		if(this.otp == checkOTPDAO.getOtp()) {
			return "OTP matched";
		} else {
			return "OTP not matched";
		}
	}
	
	
	}
	
	

