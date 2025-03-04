package com.itvedant.Watch.Entity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class User implements UserDetails{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String first_name;
	private String last_name;
	private String email;
	private String password;
	private String mobile;
	
	@ElementCollection
	private List<String> roles; 
	
	@OneToOne
	@JoinColumn(name = "address_id")
	@RestResource(path = "userAddress", rel = "address")
	private Address address;
	
	@CreatedDate
	private LocalDateTime created_at;
	@LastModifiedDate
	private LocalDateTime updated_at;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	public LocalDateTime getCreated_at() {
		return created_at;
	}
	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}
	public LocalDateTime getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(LocalDateTime updated_at) {
		this.updated_at = updated_at;
	}
	
	
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", email=" + email
				+ ", password=" + password + ", mobile=" + mobile + ", created_at=" + created_at + ", updated_at="
				+ updated_at + "]";
	}
	
	
	public User(String email, String password,  Collection<? extends GrantedAuthority> authorities ) {
		super();
		this.email = email;
		this.password = password;
		this.authorities = authorities;
		
	}
	
	public User() { }
	
	@Transient
	private Collection<? extends GrantedAuthority> authorities;
	
	
	@Override
			//return DataType 
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return this.authorities;
	}
	@Override
	public String getUsername() {
		return this.email;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
}
