package edu.zjut.model;

public class SaleMachine {
	
	public String buy(String type, double money) {
		String message = null; 

		if (type.equals("Orange")) {
			if (money != 50) {
				message = "请取走饮料，找回硬币5角!";
				return message.toString();
			} else
				message = "请取走饮料!";
		} else if (type.equals("Beer")) {
			if (money != 50) {
				message = "请取走饮料，找回硬币5角!";
				return message.toString();
			}
			message = "请取走饮料!";
		} else if ((money % 100 == 0) && type.equals("Coffee")) {
			message = "请取走饮料!";
		}
		return message.toString();
	}
}