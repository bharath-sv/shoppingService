package com.shopping.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.dto.CustomerDTO;
import com.shopping.dto.CustomerResponseDTO;
import com.shopping.dto.ItemDTO;
import com.shopping.entity.Item;
import com.shopping.exception.ValidationException;
import com.shopping.entity.Customer;
import com.shopping.repository.CustomerRepository;
import com.shopping.repository.ItemRepository;
import com.shopping.utility.Validation;

@Service
public class ShoppingServiceImpl implements  ShoppingService {
	
	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	CustomerRepository  customerRepository;
	
	@Override
	public List<ItemDTO> getAllItem() throws Exception{
		
		Iterable<Item> itemDbList=itemRepository.findAll();
		List<ItemDTO> itemList= new ArrayList<>();
		itemDbList.forEach(itemDb -> {
			ItemDTO item = new ItemDTO();
			item.setItemId(itemDb.getItemid());
			item.setItemName(itemDb.getItemName());
			item.setQuantity(itemDb.getQuantity());
			item.setPrice(itemDb.getPrice());
			itemList.add(item);
		});
		return itemList;
	}
	
	@Override
	public Integer addItem(ItemDTO item) throws Exception {
		if(!Validation.validateId(item.getItemId())) {
			 throw new ValidationException("Invalid Id");
		}
		if(!Validation.validateName(item.getItemName())) {
			 throw new ValidationException("Invalid Id");
		}
		if(!Validation.validateNumber(item.getPrice())) {
			 throw new ValidationException("Invalid Price");
		}
		if(!Validation.validateNumber(item.getQuantity())) {
			 throw new ValidationException("Invalid Quantity");
		}
		
		Item itemDb= new Item();
		itemDb.setItemid(item.getItemId());
		itemDb.setItemName(item.getItemName());
		itemDb.setQuantity(item.getQuantity());
		itemDb.setPrice(item.getPrice());
		Item added=itemRepository.save(itemDb);
		return added.getItemid();
	}
	
	@Override
	public ItemDTO getItem(Integer id) throws Exception{
		if(!Validation.validateId(id)) {
			 throw new ValidationException("Invalid Item Id");
		}
		Optional<Item> optional = itemRepository.findById(id);
		Item itemDb = optional.orElseThrow(() -> new Exception("Item not found"));
		ItemDTO item = new ItemDTO();
		item.setItemId(itemDb.getItemid());
		item.setItemName(itemDb.getItemName());
		item.setQuantity(itemDb.getQuantity());
		item.setPrice(itemDb.getPrice());
		return item;
	}
	
	@Override
	public String updateQuantityOrPrice(ItemDTO item) throws Exception {
		Boolean updated=false;
		if(!Validation.validateId(item.getItemId())) {
			 throw new ValidationException("Invalid Item Id");
		}
		Optional<Item> optional = itemRepository.findById(item.getItemId());
		Item itemDb = optional.orElseThrow(() -> new ValidationException("Item not found"));
		if(item.getPrice()!=null) {
			if(!Validation.validateNumber(item.getPrice())) {
				 throw new ValidationException("Invalid Price");
			}
			itemDb.setPrice(item.getPrice());
			updated=true;
		}
		if(item.getQuantity()!=null) {
			if(!Validation.validateNumber(item.getQuantity())) {
				 throw new ValidationException("Invalid Quantity");
			}
			itemDb.setQuantity(item.getQuantity());
			updated=true;
		}
		if(updated==true) {
			itemRepository.save(itemDb);
			return "updated successfully";
		}else {
			throw new ValidationException("Invalid Input");
		}
		
	}
	
	@Override
	public void deleteItem(Integer itemId) throws Exception {
		if(!Validation.validateId(itemId)) {
			 throw new ValidationException("Invalid Item Id");
		}
		Optional<Item> optional = itemRepository.findById(itemId);
		optional.orElseThrow(() -> new Exception("Item not found"));
		itemRepository.deleteById(itemId);
	}
	
	@Override
	public List<CustomerResponseDTO> getAllCustomer()  throws Exception{
		
		Iterable<Customer> customerDbList=customerRepository.findAll();
		List<CustomerResponseDTO> customerList= new ArrayList<>();
		customerDbList.forEach(customerDb -> {
			CustomerResponseDTO customer = new CustomerResponseDTO();
			customer.setCustomerId(customerDb.getCustomerid());
			customer.setCustomerName(customerDb.getCustomername());
			customer.setItemBought(customerDb.getItembought());
			customer.setShoppingDate(customerDb.getShoppingdate());
			customerList.add(customer);
		});
		return customerList;
		
	}
	
	@Override
	public Float addCustomer(CustomerDTO customer)  throws Exception{
		if(!Validation.validateId(customer.getCustomerId())) {
			 throw new ValidationException("Invalid Customer Id");
		}
		
		if(!Validation.validateName(customer.getCustomerName())) {
			 throw new ValidationException("Invalid Name");
		}

		 Float totalPrice=0.0f;
		 List<ItemDTO> itemsDbList= getAllItem();
		 List<String> itemNameDBList=new ArrayList<>();
		 for(ItemDTO item:itemsDbList){
			 itemNameDBList.add(item.getItemName());
		 }
		 for(String customerItem:customer.getItemBought()) {
			 if(!Validation.validateName(customerItem)) {
				 throw new ValidationException("Invalid Item Name");
			 }
			 if(!itemNameDBList.contains(customerItem)){
				 throw new ValidationException("Item not found");
			 }
		 }
		 for(ItemDTO item:itemsDbList) {
			 for(String customerItem:customer.getItemBought()) {
				 if(customerItem.equals(item.getItemName())){
					 
					 Customer Customer=new Customer();
					 Customer.setCustomerid(customer.getCustomerId());
					 Customer.setCustomername(customer.getCustomerName());
					 Customer.setItembought(customerItem);
					 Customer.setShoppingdate(customer.getShoppingDate());
					 Integer newQuantity= item.getQuantity()-1;
					 if(newQuantity>0) {
						 item.setQuantity(newQuantity);
						 updateQuantityOrPrice(item);
						 customerRepository.save(Customer);
						 totalPrice=totalPrice+item.getPrice();
					 }else {
						 throw new ValidationException("Item quantity is zero, please try later");
					 }
				 }
			 }
		 }
 
		 return totalPrice;

			
	}
}
