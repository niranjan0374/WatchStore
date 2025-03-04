package com.itvedant.Watch.Entity;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.rest.core.annotation.RestResource;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
@EntityListeners(AuditingEntityListener.class)

public class Watch {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private Integer id;
	private String product_name;
	private String description;
	private Float price;
	private String category;
	private String image_url;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	@RestResource(path = "watchCategory", rel = "category")
	private Category category2;
	
	
	@ManyToMany
	@JoinTable(
			name = "watch_cart",
			joinColumns = @JoinColumn(name = "watch_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "cart_id", referencedColumnName = "id")
			)
	@RestResource(path = "watchCart", rel = "cart"	)
	private List<Cart> cart;
	
	
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	@CreatedDate
	private LocalDateTime created_at;
	@LastModifiedDate
	private LocalDateTime updated_at;
	
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "Watch [id=" + id + ", product_name=" + product_name + ", description=" + description + ", price="
				+ price + ", category=" + category + ", image_url=" + image_url + ", created_at=" + created_at
				+ ", updated_at=" + updated_at + "]";
	}
	
	
	
	

}
