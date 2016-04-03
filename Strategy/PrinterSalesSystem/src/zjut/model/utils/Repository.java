package zjut.model.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

public class Repository {
	
	/**
	 * ȫ����ӡ��������������Key-Value
	 */
	public static HashMap<String, String> printersRepository = new HashMap<String, String>();
	
	/**
	 * ȫ���ۿ���������������Key-Value
	 */
	public static HashMap<String, String> discountsRepository = new HashMap<String, String>();
	
	/**
	 * ��ʼ����ȡ�����ļ�
	 */
	public static void initProperties() {
		String[] propertiesArray = {"printers.properties", "discounts.properties"};
		// ���ش�ӡ�������ļ������б��ۿ۷�ʽ�����ļ������б�
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
				System.out.println("��ȡ�����ļ�����");
			}
		}
	}
}