package edu.zjut.view;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class TipPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public JLabel labelTakeDrinksTip = new JLabel();
	public TipPanel() {
		labelTakeDrinksTip.setText("请选择要购买的饮料");
		add(labelTakeDrinksTip);
	}
}
