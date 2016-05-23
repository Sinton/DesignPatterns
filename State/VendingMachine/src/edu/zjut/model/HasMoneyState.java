package edu.zjut.model;

import edu.zjut.view.Launch;

/** 
 * 已投币状态 
 */
public class HasMoneyState implements State {

	private VendingMachine machine;

	public HasMoneyState(VendingMachine machine) {
		this.machine = machine;
	}

	@Override
	public void insertMoney() {
		Launch.panelTip.labelTakeDrinksTip.setText("您又进行了投币");
		System.out.println("您又进行了投币");
	}

	@Override
	public void backMoney() {
		Launch.panelTip.labelTakeDrinksTip.setText("退币成功");
		System.out.println("退币成功");
		machine.setState(machine.getNoMoneyState());
	}

	@Override
	public void turnCrank() {
		System.out.println("手柄已转动,等待出货，并且判断是否可出货");
		if (machine.getCount() > 0) {
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