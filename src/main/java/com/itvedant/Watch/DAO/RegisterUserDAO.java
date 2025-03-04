package com.itvedant.Watch.DAO;

import java.util.List;

public class RegisterUserDAO {
	
	private String first_name;
	private String last_name;
	private String email;
	private String password;
	private String mobile;
	
	
	private List<String> roles;
	
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	@Override
	public String toString() {
		return "RegisterUserDAO [first_name=" + first_name + ", last_name=" + last_name + ", email=" + email
				+ ", password=" + password + ", mobile=" + mobile + "]";
	}
	
	

}
