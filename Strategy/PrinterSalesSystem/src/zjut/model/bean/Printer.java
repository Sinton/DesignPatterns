package zjut.model.bean;

public abstract class Printer {
	
	/**
	 * ��ӡ������
	 */
	protected String printerName;
	
	/**
	 * ��ӡ���ۼ�
	 */
	protected double price;
	
	/**
	 * ��ȡ��ӡ������
	 * @return
	 */
	public String getPrintName() {
		return printerName;
	}
	
	/**
	 * ���ô�ӡ������
	 * @param printerName
	 */
	protected void setPrintName(String printerName) {
		this.printerName = printerName;
	}
	
	/**
	 * ��ȡ��ӡ���۸�
	 * @return
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * ���ô�ӡ���۸�
	 * @param price
	 */
	protected void setPrice(double price) {
		this.price = price;
	}
}