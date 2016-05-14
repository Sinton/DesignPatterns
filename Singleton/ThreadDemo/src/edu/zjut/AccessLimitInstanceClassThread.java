package edu.zjut;

import java.util.Random;

public class AccessLimitInstanceClassThread extends Thread {

	Random random = new Random();
	
	/**
	 * 构造函数初始化线程名
	 * @param name
	 */
	public AccessLimitInstanceClassThread(String name) {
		this.setName("线程：" + name + "  ");
	}

	@Override
	public void run() {
		LimitInstanceClass object = LimitInstanceClass.getInstance();
		while (object == null)
			object = LimitInstanceClass.getInstance();
		object.writeAccessMessage("正在执行" + currentThread().getName());
		try {
			sleep(random.nextInt(5) * 1000);
			object.printAccessMessage();
			object.release();
			System.out.println("该对象已经被" + currentThread().getName() + "释放");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}