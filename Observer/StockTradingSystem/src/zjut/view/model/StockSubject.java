package zjut.view.model;

import java.util.ArrayList;

public class StockSubject implements Subject {
	
	/**
	 * 股票开盘价格
	 */
	public static final double OPENING_PRICE = 100;
	/**
	 * 股票价格变动列表
	 */
	public static ArrayList<Double> priceList = new ArrayList<Double>();
	
	/**
	 * 全部观察者列表
	 */
	ArrayList<Observer> observers = new ArrayList<Observer>();

	static {
		initPriceList();
	}
	
	/**
	 * 初始化价格列表
	 */
	public static void initPriceList() {
		for (int i = 0; i < 23; i++)
			priceList.add(OPENING_PRICE * (0.9 + 0.2 * Math.random()));
	}
	
	@Override
	public void notifObservers() {
		for (Observer observer : observers) {
			observer.update();
		}
	}

	@Override
	public void addObserver(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void removeObserver(Observer observer) {
		if (observers.contains(observer)) {
			observers.remove(observer);
		}
	}
}