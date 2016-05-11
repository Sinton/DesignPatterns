package edu.zjut.view;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainApplication extends JFrame {

	private static final long serialVersionUID = 1L;
	JPanel viewPane = new JPanel();

	public MainApplication() {
		this.setTitle("自动贩卖机");
		this.setLayout(new GridLayout(1, 2));
		this.intiComponent();
		this.setSize(550, 500);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new MainApplication();
	}

	public void intiComponent() {
		this.add(new DrinkStorePanel());
		BusinessPanel businessPanel = new BusinessPanel();
		this.add(new BusinessPanel());
	}
}