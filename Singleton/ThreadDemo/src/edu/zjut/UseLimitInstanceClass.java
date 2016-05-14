package edu.zjut;

public class UseLimitInstanceClass {

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			AccessLimitInstanceClassThread accessThread = new AccessLimitInstanceClassThread(String.valueOf(i));
			accessThread.start();
		}
	}
}