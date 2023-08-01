package com.shopping.dto;

import java.time.LocalDate;
import java.util.List;

public class CustomerDTO {
	Integer customerId;
	String customerName;
	List<String> itemBought;
	LocalDate shoppingDate;
	
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public List<String>  getItemBought() {
		return itemBought;
	}
	public void setItemBought(List<String>  itemBought) {
		this.itemBought = itemBought;
	}
	public LocalDate getShoppingDate() {
		return shoppingDate;
	}
	public void setShoppingDate(LocalDate shoppingDate) {
		this.shoppingDate = shoppingDate;
	}
}
