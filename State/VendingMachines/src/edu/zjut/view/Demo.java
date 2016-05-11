package edu.zjut.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Demo extends JFrame {
	
	private ArrayList<String> printers = new ArrayList<String>();
	private ArrayList<String> discounts = new ArrayList<String>();
	private double total = 0;
    
	Demo() {
		setTitle("自动贩卖机");
		setLayout(new FlowLayout());
		intiComponent();
		setSize(650,550);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

	public static void main(String[] args) {
		new Demo();
	}
	
	/**
     * 初始化GUI界面
     */
	private void intiComponent() {
		String[] tableTitle 		= {"打印机", "折扣方式", "数量", "单价", "实际销售单价", "合计"};
		String[][] tableContents 	= null;
		DefaultTableModel model = new DefaultTableModel(tableContents, tableTitle) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
        final JTextField textNumber			= new JTextField(4);
        final JComboBox<String> boxBrand		= new JComboBox<String>(new DefaultComboBoxModel(printers.toArray()));
        final JComboBox<String> boxDiscount	= new JComboBox<String>(new DefaultComboBoxModel(discounts.toArray()));
        final JTable tableCart				= new JTable(model);
        final JScrollPane scroll				= new JScrollPane(tableCart);
        final JLabel labelTotal				= new JLabel("总价合计:" + total);
		JLabel labelSelectName		= new JLabel("请选择打印机名称:");
		JLabel labelSelectDiscount	= new JLabel("请选折扣类型:");
		JLabel labelNumber				= new JLabel("请选择数量:");
    	JButton btnAddCart				= new JButton("添加到物品清单");
    	JButton btnExit				= new JButton("退出");
    	tableCart.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    	textNumber.setText("1");
    	
		// 添加打印机到物品清单
		btnAddCart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		// 退出应用程序
		btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
    	// 添加到布局容器中
    	add(labelSelectName);
    	add(boxBrand);
    	add(labelSelectDiscount);
    	add(boxDiscount);
    	add(labelNumber);
    	add(textNumber);
    	add(btnAddCart);
    	add(scroll);
    	add(labelTotal);
    	add(btnExit);
	}
}