package edu.zjut.model;

/** 
 * 已投入钱的状态 
 */
public class HasMoneyState implements State {

	private VendingMachine machine;

	public HasMoneyState(VendingMachine machine) {
		this.machine = machine;
	}

	@Override
	public void insertMoney() {
		System.out.println("您已经投过币了，无需再投....");
	}

	@Override
	public void backMoney() {
		System.out.println("退币成功");

		machine.setState(machine.getNoMoneyState());
	}

	@Override
	public void turnCrank() {
		System.out.println("你转动了手柄");
		if (machine.getCount() > 1) {
			machine.setState(machine.getSoldState());
		} else {
			machine.setState(machine.getSoldOutState());
		}
	}

	@Override
	public void dispense() {
		throw new IllegalStateException("非法状态！");
	}
}