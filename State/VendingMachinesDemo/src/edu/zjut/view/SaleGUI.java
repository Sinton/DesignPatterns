package edu.zjut.view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import edu.zjut.model.HasMoneyState;
import edu.zjut.model.NoMoneyState;
import edu.zjut.model.SaleMachine;
import edu.zjut.model.SoldOutState;
import edu.zjut.model.SoldState;
import edu.zjut.model.State;

public class SaleGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private CoinPanel panelCoinSlot = new CoinPanel();
	//private JPanel panelCoinSlot = null; // 放置标签及输入框
	//private JTextField textCoinSlot = null; // 输入硬币数
	//private JLabel labelCoinSlot = null;
	
	private TipPanel panelTip = new TipPanel();
	//private JPanel panelTip = null; // 放置1个标签提示“取走饮料”
	//private JLabel labelTakeDrinksTip = null; // 提示“取走饮料”

	
	private JPanel panelDrinkStore = null; // 放置3个按钮
	private JButton btnOrange = null;
	private JButton btnBeer = null;
	private JButton btnCoffee = null;
	private int OrangeNum = 3; // 记录橙汁的存货量
	private int BeerNum = 3; // 记录啤酒的存货量
	private int CoffeeNum = 5; // 记录咖啡的存货量
	private int coin50 = 5; // 记录售货机的50分硬币数目
	private int coin100 = 3; // 记录售货机的100分硬币数目
	
	public SaleGUI() {

		panelDrinkStore = new JPanel(new GridLayout(0, 3));

		ImageIcon imgOrange = new ImageIcon("resource/orange.jpg");
		ImageIcon imgBeer = new ImageIcon("resource/beer.jpeg");
		ImageIcon imgCoffee = new ImageIcon("resource/coffee.jpeg");

		// 设置图片尺寸
		btnOrange = new JButton("橙汁价格 :50 美分", imgOrange);
		btnBeer = new JButton("啤酒价格 :50 美分", imgBeer);
		btnCoffee = new JButton("咖啡价格 :100 美分", imgCoffee);

		btnOrange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String str = null;

				// 获取用户投进的硬币
				int money = Integer.parseInt(panelCoinSlot.textCoinSlot.getText());

				if (money > 0) {// 已投币
					/* 判断投入的是5角还是1元硬币 */
					str = alternativeBuyOrange(money);

					if (str.equals("可以售出")) {// 投入硬币币种正确，进行售出
						SaleMachine client = new SaleMachine();
						String back = client.buy("Orange", money);

						/* 修改售货机内的硬币数以及饮料库存量 */
						if (back.equals("请取走饮料!")) {
							/* 投入的币种是5角，售货机中5角硬币数量加1 */
							setCoin50(getCoin50() + 1);
							/* 修改饮料库存 */
							setOrangeNum(getOrangeNum() - 1);
						} else {
							/* 投入的币种是1元，售货机中5角硬币数量减1，1元硬币数量加1 */
							setCoin50(getCoin50() - 1);
							setCoin100(getCoin100() + 1);
							/* 修改饮料库存 */
							setOrangeNum(getOrangeNum() - 1);
						}

						panelTip.labelTakeDrinksTip.setText(back); // 提示客户购买信息
					} else
						panelTip.labelTakeDrinksTip.setText(str); // 饮料已售完或暂时不能找钱，需要提示客户
				} else
					panelTip.labelTakeDrinksTip.setText("请先投币，售货机支持5角或1元的硬币！");
			}
		});

		btnBeer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = null;
				// 获取用户投进的硬币
				int money = Integer.parseInt(panelCoinSlot.textCoinSlot.getText());

				if (money > 0) {// 已投币
					str = alternativeBuyBeer(money); // 判断投入的是5角还是1元硬币

					if (str.equals("可以售出")) {// 投入硬币币种正确，进行售出
						SaleMachine client = new SaleMachine();
						String back = client.buy("Beer", money);

						/* 修改售货机内的硬币数以及饮料库存量 */
						if (back.equals("请取走饮料!")) {
							/* 投入的币种是5角，售货机中5角硬币数量加1 */
							setCoin50(getCoin50() + 1);
							/* 修改饮料库存 */
							setBeerNum(getBeerNum() - 1);
						} else {
							/* 投入的币种是1元，售货机中5角硬币数量减1，1元硬币数量加1 */
							setCoin50(getCoin50() - 1);
							setCoin100(getCoin100() + 1);
							/* 修改饮料库存 */
							setBeerNum(getBeerNum() - 1);
						}
						panelTip.labelTakeDrinksTip.setText(back); // 提示客户购买信息
					} else
						panelTip.labelTakeDrinksTip.setText(str); // 饮料已售完或暂时不能找钱，需要提示客户
				} else
					panelTip.labelTakeDrinksTip.setText("请先投币，售货机支持5角或1元的硬币！");
			}
		});

		btnCoffee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = null;

				// 获取用户投进的硬币
				int money = Integer.parseInt(panelCoinSlot.textCoinSlot.getText());

				if (money > 0) {// 已投币
					str = alternativeBuyCoffee(money);

					if (str.equals("可以售出")) {
						SaleMachine client = new SaleMachine();
						String back = client.buy("Coffee", money);

						/* 修改售货机内的硬币数以及饮料库存量 */
						setCoin100(getCoin100() + 1);
						/* 修改饮料库存 */
						setCoffeeNum(getCoffeeNum() - 1);

						panelTip.labelTakeDrinksTip.setText(back);
					} else
						panelTip.labelTakeDrinksTip.setText(str);
				} else
					panelTip.labelTakeDrinksTip.setText("请先投币，售货机支持5角或1元的硬币！");
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
		btnOrange.setToolTipText("点击选择橙汁!");
		btnBeer.setToolTipText("点击选择啤酒!");
		btnCoffee.setToolTipText("点击选择咖啡!");

		// 添加到面板中
		panelDrinkStore.add(btnOrange);
		panelDrinkStore.add(btnBeer);
		panelDrinkStore.add(btnCoffee);

		add(panelCoinSlot, BorderLayout.NORTH);
		add(panelDrinkStore, BorderLayout.CENTER);
		add(panelTip, BorderLayout.SOUTH);

	}

	/**
	 * 验证硬币是否50分或100分
	 * @param money
	 * @return
	 */
	public static boolean checkCoin(int money) {
		switch (money) {
		case 50:
		case 100:
			return true;
		default:
			return false;
		}
	}

	public String alternativeBuyOrange(int money) {
		String message = null;

		switch (money) {
		case 50:
			if (getOrangeNum() != 0) {// 有存货量，可以实现购买
				message = "可以售出";
				break;
			}
		case 100:
			if (getOrangeNum() != 0) {
				if (getCoin50() > 0) {// 有存货量，可以实现购买
					message = "可以售出";
				} else
					message = "对不起，目前不能找钱，请投5角硬币!";
			} else
				message = "目前该饮料已经售完!";
			break;
		}
		return message.toString();
	}

	public String alternativeBuyBeer(int money) {
		String message = null;

		switch (money) {
		case 50:
			if (getBeerNum() != 0) {// 有存货量，可以实现购买
				message = "可以售出";
				break;
			}
		case 100:
			if (getBeerNum() != 0) {
				if (getCoin50() > 0) {// 有存货量，可以实现购买
					message = "可以售出";
				} else
					message = "对不起，目前不能找钱，请投5角硬币!";
			} else
				message = "目前该饮料已经售完!";
			break;
		}
		return message.toString();
	}

	public String alternativeBuyCoffee(int money) {
		String message = null;

		switch (money) {
		case 50:
			message = "对不起,Coffee的价格是1元!";
			break;
		case 100:
			if (getCoffeeNum() != 0) {// 有存货量,进行购买行为
				message = "可以售出";
			} else
				message = "目前不提供该饮料!";
			break;
		}
		return message.toString();
	}

	/*public void setPanelCoinSlot(JPanel panelCoinSlot) {
		this.panelCoinSlot = panelCoinSlot;
	}*/

	public JPanel getPanelCoinSlot() {
		return panelCoinSlot;
	}

	public void setPanelDrinkStore(JPanel panelDrinkStore) {
		this.panelDrinkStore = panelDrinkStore;
	}

	public JPanel getPanelDrinkStore() {
		return panelDrinkStore;
	}

	/*public void setPanelTip(JPanel panelTip) {
		this.panelTip = panelTip;
	}*/

	public JPanel getPanelTip() {
		return panelTip;
	}

	/*public void setJtf(JTextField jtf) {
		this.textCoinSlot = jtf;
	}

	public JTextField getJtf() {
		return textCoinSlot;
	}

	public void setJlb(JLabel jlb) {
		this.labelCoinSlot = jlb;
	}

	public JLabel getJlb() {
		return labelCoinSlot;
	}*/

	public void setJbtOrange(JButton jbtOrange) {
		this.btnOrange = jbtOrange;
	}

	public JButton getJbtOrange() {
		return btnOrange;
	}

	public void setJbtBeer(JButton jbtBeer) {
		this.btnBeer = jbtBeer;
	}

	public JButton getJbtBeer() {
		return btnBeer;
	}

	public void setJbtCoffee(JButton jbtCoffee) {
		this.btnCoffee = jbtCoffee;
	}

	public JButton getJbtCoffee() {
		return btnCoffee;
	}

	/*public void setJlb1(JLabel jlb1) {
		this.labelTakeDrinksTip = jlb1;
	}

	public JLabel getJlb1() {
		return labelTakeDrinksTip;
	}*/

	public int getOrangeNum() {
		return OrangeNum;
	}

	public void setOrangeNum(int OrangeNum) {
		this.OrangeNum = OrangeNum;
	}

	public int getBeerNum() {
		return BeerNum;
	}

	public void setBeerNum(int BeerNum) {
		this.BeerNum = BeerNum;
	}

	public int getCoffeeNum() {
		return CoffeeNum;
	}

	public void setCoffeeNum(int CoffeeNum) {
		this.CoffeeNum = CoffeeNum;
	}

	public int getCoin50() {
		return coin50;
	}

	public void setCoin50(int coin50) {
		this.coin50 = coin50;
	}

	public int getCoin100() {
		return coin100;
	}

	public void setCoin100(int coin100) {
		this.coin100 = coin100;
	}
}