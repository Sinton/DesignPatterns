package edu.zjut.view;

import javax.swing.JFrame;

public class TestSaleMachine {

	public static void main(String[] args) {
		try {
			JFrame sale = new SaleGUI();
			sale.setTitle("自动贩卖机");
			sale.setSize(550,500);
			sale.setLocationRelativeTo(null); 
			sale.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			sale.setVisible(true);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}