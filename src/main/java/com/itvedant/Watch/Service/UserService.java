package com.itvedant.Watch.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.itvedant.Watch.DAO.RegisterUserDAO;
import com.itvedant.Watch.Entity.User;
import com.itvedant.Watch.Repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public User registerUser(RegisterUserDAO registerUserDAO) {
		if(this.userRepository.findByEmail(registerUserDAO.getEmail()).isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User is already registered with this email");	
		}
		
		User user = new User();
		
		user.setFirst_name(registerUserDAO.getFirst_name());
		user.setLast_name(registerUserDAO.getLast_name());
		user.setEmail(registerUserDAO.getEmail());
		user.setPassword(passwordEncoder.encode(registerUserDAO.getPassword()));
		user.setMobile(registerUserDAO.getMobile());
		user.setRoles(registerUserDAO.getRoles());
		this.userRepository.save(user);
		
		return user;
	}
	
	
	

}
