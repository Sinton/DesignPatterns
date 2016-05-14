package edu.zjut.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

public class MainApplication extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private static final String MONEY_UNIT = "元";
	private static final String TIP = "单击图片可以选择购买或者取消购买";

	// 饮料
	private JTextField txetPrice;
	private JLabel labelPictureTip;
	public JButton btnBuy;
	private JLabel labelRemain;
	private JTextField textRemain;
	private String picturePath;
	private String name;
	private int price;
	private int goodCount;

	// 交易
	private JLabel labelCurrentDeposit;
	private JLabel textCoinDrop;
	private JTextField textCurrentDeposit;
	private JButton btnCoinReturn;
	private JComboBox<String> denomination;
	private double[] coins = {0.5, 1.0, 5.0, 10};

	public MainApplication() {
		this.setTitle("自动贩卖机");
		this.setLayout(new FlowLayout());
		this.intiComponent();
		this.setSize(400, 300);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.picturePath = "photos/coke.jpg";
		this.setLayout(new BorderLayout(0, 0));
		labelPictureTip = new JLabel();
		this.add(labelPictureTip, BorderLayout.CENTER);
		labelPictureTip.setToolTipText(TIP);

		JPanel panelProp = new JPanel();
		this.add(panelProp, BorderLayout.SOUTH);

		JLabel labelName = new JLabel(this.name);
		panelProp.add(labelName);

		txetPrice = new JTextField();
		txetPrice.setEditable(false);
		panelProp.add(txetPrice);
		txetPrice.setText(this.price + MONEY_UNIT);

		btnBuy = new JButton("购买");
		if (this.goodCount <= 0) {
			btnBuy.setEnabled(false);
			btnBuy.setText("售空");
		}
		panelProp.add(btnBuy);

		labelRemain = new JLabel("剩余：");
		panelProp.add(labelRemain);
		textRemain = new JTextField();
		textRemain.setEditable(false);
		panelProp.add(textRemain);
		textRemain.setColumns(2);
		labelRemain.setVisible(false);
		textRemain.setVisible(false);
		labelCurrentDeposit = new JLabel("当前可用金额：");
		textCurrentDeposit = new JTextField(3);
		textCoinDrop = new JLabel("投币");
		btnCoinReturn = new JButton("退币");
		
		denomination = new JComboBox<String>();
		for (int i = 0; i < coins.length; i++)
			denomination.addItem(String.valueOf(coins[i]));
		
		denomination.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent even) {
				double totalCoin = Double.valueOf(textCurrentDeposit.getText());
				totalCoin += Double.valueOf(denomination.getSelectedItem().toString());
				textCurrentDeposit.setText(String.valueOf(totalCoin));
			}
		});
		
		btnCoinReturn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent even) {
				textCurrentDeposit.setText("0");
			}
		});
		
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.initEntity();
		this.add(labelCurrentDeposit);
		this.add(textCurrentDeposit);
		this.add(textCoinDrop);
		this.add(denomination);
		this.add(btnCoinReturn);
		initPic();
	}

	public static void main(String[] args) {
		new MainApplication();
	}

	public void intiComponent() {
		//this.add(new DrinkStorePanel());
		//this.add(new BusinessPanel());
	}
	
	/**
	 * 初始化显示的商品图片
	 */
	private void initPic() {
		if (this.picturePath == null) {
			return;
		}
		new Thread() {
			@Override
			public void run() {
				int width = getWidth();
				while (width == 0) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					width = getWidth();
				}
				int height = getHeight();
				File input = new File(picturePath);
				if (!input.exists()) {
					return;
				}
				try {
					BufferedImage image = ImageIO.read(input);
					ImageIcon bgIcon = new ImageIcon(image.getScaledInstance(width, height, Image.SCALE_DEFAULT));
					labelPictureTip.setIcon(bgIcon);
					repaint();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
	
	private void initEntity() {
		textCoinDrop.setEnabled(false);
		textCurrentDeposit.setText("0");
		textCurrentDeposit.setEnabled(false);
	}
}