package com.itvedant.Watch.DAO;

public class UpdateWatchDAO {
	
	private String product_name;
	private String description;
	private Float price;
	private String category;
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
		return "UpdateWatchDAO [product_name=" + product_name + ", description=" + description + ", price=" + price
				+ ", category=" + category + "]";
	}
	
	
}
