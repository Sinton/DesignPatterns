package edu.zjut.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CoinPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private double[] coins = {0.5, 1.0, 5.0, 10.0};
	// 投币标签
	private JLabel labelCoinSlot;
	// 当前可用金额标签
	private JLabel labelCurrentMoney;
	// 当前可用金额文本框
	public JTextField textCurrentMoney;
	// 退币按钮
	private JButton btnCoinsReturn;
	// 投币面额下拉菜单
	private JComboBox<String> boxCoins;
	
	public CoinPanel(final TipPanel panelTip) {
		setLayout(new GridLayout(0, 5));
		labelCoinSlot = new JLabel("投币口：");
		labelCurrentMoney = new JLabel("当前可用金额：");
		textCurrentMoney = new JTextField(5);
		textCurrentMoney.setText("0");
		textCurrentMoney.setEditable(false);
		btnCoinsReturn = new JButton("退币");
		boxCoins = new JComboBox<String>();
		for (int i = 0; i < coins.length; i++)
			boxCoins.addItem(String.valueOf(coins[i]));
		
		boxCoins.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent even) {
				setCurrentMoney(false, 0.0);
				panelTip.labelTakeDrinksTip.setText("已投币，请选购");
			}
		});
		
		btnCoinsReturn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent even) {
				textCurrentMoney.setText("0.0");
				Launch.machine.backMoney();
				if (getCurrentMoney() == 0.0) {
					panelTip.labelTakeDrinksTip.setText("没有可用金额无法退币，请先投币");
				}
			}
		});
		
		add(labelCurrentMoney);
		add(textCurrentMoney);
		add(labelCoinSlot);
		add(boxCoins);
		add(btnCoinsReturn);
	}
	
	/**
	 * 设置当前可用金额
	 * @param isPay
	 * @param price
	 */
	public void setCurrentMoney(boolean isPay, double price) {
		double totalCoin = getCurrentMoney();
		if (!isPay) {
			totalCoin += Double.valueOf(boxCoins.getSelectedItem().toString());
			textCurrentMoney.setText(String.valueOf(totalCoin));
			Launch.machine.insertMoney();
		}else {
			totalCoin -= price;
			textCurrentMoney.setText(String.valueOf(totalCoin));
		}
	}
	
	/**
	 * 获取当前可用金额
	 * @return
	 */
	public double getCurrentMoney() {
		return Double.valueOf(textCurrentMoney.getText());
	}
}