package zjut.model.bean;

public abstract class Printer {
	
	/**
	 * 打印机名称
	 */
	protected String printerName;
	
	/**
	 * 打印机售价
	 */
	protected double price;
	
	/**
	 * 获取打印机名称
	 * @return
	 */
	public String getPrintName() {
		return printerName;
	}
	
	/**
	 * 设置打印机名称
	 * @param printerName
	 */
	protected void setPrintName(String printerName) {
		this.printerName = printerName;
	}
	
	/**
	 * 获取打印机价格
	 * @return
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * 设置打印机价格
	 * @param price
	 */
	protected void setPrice(double price) {
		this.price = price;
	}
}