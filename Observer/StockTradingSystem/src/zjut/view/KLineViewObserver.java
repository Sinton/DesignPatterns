package zjut.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Paint;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickMarkPosition;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.SegmentedTimeline;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.CandlestickRenderer;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.time.Second;
import org.jfree.data.time.ohlc.OHLCSeries;
import org.jfree.data.time.ohlc.OHLCSeriesCollection;

import zjut.view.model.Observer;
import zjut.view.model.StockSubject;

public class KLineViewObserver extends JFrame implements Observer{
	
	double maxPrice = Double.MIN_VALUE;
	double minPrice = Double.MAX_VALUE;
	double closePrice = StockSubject.priceList.get(22);
	
	public Component getKLineView() {
		this.update();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
		OHLCSeries series = new OHLCSeries("");
		series.add(new Second(05, 07, 9, 18, 4, 2016), minPrice, maxPrice, StockSubject.OPENING_PRICE, closePrice);
		
		final OHLCSeriesCollection seriesCollection = new OHLCSeriesCollection();
		seriesCollection.addSeries(series);

		int seriesCount = seriesCollection.getSeriesCount();
		for (int i = 0; i < seriesCount; i++) {
			int itemCount = seriesCollection.getItemCount(i);
			for (int j = 0; j < itemCount; j++) {
				if (maxPrice < seriesCollection.getHighValue(i, j)) {
					maxPrice = seriesCollection.getHighValue(i, j);
				}
				if (minPrice > seriesCollection.getLowValue(i, j)) {
					minPrice = seriesCollection.getLowValue(i, j);
				}
			}
		}
		
		final CandlestickRenderer candlestickRender = new CandlestickRenderer();
		candlestickRender.setUseOutlinePaint(true);
		candlestickRender.setAutoWidthMethod(CandlestickRenderer.WIDTHMETHOD_AVERAGE);
		candlestickRender.setAutoWidthGap(0.001);
		candlestickRender.setUpPaint(Color.RED);
		candlestickRender.setDownPaint(Color.GREEN);
		DateAxis x1Axis = new DateAxis();
		x1Axis.setAutoRange(false);
		try {
			x1Axis.setRange(dateFormat.parse("2016-04-18 00:00:00"),dateFormat.parse("2016-04-19 00:00:00"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		x1Axis.setTimeline(SegmentedTimeline.newMondayThroughFridayTimeline());
		x1Axis.setAutoTickUnitSelection(false);
		x1Axis.setTickMarkPosition(DateTickMarkPosition.MIDDLE);
		x1Axis.setStandardTickUnits(DateAxis.createStandardDateTickUnits());
		x1Axis.setTickUnit(new DateTickUnit(DateTickUnit.DAY, 7));
		x1Axis.setDateFormatOverride(new SimpleDateFormat("yyyy-MM-dd kk:mm:ss"));
		NumberAxis y1Axis = new NumberAxis();
		y1Axis.setAutoRange(false);
		y1Axis.setRange(minPrice * 0.9, maxPrice * 1.1);
		y1Axis.setTickUnit(new NumberTickUnit((maxPrice * 1.1 - minPrice * 0.9) / 10));
		XYPlot plot1 = new XYPlot(seriesCollection, x1Axis, y1Axis, candlestickRender);

		XYBarRenderer xyBarRender = new XYBarRenderer() {
			public Paint getItemPaint(int i, int j) {
				if (seriesCollection.getCloseValue(i, j) > seriesCollection.getOpenValue(i, j)) {
					return candlestickRender.getUpPaint();
				} else {
					return candlestickRender.getDownPaint();
				}
			}
		};
		
		CombinedDomainXYPlot combineddomainxyplot = new CombinedDomainXYPlot(x1Axis);
		combineddomainxyplot.add(plot1, 3);
		combineddomainxyplot.setGap(10);
		JFreeChart chart = new JFreeChart("中国铁通股票", new Font("微软雅黑", 1, 12), combineddomainxyplot, false);
		ChartPanel chartPanel = new ChartPanel(chart);
		return chartPanel;
	}

	@Override
	public void update() {
		maxPrice = getMaxPrice();
		minPrice = getMinPrice();
	}

	private double getMaxPrice() {
		double max = Double.MIN_VALUE;
		for (int i = 0; i < StockSubject.priceList.size(); i++) {
			if (StockSubject.priceList.get(i) > maxPrice)
				max = StockSubject.priceList.get(i);
		}
		return max;
	}

	private double getMinPrice() {
		double min = Double.MAX_VALUE;
		for (int i = 0; i < StockSubject.priceList.size(); i++) {
			if (StockSubject.priceList.get(i) < minPrice)
				min = StockSubject.priceList.get(i);
		}
		return min;
	}
}