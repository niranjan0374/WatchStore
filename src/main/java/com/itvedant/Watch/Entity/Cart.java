package com.itvedant.Watch.Entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.validator.constraints.Mod10Check;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;


@Entity
@EntityListeners(AuditingEntityListener.class)
public class Cart {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String status;
	private Integer total_price;
	
	@ManyToMany(mappedBy = "cart")
	private List<Watch> watch;
	
	
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getTotal_price() {
		return total_price;
	}

	public void setTotal_price(Integer total_price) {
		this.total_price = total_price;
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
	
	

}
