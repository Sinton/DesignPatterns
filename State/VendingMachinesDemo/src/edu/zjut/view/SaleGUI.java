package edu.zjut.view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import edu.zjut.model.SaleMachine;

public class SaleGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private static final CoinPanel panelCoinSlot = new CoinPanel();
	private static final TipPanel panelTip = new TipPanel();
	
	private JPanel panelDrinkStore = null;
	private JButton btnOrange = null;
	private JButton btnBeer = null;
	private JButton btnCoffee = null;
	private double orangePrice = 2.5;
	private double beerPrice = 3.0;
	private double coffeePrice = 4.0;
	private int orangeNum = 3;
	private int beerNum = 3;
	private int coffeeNum = 4;
	
	public SaleGUI() {
		
		panelDrinkStore = new JPanel(new GridLayout(0, 3));
		
		ImageIcon imgOrange = new ImageIcon("resource/orange.jpg");
		ImageIcon imgBeer = new ImageIcon("resource/beer.jpeg");
		ImageIcon imgCoffee = new ImageIcon("resource/coffee.jpeg");

		btnOrange = new JButton("橙汁价格 :2.5 元", imgOrange);
		btnBeer = new JButton("啤酒价格 : 3 元", imgBeer);
		btnCoffee = new JButton("咖啡价格 :4 元", imgCoffee);

		btnOrange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				double money = Double.valueOf(panelCoinSlot.textCurrentMoney.getText());
				Launch.machine.turnCrank();
				if (money > 0) {
					if (checkBuyOrange()) {
						SaleMachine client = new SaleMachine();
						String back = client.buy("Orange", money);
						// 修改饮料库存
						setOrangeNum(getOrangeNum() - 1);
						panelCoinSlot.setCurrentMoney(true, orangePrice);
						// 修改自动售货机当前状态
						Launch.machine.setState(Launch.machine.getSoldState());
						// Launch.machine.turnCrank();
						// 修改UI界面提示
						panelTip.labelTakeDrinksTip.setText(back);
					} else {
						// 饮料已售完或暂时不能找钱，需要提示客户
						panelTip.labelTakeDrinksTip.setText("已经售罄");
						Launch.machine.setState(Launch.machine.getSoldOutState());
						//Launch.machine.turnCrank();
					}
				} else {
					panelTip.labelTakeDrinksTip.setText("目前无可用金额，请先投币！");
				}
			}
		});

		btnBeer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				double money = Double.valueOf(panelCoinSlot.textCurrentMoney.getText());
				Launch.machine.turnCrank();
				if (money > 0) {
					if (checkBuyBeer()) {
						SaleMachine client = new SaleMachine();
						String back = client.buy("Beer", money);
						// 修改饮料库存
						setBeerNum(getBeerNum() - 1);
						panelCoinSlot.setCurrentMoney(true, beerPrice);
						// 修改自动售货机当前状态
						Launch.machine.setState(Launch.machine.getSoldState());
						// Launch.machine.turnCrank();
						// 修改UI界面提示
						panelTip.labelTakeDrinksTip.setText(back);
					} else {
						// 饮料已售完或暂时不能找钱，需要提示客户
						panelTip.labelTakeDrinksTip.setText("已经售罄");
						Launch.machine.setState(Launch.machine.getSoldOutState());
						//Launch.machine.turnCrank();
					}
				} else
					panelTip.labelTakeDrinksTip.setText("目前无可用金额，请先投币！");
			}
		});

		btnCoffee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				double money = Double.valueOf(panelCoinSlot.textCurrentMoney.getText());
				Launch.machine.turnCrank();
				if (money > 0) {
					if (checkBuyCoffee()) {
						SaleMachine client = new SaleMachine();
						String back = client.buy("Coffee", money);
						// 修改饮料库存
						setCoffeeNum(getCoffeeNum() - 1);
						panelCoinSlot.setCurrentMoney(true, coffeePrice);
						// 修改自动售货机当前状态
						Launch.machine.setState(Launch.machine.getSoldState());
						// Launch.machine.turnCrank();
						// 修改UI界面提示
						panelTip.labelTakeDrinksTip.setText(back);
					} else {
						// 饮料已售完或暂时不能找钱，需要提示客户
						panelTip.labelTakeDrinksTip.setText("已经售罄");
						Launch.machine.setState(Launch.machine.getSoldOutState());
						//Launch.machine.turnCrank();
					}
				} else
					panelTip.labelTakeDrinksTip.setText("目前无可用金额，请先投币！");
			}
		});

		// 设置按钮的背景色
		btnOrange.setBackground(Color.WHITE);
		btnBeer.setBackground(Color.WHITE);
		btnCoffee.setBackground(Color.WHITE);

		// 设置按钮文本的位置
		btnOrange.setHorizontalTextPosition(SwingConstants.CENTER);
		btnBeer.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCoffee.setHorizontalTextPosition(SwingConstants.CENTER);
		btnOrange.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnBeer.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnCoffee.setVerticalTextPosition(SwingConstants.BOTTOM);

		// 设置按钮提示信息
		btnOrange.setToolTipText("点击选择橙汁");
		btnBeer.setToolTipText("点击选择啤酒");
		btnCoffee.setToolTipText("点击选择咖啡");

		// 添加到面板中
		panelDrinkStore.add(btnOrange);
		panelDrinkStore.add(btnBeer);
		panelDrinkStore.add(btnCoffee);

		add(panelCoinSlot, BorderLayout.NORTH);
		add(panelDrinkStore, BorderLayout.CENTER);
		add(panelTip, BorderLayout.SOUTH);

	}

	/**
	 * 检查橙汁是否可售
	 */
	public boolean checkBuyOrange() {
		return getOrangeNum() > 0 && orangePrice <= panelCoinSlot.getCurrentMoney()? true : false;
	}
	
	/**
	 * 检查啤酒是否可售
	 */
	public boolean checkBuyBeer() {
		return getBeerNum() > 0 && beerPrice <= panelCoinSlot.getCurrentMoney() ? true : false;
	}
	
	/**
	 * 检查咖啡是否可售
	 */
	public boolean checkBuyCoffee() {
		return getCoffeeNum() > 0 && coffeePrice <= panelCoinSlot.getCurrentMoney() ? true : false;
	}

	public void setBtnOrange(JButton btnOrange) {
		this.btnOrange = btnOrange;
	}

	public JButton getBtnOrange() {
		return btnOrange;
	}

	public void setBtnBeer(JButton btnBeer) {
		this.btnBeer = btnBeer;
	}

	public JButton getBtnBeer() {
		return btnBeer;
	}

	public void setBtnCoffee(JButton btnCoffee) {
		this.btnCoffee = btnCoffee;
	}

	public JButton getBtnCoffee() {
		return btnCoffee;
	}

	public int getOrangeNum() {
		return orangeNum;
	}

	public void setOrangeNum(int orangeNum) {
		this.orangeNum = orangeNum;
	}

	public int getBeerNum() {
		return beerNum;
	}

	public void setBeerNum(int beerNum) {
		this.beerNum = beerNum;
	}

	public int getCoffeeNum() {
		return coffeeNum;
	}

	public void setCoffeeNum(int coffeeNum) {
		this.coffeeNum = coffeeNum;
	}
}