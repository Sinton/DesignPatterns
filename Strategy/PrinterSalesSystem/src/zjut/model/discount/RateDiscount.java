package zjut.model.discount;

import zjut.model.bean.Discount;

public class RateDiscount implements Discount{
	
	private static final double RATE = 0.05;

	@Override
	public double discountPrice(double price, int amount) {
		return salePrice(price) * amount;
	}

	@Override
	public double salePrice(double price) {
		return price * (1 - RATE);
	}
}