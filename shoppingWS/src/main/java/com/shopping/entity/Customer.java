package com.shopping.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="customer")
public class Customer {

	@Id
	Integer customerid;
	String customername;
	String itembought;
	LocalDate shoppingdate;
	
	public Integer getCustomerid() {
		return customerid;
	}
	public void setCustomerid(Integer customerid) {
		this.customerid = customerid;
	}
	public String getCustomername() {
		return customername;
	}
	public void setCustomername(String customername) {
		this.customername = customername;
	}
	public String getItembought() {
		return itembought;
	}
	public void setItembought(String itembought) {
		this.itembought = itembought;
	}
	public LocalDate getShoppingdate() {
		return shoppingdate;
	}
	public void setShoppingdate(LocalDate shoppingdate) {
		this.shoppingdate = shoppingdate;
	}
}
