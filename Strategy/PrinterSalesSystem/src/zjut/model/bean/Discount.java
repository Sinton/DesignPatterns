package zjut.model.bean;

public interface Discount {
	double discountPrice(double price, int amount);
	double salePrice(double price);
}