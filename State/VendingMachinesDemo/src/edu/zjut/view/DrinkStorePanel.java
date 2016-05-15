package edu.zjut.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import edu.zjut.javabean.Drink;
import edu.zjut.utils.Repository;

public class DrinkStorePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private static ArrayList<Drink> drinks;
	private static Repository repository = new Repository();
	
	public DrinkStorePanel(final CoinPanel panelCoinSlot, final TipPanel panelTip) {
		setLayout(new GridLayout(0, 3));
		initComponent();
		JButton[] btnDrinks = new JButton[drinks.size()];
		ImageIcon[] imgDrinks = new ImageIcon[drinks.size()];
		for (int i = 0; i < drinks.size(); i++) {
			imgDrinks[i] = new ImageIcon(drinks.get(i).getUrl());
			btnDrinks[i] = new JButton(drinks.get(i).getName() + ":" + drinks.get(i).getPrice() + " 元", imgDrinks[i]);
			btnDrinks[i].setActionCommand(String.valueOf(i));
			btnDrinks[i].addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent event) {
					int index = Integer.parseInt(event.getActionCommand());
					double money = Double.valueOf(panelCoinSlot.textCurrentMoney.getText());
					Launch.machine.turnCrank();
					if (money > 0) {
						// 判断该饮料是否还有库存
						if (drinks.get(index).getCount() > 0) {
							// 判断当前金额是否够买该饮料
							if (drinks.get(index).getPrice() <= panelCoinSlot.getCurrentMoney()) {
								// 售出该饮料并修改饮料库存
								drinks.get(index).sale();
								panelCoinSlot.setCurrentMoney(true, drinks.get(index).getPrice());
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
			btnDrinks[i].setBackground(Color.WHITE);
			
			// 设置按钮文本的位置
			btnDrinks[i].setHorizontalTextPosition(SwingConstants.CENTER);
			btnDrinks[i].setVerticalTextPosition(SwingConstants.BOTTOM);
			
			// 设置按钮提示信息
			btnDrinks[i].setToolTipText("点击选择" + drinks.get(i).getName());
			
			// 添加到面板中
			add(btnDrinks[i]);
		}
	}
	
	/**
	 * 初始化自动售货机容器
	 */
	public void initComponent() {
		repository.setDrinks();
		drinks = repository.getDrinks();
	}
}