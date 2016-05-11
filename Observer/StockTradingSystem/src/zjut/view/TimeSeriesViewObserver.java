package zjut.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.Hour;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.RectangleInsets;

import zjut.view.model.Observer;
import zjut.view.model.StockSubject;

public class TimeSeriesViewObserver extends JFrame implements Observer{
	
	private static TimeSeries timeSeriesPrice;
	private static ChartPanel chartPanel;
	private static final int MAXIMUM_ITEM_AGE = 30000;
	private static Day day = new Day(18, 4, 2016);
	private static int count = 1;
	private static int index = 0;
	
	/**
	 * 构造分时图的全部元素
	 */
	public TimeSeriesViewObserver() {
		timeSeriesPrice = new TimeSeries("分时图", Hour.class);
		timeSeriesPrice.add(new Hour(count++, day), StockSubject.OPENING_PRICE);
		timeSeriesPrice.setMaximumItemAge(MAXIMUM_ITEM_AGE);

		TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
		timeseriescollection.addSeries(timeSeriesPrice);
		DateAxis dateaxis = new DateAxis("时间");
		NumberAxis numberaxis = new NumberAxis("股票价格");
		dateaxis.setTickLabelFont(new Font("微软雅黑", 0, 12));
		numberaxis.setTickLabelFont(new Font("微软雅黑", 0, 12));
		dateaxis.setLabelFont(new Font("微软雅黑", 0, 12));
		numberaxis.setLabelFont(new Font("微软雅黑", 0, 12));

		XYLineAndShapeRenderer xylineandshaperenderer = new XYLineAndShapeRenderer(true, false);
		xylineandshaperenderer.setSeriesPaint(0, Color.red);
		xylineandshaperenderer.setSeriesStroke(0, new BasicStroke(1F, 0, 1));

		XYPlot xyplot = new XYPlot(timeseriescollection, dateaxis, numberaxis, xylineandshaperenderer);
		xyplot.setAxisOffset(new RectangleInsets(0D, 0D, 0D, 0D));
		dateaxis.setAutoRange(true);
		dateaxis.setLowerMargin(0.0D);
		dateaxis.setUpperMargin(0.0D);
		dateaxis.setTickLabelsVisible(true);
		numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

		JFreeChart chart = new JFreeChart("中国铁通股票", new Font("微软雅黑", 1, 12), xyplot, true);
		chartPanel = new ChartPanel(chart);
	}

	/**
	 * 获取股票价格的分时图
	 * @return
	 */
	public Component getTimeSeriesView() {
		TimeSeriesViewObserver timeSeriesChar = new TimeSeriesViewObserver();
		(timeSeriesChar.new DataGenerator(1000)).start();
		return chartPanel;
	}
	
	/**
	 * 获取随机价格
	 * @return
	 */
	private double getRamdomPrice () {
		return StockSubject.priceList.get(index++);
	}

	/**
	 * 添加价格
	 * @param price
	 */
	private static void addPrice(double price) {
		StockSubject.priceList.add(price);
		timeSeriesPrice.add(new Hour(count++, day), price);
	}

	/**
	 * 时间监听器，用于监听时间改变
	 */
	class DataGenerator extends Timer implements ActionListener {
		public void actionPerformed(ActionEvent actionevent) {
			update();
			if (count > 23)
				this.stop();
		}

		DataGenerator(int hour) {
			super(hour, null);
			addActionListener(this);
		}
	}

	@Override
	public void update() {
		addPrice(getRamdomPrice());
	}
}