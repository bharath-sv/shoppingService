package com.shopping.repository;

import org.springframework.data.repository.CrudRepository;

import com.shopping.dto.ItemDTO;
import com.shopping.entity.Item;

public interface ItemRepository extends CrudRepository<Item, Integer>  {

	
}
