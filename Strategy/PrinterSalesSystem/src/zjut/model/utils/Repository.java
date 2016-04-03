package zjut.model.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

public class Repository {
	
	/**
	 * 全部打印机名与其类名的Key-Value
	 */
	public static HashMap<String, String> printersRepository = new HashMap<String, String>();
	
	/**
	 * 全部折扣名称与其类名的Key-Value
	 */
	public static HashMap<String, String> discountsRepository = new HashMap<String, String>();
	
	/**
	 * 初始化读取配置文件
	 */
	public static void initProperties() {
		String[] propertiesArray = {"printers.properties", "discounts.properties"};
		// 加载打印机配置文件属性列表、折扣方式配置文件属性列表
		for (int i = 0; i < propertiesArray.length; i++) {
			try {
				Properties properties = new Properties();
				properties.clear();
				properties.load(new FileInputStream(propertiesArray[i]));
				Iterator<String> iterator = properties.stringPropertyNames().iterator();
				while (iterator.hasNext()) {
					String key = iterator.next();
					switch (propertiesArray[i]) {
						case "printers.properties":
							Repository.printersRepository.put(properties.getProperty(key), key);
							break;
						case "discounts.properties":
							Repository.discountsRepository.put(properties.getProperty(key), key);
							break;
					}
				}
			} catch (IOException exception) {
				System.out.println("读取配置文件出错");
			}
		}
	}
}