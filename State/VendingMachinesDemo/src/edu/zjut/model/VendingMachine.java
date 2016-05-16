package edu.zjut.model;

/** 
 * 自动售货机 
 */  
public class VendingMachine {
	
	private State noMoneyState;
	private State hasMoneyState;
	private State soldState;
	private State soldOutState;
	private State currentState = noMoneyState;

	// 某种饮料的库存
	private int count = 0;

	public VendingMachine() {
		noMoneyState = new NoMoneyState(this);
		hasMoneyState = new HasMoneyState(this);
		soldState = new SoldState(this);
		soldOutState = new SoldOutState(this);
		currentState = noMoneyState;
	}

	public void insertMoney() {
		currentState.insertMoney();
	}

	public void backMoney() {
		currentState.backMoney();
	}

	public void turnCrank() {
		currentState.turnCrank();
		if (currentState == soldState)
			currentState.dispense();
	}

	public void dispense() {
		System.out.println("售出一瓶饮料");
		if (count != 0)
			count -= 1;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	public int getCount() {
		return count;
	}
	
	public void setState(State state) {
		this.currentState = state;
	}
	
	public State getNoMoneyState(){
		return noMoneyState;
	}

	public State getHasMoneyState() {
		return hasMoneyState;
	}

	public State getSoldState() {
		return soldState;
	}

	public State getSoldOutState() {
		return soldOutState;
	}
}
