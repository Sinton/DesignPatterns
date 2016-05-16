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
	private ArrayList<Drink> drinkRepository = new ArrayList<Drink>();
	
	/**
	 * 初始化饮料的集合
	 * @return
	 */
	public void setDrinks() {
		String JsonContext = new ReadJson().readJsonFile(FILE_Name);
		String name;
		String url;
		double price;
		int count;
		JSONArray jsonArray = new JSONArray(JsonContext);
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			name = jsonObject.getString("name");
			url = jsonObject.getString("url");
			price = jsonObject.getDouble("price");
			count = jsonObject.getInt("count");
			drinkRepository.add(new Drink(name, url, price, count));
		}
	}
	
	/**
	 * 获取全部饮料集合
	 * @return
	 */
	public ArrayList<Drink> getDrinks() {
		return drinkRepository;
	}
}