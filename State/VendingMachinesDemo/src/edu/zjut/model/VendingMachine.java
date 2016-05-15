package edu.zjut.model;

/** 
 * 自动售货机 
 */  
public class VendingMachine {
	
	private State noMoneyState;
	private State hasMoneyState;
	private State soldState;
	private State soldOutState;

	private int count = 0;// 库存
	private State currentState = noMoneyState;

	public VendingMachine(int count) {
		noMoneyState = new NoMoneyState(this);
		hasMoneyState = new HasMoneyState(this);
		soldState = new SoldState(this);
		soldOutState = new SoldOutState(this);

		if (count > 0) {
			this.count = count;
			currentState = noMoneyState;
		}
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
		System.out.println("发出一件商品...");
		if (count != 0)
			count -= 1;
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
