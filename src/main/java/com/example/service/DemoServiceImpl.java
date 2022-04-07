package com.example.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.controller.ItemController;
import com.example.entity.Item;
import com.example.exception.DemoException;
import com.example.resource.ItemRequestBody;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service
public class DemoServiceImpl implements DemoService {
	Logger logger =LoggerFactory.getLogger(ItemController.class);
	@Override
	public Long getUniqueRecords() {
     
		List<Item> getAllItems = getAllItemData();
		Long countOfUniqueRecards=getAllItems.stream().filter(distinctByKeys(Item::getUserId)).count();
		logger.info("countOfUniqueRecards :", countOfUniqueRecards);
		return countOfUniqueRecards;
	}

	private <T> Predicate<T> distinctByKeys(final Function<? super T, ?>... keyExtractors) {
		final Map<List<?>, Boolean> seen = new ConcurrentHashMap<>();

		return t -> {
			final List<?> keys = Arrays.stream(keyExtractors).map(ke -> ke.apply(t)).collect(Collectors.toList());

			return seen.putIfAbsent(keys, Boolean.TRUE) == null;
		};
	}

	private List<Item> getAllItemData() {

		DefaultHttpClient httpClient = null;
		try {
			httpClient = new DefaultHttpClient();
			HttpResponse response = getResponse("http://jsonplaceholder.typicode.com/posts", httpClient);
			String outPut = readData(response);
			System.out.println(outPut);
			Gson gson = new Gson();
			List<Item> fromJson = gson.fromJson(outPut, new TypeToken<List<Item>>() {
			}.getType());
			return fromJson;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		return null;
	}

	private HttpResponse getResponse(String url, DefaultHttpClient httpClient) throws IOException {
		try {

			HttpGet httpGet = new HttpGet(url);
			httpGet.addHeader("accept", "application/json");
			HttpResponse response = httpClient.execute(httpGet);
			return response;
		} catch (IOException e) {
			throw e;
		}
	}

	private String readData(HttpResponse response) throws Exception {
		BufferedReader reader = null;
		try {

			reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer data = new StringBuffer();
			char[] dataLength = new char[1024];
			int read;
			while (((read = reader.read(dataLength)) != -1)) {
				data.append(dataLength, 0, read);
			}
			return data.toString();
		} finally {
			if (reader != null)
				reader.close();
		}
	}

	@Override
	public Item updateTitle(ItemRequestBody itemRequeast) {
		
		Item item =forUpdateData(itemRequeast.getUserId());
		Optional<Item> optionalItem =Optional.ofNullable(item);
		if(optionalItem.isPresent()) {
			item.setUserId(itemRequeast.getUserId());
			item.setBody(itemRequeast.getBody());
			item.setTitle(itemRequeast.getTitle());
			logger.info("updated  :", itemRequeast.getUserId());
			return item;
		}
		else {
			logger.error("record is not found");
			throw new DemoException();
		}
	}

	private Item forUpdateData(String userId) {

		DefaultHttpClient httpClient = null;
		try {
			
			Item item =new Item();
			httpClient = new DefaultHttpClient();
			HttpResponse response = getResponse("http://jsonplaceholder.typicode.com/posts", httpClient);
			String outPut = readData(response);
			System.out.println(outPut);
			Gson gson = new Gson();
			List<Item> fromJson = gson.fromJson(outPut, new TypeToken<List<Item>>() {
			}.getType());
			
			return fromJson.get(Integer.parseInt(userId));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		return null;
	}
}