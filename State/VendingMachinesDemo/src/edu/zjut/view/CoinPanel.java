package edu.zjut.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CoinPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	JTextField textCoinSlot = null;// 输入硬币数
	JLabel labelCoinSlot = null;
	
	public CoinPanel() {
		setLayout(new BorderLayout());
		labelCoinSlot = new JLabel("投币口：");
		textCoinSlot = new JTextField(5);

		textCoinSlot.setText("0");

		textCoinSlot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				textCoinSlot.requestFocusInWindow();
			}
		});

		add(labelCoinSlot, BorderLayout.WEST);
		add(textCoinSlot, BorderLayout.CENTER);
	}
}