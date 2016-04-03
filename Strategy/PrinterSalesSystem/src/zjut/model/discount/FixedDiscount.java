package zjut.model.discount;

import zjut.model.bean.Discount;

public class FixedDiscount implements Discount{

	private static final double FIXED_AMOUNT = 200;
	
	@Override
	public double discountPrice(double price, int amount) {
		return salePrice(price) * amount;
	}

	@Override
	public double salePrice(double price) {
		return price - FIXED_AMOUNT;
	}
}