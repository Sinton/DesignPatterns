package edu.zjut.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class DrinkStorePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JButton btnOrange;
	private JButton btnCoke;
	private JButton btnFenda;
	private double orangePrice = 2.5;
	private double cokePrice = 3.0;
	private double coffeePrice = 4.0;
	private int orangeNum = 10;
	private int cokeNum = 10;
	private int coffeeNum = 10;
	private final CoinPanel panelCoinSlot;
	private final TipPanel panelTip;
	
	public DrinkStorePanel(final CoinPanel panelCoinSlot, final TipPanel panelTip) {
		this.panelCoinSlot = panelCoinSlot;
		this.panelTip = panelTip;
		
		ImageIcon imgOrange = new ImageIcon("resource/orange.jpg");
		ImageIcon imgCoke = new ImageIcon("resource/coke.jpg");
		ImageIcon imgFenda = new ImageIcon("resource/fenda.jpg");

		btnOrange = new JButton("橙汁价格 :2.5 元", imgOrange);
		btnCoke = new JButton("可乐价格 : 3 元", imgCoke);
		btnFenda = new JButton("咖啡价格 :4 元", imgFenda);
		
		setLayout(new GridLayout(0, 3));

		btnOrange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				double money = Double.valueOf(panelCoinSlot.textCurrentMoney.getText());
				Launch.machine.turnCrank();
				if (money > 0) {
					if (!checkBuyOrange()) {
						if (checkBuyOrange()) {
							// 修改饮料库存
							setOrangeNum(getOrangeNum() - 1);
							panelCoinSlot.setCurrentMoney(true, orangePrice);
							// 修改自动售货机当前状态
							Launch.machine.setState(Launch.machine.getSoldState());
							// 修改UI界面提示
							panelTip.labelTakeDrinksTip.setText("请取走饮料!");
						} else {
							// 饮料已售完或暂时不能找钱，需要提示客户
							panelTip.labelTakeDrinksTip.setText("已经售罄");
							Launch.machine.setState(Launch.machine.getSoldOutState());
						}
					} else {
						// 修改自动售货机当前状态
						Launch.machine.setState(Launch.machine.getNoMoneyState());
						// 修改UI界面提示
						panelTip.labelTakeDrinksTip.setText("金额不够，请投币");
					}
				} else {
					panelTip.labelTakeDrinksTip.setText("目前无可用金额，请先投币！");
				}
			}
		});

		btnCoke.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				double money = Double.valueOf(panelCoinSlot.textCurrentMoney.getText());
				Launch.machine.turnCrank();
				if (money > 0) {
					if (!checkBuyCoke()) {
						if (checkBuyCoke()) {
							// 修改饮料库存
							setCokeNum(getCokeNum() - 1);
							panelCoinSlot.setCurrentMoney(true, cokePrice);
							// 修改自动售货机当前状态
							Launch.machine.setState(Launch.machine.getSoldState());
							// 修改UI界面提示
							panelTip.labelTakeDrinksTip.setText("请取走饮料!");
						} else {
							// 修改自动售货机当前状态
							Launch.machine.setState(Launch.machine.getNoMoneyState());
							// 修改UI界面提示
							panelTip.labelTakeDrinksTip.setText("金额不够，请投币");
						}
					} else {
						// 饮料已售完或暂时不能找钱，需要提示客户
						panelTip.labelTakeDrinksTip.setText("已经售罄");
						Launch.machine.setState(Launch.machine.getSoldOutState());
					}
				} else
					panelTip.labelTakeDrinksTip.setText("目前无可用金额，请先投币！");
			}
		});

		btnFenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				double money = Double.valueOf(panelCoinSlot.textCurrentMoney.getText());
				Launch.machine.turnCrank();
				if (money > 0) {
					if (!checkIsNullByFenda()) {
						if (checkBuyFenda()) {
							// 修改饮料库存
							setFendaNum(getFendaNum() - 1);
							panelCoinSlot.setCurrentMoney(true, coffeePrice);
							// 修改自动售货机当前状态
							Launch.machine.setState(Launch.machine.getSoldState());
							// 修改UI界面提示
							panelTip.labelTakeDrinksTip.setText("请取走饮料!");
						} else {
							// 修改自动售货机当前状态
							Launch.machine.setState(Launch.machine.getNoMoneyState());
							// 修改UI界面提示
							panelTip.labelTakeDrinksTip.setText("金额不够，请投币");
						}
					} else {
						// 饮料已售完或暂时不能找钱，需要提示客户
						panelTip.labelTakeDrinksTip.setText("已经售罄");
						Launch.machine.setState(Launch.machine.getSoldOutState());
					}
				} else
					panelTip.labelTakeDrinksTip.setText("目前无可用金额，请先投币！");
			}
		});

		// 设置按钮的背景色
		btnOrange.setBackground(Color.WHITE);
		btnCoke.setBackground(Color.WHITE);
		btnFenda.setBackground(Color.WHITE);

		// 设置按钮文本的位置
		btnOrange.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCoke.setHorizontalTextPosition(SwingConstants.CENTER);
		btnFenda.setHorizontalTextPosition(SwingConstants.CENTER);
		btnOrange.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnCoke.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnFenda.setVerticalTextPosition(SwingConstants.BOTTOM);

		// 设置按钮提示信息
		btnOrange.setToolTipText("点击选择橙汁");
		btnCoke.setToolTipText("点击选择可乐");
		btnFenda.setToolTipText("点击选择咖啡");

		// 添加到面板中
		add(btnOrange);
		add(btnCoke);
		add(btnFenda);
	}
	
	/**
	 * 检查橙汁是否可售
	 */
	public boolean checkBuyOrange() {
		return orangePrice <= panelCoinSlot.getCurrentMoney()? true : false;
	}
	
	/**
	 * 检查啤酒是否可售
	 */
	public boolean checkBuyCoke() {
		return cokePrice <= panelCoinSlot.getCurrentMoney() ? true : false;
	}
	
	/**
	 * 检查咖啡是否可售
	 */
	public boolean checkBuyFenda() {
		return coffeePrice <= panelCoinSlot.getCurrentMoney() ? true : false;
	}
	
	/**
	 * 检查芬达存货是否为空
	 * @return
	 */
	public boolean checkIsNullByFenda() {
		return getFendaNum() <= 0 ? true : false;
	}
	
	/**
	 * 检查可乐存货是否为空
	 * @return
	 */
	public boolean checkIsNullByCoke() {
		return getCokeNum() <= 0 ? true : false;
	}
	
	/**
	 * 检查橙汁存货是否为空
	 * @return
	 */
	public boolean checkIsNullByOrange() {
		return getOrangeNum() <= 0 ? true : false;
	}

	public int getOrangeNum() {
		return orangeNum;
	}

	public void setOrangeNum(int orangeNum) {
		this.orangeNum = orangeNum;
	}

	public int getCokeNum() {
		return cokeNum;
	}

	public void setCokeNum(int beerNum) {
		this.cokeNum = beerNum;
	}

	public int getFendaNum() {
		return coffeeNum;
	}

	public void setFendaNum(int coffeeNum) {
		this.coffeeNum = coffeeNum;
	}
}