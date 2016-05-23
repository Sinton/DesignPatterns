package edu.zjut.model;

import edu.zjut.view.Launch;

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
		Launch.panelTip.labelTakeDrinksTip.setText("已投币,请选购");
		// 测试输出
		System.out.println("已投币,请选购");
		machine.setState(machine.getHasMoneyState());
	}

	@Override
	public void backMoney() {
		Launch.panelTip.labelTakeDrinksTip.setText("您未投币,请先投币");
		System.out.println("您未投币,请先投币");
	}

	@Override
	public void turnCrank() {
		Launch.panelTip.labelTakeDrinksTip.setText("您未投币,无法出售");
		System.out.println("您未投币,无法出售");
	}

	@Override
	public void dispense() {
		throw new IllegalStateException("非法状态！");
	}
}