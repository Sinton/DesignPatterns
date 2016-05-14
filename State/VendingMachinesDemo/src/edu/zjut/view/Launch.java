package edu.zjut.view;

import javax.swing.JFrame;

import edu.zjut.model.VendingMachine;

public class Launch {

	final static VendingMachine machine = new VendingMachine(10);
	
	public static void main(String[] args) {
		JFrame sale = new SaleGUI();
		sale.setTitle("自动售货机");
		sale.setSize(500,500);
		sale.setLocationRelativeTo(null); 
		sale.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sale.setVisible(true);
	}
}