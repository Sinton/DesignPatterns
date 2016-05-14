package edu.zjut.model;

public interface State {

	/**
	 * 投币
	 */
	public void insertMoney();

	/**
	 * 退币
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