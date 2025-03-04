package com.itvedant.Watch.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itvedant.Watch.DAO.CheckOTPDAO;
import com.itvedant.Watch.DAO.SendEmailDAO;
import com.itvedant.Watch.DAO.UpdatePasswordDAO;
import com.itvedant.Watch.Service.UpdateUserPasswordService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/users/update_password")
public class UpdateUserController {
	
	
	@Autowired
	private UpdateUserPasswordService updateUserPasswordService;
	
	
	@PutMapping("")
	public ResponseEntity<?> updatePassword(@RequestBody UpdatePasswordDAO updatePasswordDAO, HttpSession session){
		
	    String email = (String)	session.getAttribute("email"); 
		
		return ResponseEntity.ok(this.updateUserPasswordService.updatePassword(updatePasswordDAO, email));
		
	}
	
	
	@PostMapping("/email")
	public ResponseEntity<?> send(@RequestBody SendEmailDAO sendEmailDAO , HttpSession session){
		
		
		session.setAttribute("email", sendEmailDAO.getEmail());
		
	
		
		return ResponseEntity.ok(this.updateUserPasswordService.sendEmail(sendEmailDAO));
	}
	
	
	@PostMapping("/check")
	public ResponseEntity<?> checkotp(@RequestBody  CheckOTPDAO checkOTPDAO){
		return ResponseEntity.ok(this.updateUserPasswordService.checkOTP(checkOTPDAO));
		
	}
	
	
	
	

}
