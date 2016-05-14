package edu.zjut.model;

public interface State {
	
	/**
	 * 放钱
	 */
	public void insertMoney();

	/**
	 * 退钱
	 */
	public void backMoney();

	/**
	 * 转动曲柄
	 */
	public void turnCrank();

	/**
	 * 出商品
	 */
	public void dispense();  
}