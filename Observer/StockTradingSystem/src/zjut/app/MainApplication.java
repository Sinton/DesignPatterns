package zjut.app;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import zjut.view.KLineViewObserver;
import zjut.view.TimeSeriesViewObserver;
import zjut.view.model.StockSubject;

public class MainApplication extends JFrame {
	
	private JTabbedPane tabbedPane;
    private JPanel panelTimeSeries;
    private JPanel panelKLine;  
  
	private static final long serialVersionUID = 1L;

	public MainApplication () {
		KLineViewObserver kLine = new KLineViewObserver();
		TimeSeriesViewObserver timeSeries = new TimeSeriesViewObserver();
		StockSubject stock = new StockSubject();
		StockSubject.priceList.add(StockSubject.OPENING_PRICE);
		stock.addObserver(kLine);
		stock.addObserver(timeSeries);
		
		setTitle("股票证券系统");
		setSize(700, 500);
		intiComponent();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
     * 初始化GUI界面
     */
	private void intiComponent() {
		tabbedPane = new JTabbedPane();
		panelTimeSeries = new JPanel();
		panelKLine = new JPanel();
		panelTimeSeries.add(new TimeSeriesViewObserver().getTimeSeriesView());
		panelKLine.add(new KLineViewObserver().getKLineView());
		tabbedPane.addTab("分时图", null, panelTimeSeries, null);
		tabbedPane.addTab("K线图", null, panelKLine, null);
		add(tabbedPane);
		//tabbedPane.setSelectedIndex(1);
	}

	public static void main(String[] args) {
		new MainApplication();
	}
}