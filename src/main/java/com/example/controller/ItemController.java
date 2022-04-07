package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Item;
import com.example.resource.ItemRequestBody;
import com.example.service.DemoService;

@RestController
@RequestMapping("/ravi")
public class ItemController {
	
	Logger logger =LoggerFactory.getLogger(ItemController.class);
	@Autowired
	DemoService service;
	
	@GetMapping(value="/countendpoint")
	public Long getUniqueItems(){
		
		logger.debug("Controller class of getUniqueItems() method");
		return service.getUniqueRecords();
		
	}
	
	@PostMapping("/update")
	public Item getUpdateItem(@RequestBody ItemRequestBody item) {
		logger.debug("Controller class of getUpdateItem() method");
		return service.updateTitle(item);
		
		
	}

}
