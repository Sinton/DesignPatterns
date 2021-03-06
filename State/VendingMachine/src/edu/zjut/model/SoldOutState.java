package edu.zjut.model;

/** 
 * 售罄状态 
 */
public class SoldOutState implements State {
	
	private VendingMachine machine;

	public SoldOutState(VendingMachine machine) {
		this.machine = machine;
	}

	@Override
	public void insertMoney() {
		System.out.println("投币成功");
		machine.setState(machine.getHasMoneyState());
	}

	@Override
	public void backMoney() {
		System.out.println("您未投币,请先投币");
	}

	@Override
	public void turnCrank() {
		System.out.println("商品已售罄");
	}

	@Override
	public void dispense() {
		throw new IllegalStateException("非法状态！");
	}
}