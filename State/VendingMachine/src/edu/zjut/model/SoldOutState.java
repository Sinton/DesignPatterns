package edu.zjut.model;

import edu.zjut.view.Launch;

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
		Launch.panelTip.labelTakeDrinksTip.setText("已投币,请选购");
		System.out.println("SoldOut----已投币,请选购");
		machine.setState(machine.getHasMoneyState());
	}

	@Override
	public void backMoney() {
		Launch.panelTip.labelTakeDrinksTip.setText("您未投币,请先投币");
		System.out.println("SoldOut----您未投币,请先投币");
	}

	@Override
	public void turnCrank() {
		Launch.panelTip.labelTakeDrinksTip.setText("已经售罄");
		System.out.println("饮料已售罄");
	}

	@Override
	public void dispense() {
		throw new IllegalStateException("非法状态！");
	}
}