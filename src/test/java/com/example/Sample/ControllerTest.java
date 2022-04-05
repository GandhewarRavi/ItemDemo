package com.example.Sample;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.controller.ItemController;
import com.example.entity.Item;
import com.example.resource.ItemRequestBody;
import com.example.service.DemoService;
import com.example.service.DemoServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ControllerTest {

	@InjectMocks
	ItemController itemController;

	@Mock
	DemoService service;

	Item item = new Item();

	@Test
	public void getUniqueItemsTest() {
		List<Item> lisItem = new ArrayList<>();

		item.setId("1");
		item.setTitle("10");
		item.setBody("wel come to 1800 flowers");
		item.setUserId("ravi");
		lisItem.add(item);
		when(service.getUniqueRecords()).thenReturn(lisItem);
		itemController.getUniqueItems();
		assertEquals(itemController.getUniqueItems(), lisItem);

	}

	@Test
	public void getUpdateItemTest() {

		ItemRequestBody itemRequest = new ItemRequestBody();

		itemRequest.setBody("1800 flowers");
		itemRequest.setTitle("1800 flowers");
		item.setId("1");
		item.setTitle("1800 flowers");
		item.setBody("1800 flowers");
		item.setUserId("ravi");
		when(service.updateTitle(itemRequest)).thenReturn(item);
		itemController.getUpdateItem(itemRequest);
		assertEquals(itemController.getUpdateItem(itemRequest), item);
	}

}
