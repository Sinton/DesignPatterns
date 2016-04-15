package zjut.view;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class MainApplication extends JFrame {
	
	private JTabbedPane tabbedPane;  
    private JPanel panelTimeSeries, panelKLine;  
  
	private static final long serialVersionUID = 1L;

	public MainApplication () {
		setTitle("股票证券系统");
		setSize(700, 600);

		//setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
		intiComponent();
		/*setLayout(new FlowLayout());
		setSize(650,550);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);*/
	}

	/**
     * 初始化GUI界面
     */
	private void intiComponent() {
		// 创建选项卡面板对象
		Container container = getContentPane();
		// 创建面板
		tabbedPane = new JTabbedPane();
		panelTimeSeries = new JPanel();
		panelKLine = new JPanel();

		// 将标签面板加入到选项卡面板对象上
		tabbedPane.addTab("时序图", null, panelTimeSeries, null);
		tabbedPane.addTab("K线图", null, panelKLine, null);
		//container.add(tabbedPane);
		add(tabbedPane);
		//panelKLine.add();
		new KLine().getKLineView();
	}

	public static void main(String[] args) {
		new MainApplication();
	}
}