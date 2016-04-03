package zjut.model.discount;

import zjut.model.bean.Discount;

public class NewDiscount implements Discount {

	@Override
	public double discountPrice(double price, int amount) {
		return salePrice(price) * amount;
	}

	@Override
	public double salePrice(double price) {
		return price > 400 ? price * 0.85 : price;
	}
}