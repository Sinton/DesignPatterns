package edu.zjut.utils;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import edu.zjut.javabean.Drink;

public class Repository {
	
	private static final String FILE_Name = "drink.json";
	
	/**
	 * 全部饮料与其JavaBean的集合
	 */
	public ArrayList<Drink> drinkRepository = new ArrayList<Drink>();
	
	/**
	 * 设置全部饮料的集合
	 * @return
	 */
	public ArrayList<Drink> setDrinkList() {
		String JsonContext = new JsonParse().readJsonFile(FILE_Name);
		String name = null;
		String url = null;
		Integer price = null;
		Integer count = null;
		JSONArray jsonArray = new JSONArray(JsonContext);
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			name = jsonObject.getString("name");
			url = jsonObject.getString("url");
			price = jsonObject.getInt("price");
			count = jsonObject.getInt("count");
			drinkRepository.add(new Drink(name, url, price, count));
		}
		return drinkRepository;
	}
}