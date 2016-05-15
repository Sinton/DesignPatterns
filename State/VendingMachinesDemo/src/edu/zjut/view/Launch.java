package edu.zjut.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import edu.zjut.model.VendingMachine;

public class Launch extends JFrame{

	private static final long serialVersionUID = 1L;
	final static VendingMachine machine = new VendingMachine(30);
	
	private static final CoinPanel panelCoinSlot = new CoinPanel();
	private static final TipPanel panelTip = new TipPanel();
	private static final DrinkStorePanel panelDrinkStore = new DrinkStorePanel(panelCoinSlot, panelTip);
	
	public Launch() {
		add(panelCoinSlot, BorderLayout.NORTH);
		add(panelDrinkStore, BorderLayout.CENTER);
		add(panelTip, BorderLayout.SOUTH);
		setTitle("自动售货机");
		setSize(500,500);
		setLocationRelativeTo(null); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Launch();
	}
}