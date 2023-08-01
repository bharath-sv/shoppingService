package com.shopping.dto;

import java.time.LocalDate;
import java.util.List;

public class CustomerResponseDTO {
	Integer customerId;
	String customerName;
	String itemBought;
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
	public String  getItemBought() {
		return itemBought;
	}
	public void setItemBought(String  itemBought) {
		this.itemBought = itemBought;
	}
	public LocalDate getShoppingDate() {
		return shoppingDate;
	}
	public void setShoppingDate(LocalDate shoppingDate) {
		this.shoppingDate = shoppingDate;
	}
}
