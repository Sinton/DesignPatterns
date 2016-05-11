package edu.zjut.javabean;

public class Drink {

	private String name;
	private String url;
	private Integer price;
	private Integer count;
	
	public Drink(String name, String url, Integer price, Integer count) {
		setName(name);
		setUrl(url);
		setPrice(price);
		setCount(count);
	}
	
	public String getName() {
		return name;
	}
	
	private void setName(String name) {
		this.name = name;
	}
	
	public String getUrl() {
		return url;
	}
	
	private void setUrl(String url) {
		this.url = url;
	}
	
	public Integer getPrice() {
		return price;
	}
	
	private void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getCount() {
		return count;
	}

	private void setCount(Integer count) {
		this.count = count;
	}
}