package com.example.service;

import java.util.List;

import com.example.entity.Item;
import com.example.resource.ItemRequestBody;

public interface DemoService {
	
	public List<Item> getUniqueRecords();
	
	public Item updateTitle(ItemRequestBody item);

}
