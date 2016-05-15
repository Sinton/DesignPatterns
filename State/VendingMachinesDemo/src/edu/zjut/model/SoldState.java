package edu.zjut.model;

/**
 * 出售状态
 */
public class SoldState implements State {
	
	private VendingMachine machine;

	public SoldState(VendingMachine machine) {
		this.machine = machine;
	}

	@Override
	public void insertMoney() {
		System.out.println("正在出货，请勿投币");
	}

	@Override
	public void backMoney() {
		System.out.println("退币成功");
		machine.setState(machine.getNoMoneyState());
	}

	@Override
	public void turnCrank() {
		System.out.println("正在出货，请勿重复转动手柄");
	}

	@Override
	public void dispense() {
		machine.dispense();
		if (machine.getCount() > 0) {
			machine.setState(machine.getNoMoneyState());
		} else {
			System.out.println("商品已经售罄");
			machine.setState(machine.getSoldOutState());
		}
	}
}