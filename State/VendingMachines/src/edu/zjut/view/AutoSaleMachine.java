package edu.zjut.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;


public class AutoSaleMachine extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 初始化所有商品的名字
	private static final String NAME_COKE = "可口可乐";
    private static final String NAME_FANTA = "芬达";
    private static final String NAME_RTEA = "红茶";
    private static final String NAME_GTEA = "绿茶";
    // 初始化所有商品数量
    private static int COUNT_COKE = 10;
    private static int COUNT_FANTA = 1;
    private static int COUNT_RTEA = 30;
    private static int COUNT_GTEA = 2;
    // 初始化所有商品的价格
    private static final int PRICE_COKE = 3;
    private static final int PRICE_FANTA = 2;
    private static final int PRICE_RTEA = 4;
    private static final int PRICE_GTEA = 5;
    // 初始化所有商品的ID
    private static final int ID_COKE = 1;
    private static final int ID_FANTA = 2;
    private static final int ID_RTEA = 3;
    private static final int ID_GTEA = 4;
    
    private String img_coke = "photos/coke.jpg";
    private String img_fanta = "photos/fenda.jpg";
    private String img_rtea = "photos/rtea.jpg";
    private String img_gtea = "photos/gtea.jpg";
    

    private JTextField txt_UserMoney;
    private JButton btn_UserFive;
    private JButton btn_UserOne;
    
    private GoodPanel gp_coke;
    private GoodPanel gp_fanta;
    private GoodPanel gp_rtea;
    private GoodPanel gp_gtea;

    private JButton btn_Buy;
    private JButton btn_Cancle;


    
    public AutoSaleMachine(){
    	GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[] { 0, 0 };
        gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0 };
        gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
        gridBagLayout.rowWeights = new double[] { 1.0, 0.0, 0.0,
                Double.MIN_VALUE };
        getContentPane().setLayout(gridBagLayout);
    	
        JPanel panel_Good = new JPanel();
        GridBagConstraints gbc_panel_Good = new GridBagConstraints();
        gbc_panel_Good.insets = new Insets(0, 0, 5, 0);
        gbc_panel_Good.fill = GridBagConstraints.BOTH;
        gbc_panel_Good.gridx = 0;
        gbc_panel_Good.gridy = 0;
        getContentPane().add(panel_Good, gbc_panel_Good);
        GridBagLayout gbl_panel_Good = new GridBagLayout();
        gbl_panel_Good.columnWidths = new int[] { 230, 230, 0 };
        gbl_panel_Good.rowHeights = new int[] { 0, 0, 0 };
        gbl_panel_Good.columnWeights = new double[] { 0.5, 0.5,
                Double.MIN_VALUE };
        gbl_panel_Good.rowWeights = new double[] { 0.5, 0.5, Double.MIN_VALUE };
        panel_Good.setLayout(gbl_panel_Good);

        gp_coke = new GoodPanel(img_coke, PRICE_COKE, NAME_COKE, ID_COKE,
                COUNT_COKE);
        GridBagConstraints gbc_gp_coke = new GridBagConstraints();
        gbc_gp_coke.insets = new Insets(5, 5, 5, 5);
        gbc_gp_coke.fill = GridBagConstraints.BOTH;
        gbc_gp_coke.gridx = 0;
        gbc_gp_coke.gridy = 0;
        panel_Good.add(gp_coke, gbc_gp_coke);

        gp_fanta = new GoodPanel(img_fanta, PRICE_FANTA, NAME_FANTA, ID_FANTA,
                COUNT_FANTA);
        GridBagConstraints gbc_gp_fanta = new GridBagConstraints();
        gbc_gp_fanta.insets = new Insets(5, 5, 5, 5);
        gbc_gp_fanta.fill = GridBagConstraints.BOTH;
        gbc_gp_fanta.gridx = 1;
        gbc_gp_fanta.gridy = 0;
        panel_Good.add(gp_fanta, gbc_gp_fanta);

        gp_rtea = new GoodPanel(img_rtea, PRICE_RTEA, NAME_RTEA, ID_RTEA,
                COUNT_RTEA);
        GridBagConstraints gbc_gp_rtea = new GridBagConstraints();
        gbc_gp_rtea.insets = new Insets(5, 5, 5, 5);
        gbc_gp_rtea.fill = GridBagConstraints.BOTH;
        gbc_gp_rtea.gridx = 0;
        gbc_gp_rtea.gridy = 1;
        panel_Good.add(gp_rtea, gbc_gp_rtea);

        gp_gtea = new GoodPanel(img_gtea, PRICE_GTEA, NAME_GTEA, ID_GTEA,
                COUNT_GTEA);
        GridBagConstraints gbc_gp_gtea = new GridBagConstraints();
        gbc_gp_gtea.insets = new Insets(5, 5, 5, 5);
        gbc_gp_gtea.fill = GridBagConstraints.BOTH;
        gbc_gp_gtea.gridx = 1;
        gbc_gp_gtea.gridy = 1;
        panel_Good.add(gp_gtea, gbc_gp_gtea);
        
        
        JPanel panel_Btn = new JPanel();
        GridBagConstraints gbc_panel_Btn = new GridBagConstraints();
        gbc_panel_Btn.insets = new Insets(0, 0, 5, 0);
        gbc_panel_Btn.fill = GridBagConstraints.HORIZONTAL;
        gbc_panel_Btn.gridx = 0;
        gbc_panel_Btn.gridy = 1;
        getContentPane().add(panel_Btn, gbc_panel_Btn);
        GridBagLayout gbl_panel_Btn = new GridBagLayout();
        gbl_panel_Btn.columnWidths = new int[] { 0, 0, 0, 0, 0 };
        gbl_panel_Btn.rowHeights = new int[] { 0, 0, 0 };
        gbl_panel_Btn.columnWeights = new double[] { 0.25, 0.25, 0.25, 0.25,
                Double.MIN_VALUE };
        gbl_panel_Btn.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
        panel_Btn.setLayout(gbl_panel_Btn);
        
        JLabel lbl_UserMoney = new JLabel("");
        GridBagConstraints gbc_lbl_UserMoney = new GridBagConstraints();
        gbc_lbl_UserMoney.insets = new Insets(5, 5, 5, 5);
        gbc_lbl_UserMoney.gridx = 0;
        gbc_lbl_UserMoney.gridy = 0;
        panel_Btn.add(lbl_UserMoney, gbc_lbl_UserMoney);

        txt_UserMoney = new JTextField();
        txt_UserMoney.setEditable(false);
        GridBagConstraints gbc_txt_UserMoney = new GridBagConstraints();
        gbc_txt_UserMoney.insets = new Insets(5, 0, 5, 5);
        gbc_txt_UserMoney.fill = GridBagConstraints.HORIZONTAL;
        gbc_txt_UserMoney.gridx = 1;
        gbc_txt_UserMoney.gridy = 0;
        panel_Btn.add(txt_UserMoney, gbc_txt_UserMoney);
        txt_UserMoney.setColumns(10);



        btn_Buy = new JButton("\u8D2D\u4E70");
        GridBagConstraints gbc_btn_Buy = new GridBagConstraints();
        gbc_btn_Buy.insets = new Insets(0, 0, 5, 5);
        gbc_btn_Buy.gridx = 1;
        gbc_btn_Buy.gridy = 1;
        panel_Btn.add(btn_Buy, gbc_btn_Buy);
        btn_Buy.addActionListener(handler);
        
        
        btn_UserOne = new JButton("\u6295\u5165\u4E00\u5143");
        GridBagConstraints gbc_btn_UserOne = new GridBagConstraints();
        gbc_btn_UserOne.insets = new Insets(5, 0, 5, 5);
        gbc_btn_UserOne.gridx = 2;
        gbc_btn_UserOne.gridy = 0;
        panel_Btn.add(btn_UserOne, gbc_btn_UserOne);
        btn_UserOne.addActionListener(handler);
        
        
        btn_UserFive = new JButton("\u6295\u5165\u4E94\u5143");
        GridBagConstraints gbc_btn_UserFive = new GridBagConstraints();
        gbc_btn_UserFive.insets = new Insets(5, 0, 5, 0);
        gbc_btn_UserFive.gridx = 3;
        gbc_btn_UserFive.gridy = 0;
        panel_Btn.add(btn_UserFive, gbc_btn_UserFive);
        btn_UserFive.addActionListener(handler);

        btn_Cancle = new JButton("\u9000\u6B3E");
        GridBagConstraints gbc_btn_Cancle = new GridBagConstraints();
        gbc_btn_Cancle.insets = new Insets(0, 0, 5, 5);
        gbc_btn_Cancle.gridx = 2;
        gbc_btn_Cancle.gridy = 1;
        panel_Btn.add(btn_Cancle, gbc_btn_Cancle);
        btn_Cancle.addActionListener(handler);
        
        
    }
    
    private ActionListener handler = new ActionListener() {
    	 public void actionPerformed(ActionEvent e){}
    };
    
    public static void main(String[] args){
    	AutoSaleMachine asm = new AutoSaleMachine();
    	asm.setTitle("自动售货机DEMO");
        asm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        asm.setSize(500, 600);
        asm.setResizable(false);
        asm.setLocationRelativeTo(null);
        asm.setVisible(true);
    	
    }
    
    
    class GoodPanel extends JPanel {
        private static final long serialVersionUID = 4364216395712746083L;
        private static final String MONEY_UNIT = "元";
        private static final String TIP = "单击图片可以选择购买或者取消购买";

        private String picPath;
        private String name;
        private JTextField txt_Price;
        private int price;
        private JLabel lbl_Pic;
        private JCheckBox cbox_Select;
        private int goodId;
        private int goodCount;
        private JLabel lbl_Remain;
        private JTextField txt_Remain;

        GoodPanel(String picPath, int price, String name, int goodId, int goodCount) {
            this.picPath = picPath;
            this.price = price;
            this.name = name;
            this.goodId = goodId;
            this.goodCount = goodCount;
            this.setBorder(new CompoundBorder(null, new LineBorder(new Color(0,
                    0, 0), 2)));
            this.setLayout(new BorderLayout(0, 0));
            lbl_Pic = new JLabel();
            this.add(lbl_Pic, BorderLayout.CENTER);
            lbl_Pic.setToolTipText(TIP);
         

            JPanel panel_Prop = new JPanel();
            this.add(panel_Prop, BorderLayout.SOUTH);

            JLabel lbl_Name = new JLabel(this.name);
            panel_Prop.add(lbl_Name);

            txt_Price = new JTextField();
            txt_Price.setEditable(false);
            panel_Prop.add(txt_Price);
            txt_Price.setColumns(2);
            txt_Price.setText(this.price + MONEY_UNIT);

            cbox_Select = new JCheckBox("\u8D2D\u4E70");
            if (this.goodCount <= 0) {
                cbox_Select.setSelected(false);
            }
            panel_Prop.add(cbox_Select);

            lbl_Remain = new JLabel("剩余：");
            panel_Prop.add(lbl_Remain);
            txt_Remain = new JTextField();
            txt_Remain.setEditable(false);
            panel_Prop.add(txt_Remain);
            txt_Remain.setColumns(2);
            lbl_Remain.setVisible(false);
            txt_Remain.setVisible(false);
            initPic();
        }

       

     

        /**
         * 初始化显示的商品图片
         */
        private void initPic() {
            if (this.picPath == null) {
                return;
            }
            new Thread() {
                @Override
                public void run() {
                    int width = getWidth();
                    while (width == 0) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        width = getWidth();
                    }
                    int height = getHeight();
                    File input = new File(picPath);
                    if (!input.exists()) {
                        return;
                    }
                    try {
                        BufferedImage image = ImageIO.read(input);
                        ImageIcon bgIcon = new ImageIcon(image.getScaledInstance(
                                width, height, Image.SCALE_DEFAULT));
                        lbl_Pic.setIcon(bgIcon);
                        repaint();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }         
       }
    }
