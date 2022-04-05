package com.example.controller;

import java.util.List;

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
	
	@Autowired
	DemoService service;
	
	@GetMapping(value="/countendpoint")
	public List<Item> getUniqueItems(){
		
		return service.getUniqueRecords();
		
	}
	
	@PostMapping("/update")
	public Item getUpdateItem(@RequestBody ItemRequestBody item) {
		
		return service.updateTitle(item);
		
		
	}

}
