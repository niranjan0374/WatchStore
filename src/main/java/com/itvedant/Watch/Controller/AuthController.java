package com.itvedant.Watch.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itvedant.Watch.DAO.LoginDAO;
import com.itvedant.Watch.DAO.RegisterUserDAO;
import com.itvedant.Watch.Service.UserService;

@Controller
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody RegisterUserDAO registerUserDAO){
		return ResponseEntity.ok(this.userService.registerUser(registerUserDAO));
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginDAO loginDAO ){
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginDAO.getEmail(), loginDAO.getPassword())
				);
			
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return ResponseEntity.ok("User Logged In !!!");
	}
}
