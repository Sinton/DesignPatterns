package edu.zjut;

import java.util.Random;
import java.util.Vector;

import edu.zjut.utils.Tools;

public class LimitInstanceClass {
	
	private int id;
	private boolean isBusy;
	private String accessMessage;
	
	// 使用线程安全的容器
	private static Vector<LimitInstanceClass> classVector;
	
	/**
	 * 构造方式初始化对象属性的初始值
	 */
	private LimitInstanceClass() {
		this.isBusy = false;
		this.accessMessage = "";
	}
	
	/**
	 * 获取实例化对象
	 * @return
	 */
	public static synchronized LimitInstanceClass getInstance() {
		if(classVector == null){
			int count = new Tools().getCount();
			initContainer(count);
			LimitInstanceClass instanceObject = classVector.get(new Random().nextInt(count));
			instanceObject.accessMessage = "对象--ID：" + instanceObject.id + "\t";
			instanceObject.isBusy = true;
			return instanceObject;
		} else {
			for (int i = 0; i < classVector.size(); i++) {
				if (!classVector.elementAt(i).isBusy) {
					classVector.elementAt(i).isBusy = true;
					classVector.elementAt(i).accessMessage = "对象--ID：" + classVector.elementAt(i).id + "\t";
					return classVector.elementAt(i);
				}
			}
		}
		return null;
	}
	
	/**
	 * 实例化对象容器
	 * @param count
	 */
	private static void initContainer(int count) {
		classVector = new Vector<LimitInstanceClass>(count);
		for (int i = 0; i < count; i++) {
			LimitInstanceClass instanceObject = new LimitInstanceClass();
			instanceObject.id = i;
			classVector.add(instanceObject);
		}
	}

	/**
	 * 模拟释放对象
	 */
	public void release() {
		isBusy = false;
		accessMessage = "";
	}
	
	/**
	 * 追加访问信息
	 * @param message
	 */
	public void writeAccessMessage(String message) {
		accessMessage += message;
	}
	
	/**
	 * 输出访问信息
	 */
	public void printAccessMessage() {
		System.out.println(accessMessage);
	}
}