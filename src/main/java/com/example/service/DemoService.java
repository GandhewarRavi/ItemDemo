package com.example.service;

import com.example.entity.Item;
import com.example.resource.ItemRequestBody;

public interface DemoService {
	
	public Long getUniqueRecords();
	
	public Item updateTitle(ItemRequestBody item);

}
