package edu.zjut.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BusinessPanel extends JPanel {
	
	private static final long serialVersionUID = -1L;
	private JLabel labelCurrentDeposit;
	private JLabel textCoinDrop;
	private JTextField textCurrentDeposit;
	private JButton btnCoinReturn;
	private JComboBox<String> denomination;
	private double[] coins = {0.5, 1.0, 5.0, 10};

	public BusinessPanel() {
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
		this.setVisible(true);
	}

	private void initEntity() {
		textCoinDrop.setEnabled(false);
		textCurrentDeposit.setText("0");
		textCurrentDeposit.setEnabled(false);
	}
}