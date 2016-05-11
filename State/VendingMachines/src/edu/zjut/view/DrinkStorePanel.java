package edu.zjut.view;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

import edu.zjut.utils.Repository;

public class DrinkStorePanel extends JPanel {

	private static final long serialVersionUID = -1L;
	private static final int COL = 2;
	private static final int ROW = 2;
	
	Repository repository = new Repository();
	
	public DrinkStorePanel() {
		repository.setDrinkList();
		this.setLayout(new GridLayout(0, ROW));
		this.initEntity(COL, ROW);
		this.setVisible(true);
	}
	
	public void initEntity(int col, int row) {
		String name = null;
		String url = null;
		Integer price = null;
		Integer count = null;
		for (int i = 0; i < col; i++) {
			for (int j = 0; j < row; j++) {
				name = repository.drinkRepository.get(i * row + j).getName();
				url = repository.drinkRepository.get(i * row + j).getUrl();
				price = repository.drinkRepository.get(i * row + j).getPrice();
				count = repository.drinkRepository.get(i * row + j).getCount();
				GoodPanel goodPanel = new GoodPanel(url, price, name, 10);
				goodPanel.btnBuy.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent event) {
						System.out.println(event);
					}
				});
				this.add(goodPanel);
			}
		}
	}
	
	class GoodPanel extends JPanel {
		
		private static final long serialVersionUID = 1L;
		private static final String MONEY_UNIT = "元";
		private static final String TIP = "单击图片可以选择购买或者取消购买";

		private JTextField txt_Price;
		private JLabel labelPictureTip;
		public JButton btnBuy;
		private JLabel lbl_Remain;
		private JTextField txt_Remain;
		private String picturePath;
		private String name;
		private int price;
		private int goodCount;

		GoodPanel(String picPath, int price, String name, int goodCount) {
			this.picturePath = picPath;
			this.price = price;
			this.name = name;
			this.goodCount = goodCount;
			this.setBorder(new CompoundBorder(null, new LineBorder(new Color(0, 0, 0), 1)));
			this.setLayout(new BorderLayout(0, 0));
			labelPictureTip = new JLabel();
			this.add(labelPictureTip, BorderLayout.CENTER);
			labelPictureTip.setToolTipText(TIP);

			JPanel panel_Prop = new JPanel();
			this.add(panel_Prop, BorderLayout.SOUTH);

			JLabel lbl_Name = new JLabel(this.name);
			panel_Prop.add(lbl_Name);

			txt_Price = new JTextField();
			txt_Price.setEditable(false);
			panel_Prop.add(txt_Price);
			txt_Price.setText(this.price + MONEY_UNIT);

			btnBuy = new JButton("购买");
			if (this.goodCount <= 0) {
				btnBuy.setEnabled(false);
				btnBuy.setText("售空");
			}
			panel_Prop.add(btnBuy);

			lbl_Remain = new JLabel("剩余：");
			panel_Prop.add(lbl_Remain);
			txt_Remain = new JTextField();
			txt_Remain.setEditable(false);
			panel_Prop.add(txt_Remain);
			txt_Remain.setColumns(2);
			lbl_Remain.setVisible(false);
			txt_Remain.setVisible(false);
			initPic();
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
	}
}