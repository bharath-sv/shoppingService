package com.shopping.service;

import java.util.List;

import com.shopping.dto.CustomerDTO;
import com.shopping.dto.CustomerResponseDTO;
import com.shopping.dto.ItemDTO;

public interface ShoppingService {
	
	public List<ItemDTO> getAllItem()  throws Exception;
	public Integer addItem(ItemDTO item)  throws Exception ;
	public ItemDTO getItem(Integer id) throws Exception;
	public String updateQuantityOrPrice(ItemDTO item)  throws Exception ;
	public void deleteItem(Integer itemId) throws Exception;
	public List<CustomerResponseDTO> getAllCustomer()  throws Exception;
	public Float addCustomer(CustomerDTO customer)  throws Exception;
}
