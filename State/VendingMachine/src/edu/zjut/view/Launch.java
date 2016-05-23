package edu.zjut.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import edu.zjut.model.VendingMachine;

public class Launch extends JFrame{

	private static final long serialVersionUID = 1L;
	
	final static VendingMachine machine = new VendingMachine();
	
	public static final TipPanel panelTip = new TipPanel();
	private static final CoinPanel panelCoinSlot = new CoinPanel(panelTip);
	private static final DrinkStorePanel panelDrinkStore = new DrinkStorePanel(panelCoinSlot, panelTip);
	
	public Launch() {
		add(panelCoinSlot, BorderLayout.NORTH);
		add(panelTip, BorderLayout.CENTER);
		add(panelDrinkStore, BorderLayout.SOUTH);
		setTitle("自动售货机");
		setSize(600,620);
		setLocationRelativeTo(null); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Launch();
	}
}