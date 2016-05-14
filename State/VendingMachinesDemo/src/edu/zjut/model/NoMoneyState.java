package edu.zjut.model;

/** 
 * 未投币状态 
 */  
public class NoMoneyState implements State {

	private VendingMachine machine;

	public NoMoneyState(VendingMachine machine) {
		this.machine = machine;
	}

	@Override
	public void insertMoney() {
		System.out.println("投币成功");
		machine.setState(machine.getHasMoneyState());
	}

	@Override
	public void backMoney() {
		System.out.println("您未投币，想退钱？...");
	}

	@Override
	public void turnCrank() {
		System.out.println("您未投币，想拿东西么？...");
	}

	@Override
	public void dispense() {
		throw new IllegalStateException("非法状态！");
	}
}