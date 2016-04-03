package zjut.view;

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

import zjut.model.bean.Discount;
import zjut.model.bean.Printer;
import zjut.model.utils.Repository;
import zjut.model.utils.Tools;

public class MainApplication extends JFrame {
	
	private ArrayList<String> printers = new ArrayList<String>();
	private ArrayList<String> discounts = new ArrayList<String>();
	private Tools tools = new Tools();
	private double total = 0;
    
	MainApplication() {
		setTitle("��ӡ������ϵͳ");
		setLayout(new FlowLayout());
		Repository.initProperties();
		intiComponent();
		setSize(650,550);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

	public static void main(String[] args) {
		new MainApplication();
	}
	
	/**
     * ��ʼ��GUI����
     */
	private void intiComponent() {
		printers = tools.getAllPrinters();
		discounts = tools.getAllDiscounts();
		String[] tableTitle 		= {"��ӡ��", "�ۿ۷�ʽ", "����", "����", "ʵ�����۵���", "�ϼ�"};
		String[][] tableContents 	= null;
		DefaultTableModel model = new DefaultTableModel(tableContents, tableTitle) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
        final JTextField textNumber 			= new JTextField(4);
        final JComboBox<String> boxBrand		= new JComboBox<String>(new DefaultComboBoxModel(printers.toArray()));
        final JComboBox<String> boxDiscount	= new JComboBox<String>(new DefaultComboBoxModel(discounts.toArray()));
        final JTable tableCart				= new JTable(model);
        final JScrollPane scroll 				= new JScrollPane(tableCart);
        final JLabel labelTotal 				= new JLabel("�ܼۺϼ�:" + total);
		JLabel labelSelectName 		= new JLabel("��ѡ���ӡ������:");
		JLabel labelSelectDiscount	= new JLabel("��ѡ�ۿ�����:");
		JLabel labelNumber 			= new JLabel("��ѡ������:");
    	JButton btnAddCart 			= new JButton("��ӵ���Ʒ�嵥");
    	JButton btnExit 				= new JButton("�˳�");
    	tableCart.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    	textNumber.setText("1");
    	
		// ��Ӵ�ӡ������Ʒ�嵥
		btnAddCart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String printer = boxBrand.getSelectedItem().toString();
				String discount = boxDiscount.getSelectedItem().toString();
				String amount = textNumber.getText().equals("") == false ? textNumber.getText().toString() : "1";
				String printerClassName = tools.getClassName(printer, "printers");
				String discountClassName = tools.getClassName(discount, "discounts");
				String unitPrice = null;
				String salePrice = null;
				String price = null;
				try {
					// �����ӡ���ࡢ�ۿ���
					Class<?> anyPrinterClass = Class.forName("zjut.model.printer." + printerClassName);
					Class<?> anyDiscountClass = Class.forName("zjut.model.discount." + discountClassName);
					Printer anyPrinter = (Printer) anyPrinterClass.newInstance();
					Discount anyDiscount = (Discount) anyDiscountClass.newInstance();
					unitPrice = String.format("%.2f", anyPrinter.getPrice());
					salePrice = String.format("%.2f", anyDiscount.salePrice(anyPrinter.getPrice()));
					price = String.format("%.2f", anyDiscount.discountPrice(anyPrinter.getPrice(), Integer.valueOf(amount)));
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException  exception) {
					System.out.println("ʵ�������쳣");
				}
				DefaultTableModel tableModel = (DefaultTableModel) tableCart.getModel();
				tableModel.addRow(new String[] { printer, discount, amount, unitPrice, salePrice, price });
				total += Double.parseDouble(price);
				labelTotal.setText("�ܼۺϼ�:" + String.format("%.2f��", total));
			}
		});
		
		// �˳�Ӧ�ó���
		btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
    	// ��ӵ�����������
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