package edu.zjut.javabean;

public class Drink {
	
	private String name;
	private String url;
	private double price;
	private int count;
	
	public Drink(String name, String url, double price, int count) {
		this.setName(name);
		this.setUrl(url);
		this.setPrice(price);
		this.setCount(count);
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
	
	public double getPrice() {
		return price;
	}
	
	private void setPrice(double price) {
		this.price = price;
	}
	
	public int getCount() {
		return count;
	}
	
	private void setCount(int count) {
		this.count = count;
	}
}