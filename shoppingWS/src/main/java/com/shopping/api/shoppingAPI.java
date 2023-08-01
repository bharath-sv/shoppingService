package com.shopping.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.dto.CustomerDTO;
import com.shopping.dto.CustomerResponseDTO;
import com.shopping.dto.ItemDTO;
import com.shopping.service.ShoppingService;

@RestController
@RequestMapping(value="/shopping")
public class shoppingAPI {

	@Autowired
	ShoppingService shoppingService;
	
	@GetMapping(value = "/hello")
	public ResponseEntity<String> getAllCustomers1() throws Exception {
		return new ResponseEntity<>("SUCCESS BSV", HttpStatus.OK);
	}
	
	@GetMapping(value = "/item")
	public ResponseEntity<List<ItemDTO>> getAllitem() throws Exception {
		List<ItemDTO> itemList=shoppingService.getAllItem();
		return new ResponseEntity<>(itemList, HttpStatus.OK);
	}

	@GetMapping(value = "/item/{id}")
	public ResponseEntity<ItemDTO> getAllitem(@PathVariable Integer id) throws Exception {
		ItemDTO itemDTO=shoppingService.getItem(id);
		return new ResponseEntity<>(itemDTO, HttpStatus.OK);
	}

	@PostMapping(value = "/item")
	public ResponseEntity<Integer> addItem(@RequestBody ItemDTO itemDTO) throws Exception {
		Integer id= shoppingService.addItem(itemDTO);
		return new ResponseEntity<>(id, HttpStatus.OK);
	}

	
	@PutMapping(value = "/item")
	public ResponseEntity<String> update(@RequestBody ItemDTO itemDTO) throws Exception {
		String id= shoppingService.updateQuantityOrPrice(itemDTO);
		return new ResponseEntity<>(id, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/item/{id}")
	public ResponseEntity<String> delete(@PathVariable Integer id) throws Exception {
		shoppingService.deleteItem(id);
		return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
	}
	
	@GetMapping(value = "/customer")
	public ResponseEntity<List<CustomerResponseDTO>> getAllCustomers() throws Exception {
		List<CustomerResponseDTO> customerList=shoppingService.getAllCustomer();
		return new ResponseEntity<>(customerList, HttpStatus.OK);
	}
	
	@PostMapping(value = "/customer")
	public ResponseEntity<Float> addCustomerItem(@RequestBody CustomerDTO customer) throws Exception {
		Float totalPrice=shoppingService.addCustomer(customer);
		return new ResponseEntity<>(totalPrice, HttpStatus.OK);
	}
}

