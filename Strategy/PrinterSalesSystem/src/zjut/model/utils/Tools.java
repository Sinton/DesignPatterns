package zjut.model.utils;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class Tools {
	
	/**
	 * ��ȡ���д�ӡ��
	 * @return
	 */
	public ArrayList<String> getAllPrinters() {
		ArrayList<String> printers = new ArrayList<String>();
		printers.addAll(Repository.printersRepository.values());
		return printers;
	}

	/**
	 * ��ȡ�����ۿ۷�ʽ
	 * @return
	 */
	public ArrayList<String> getAllDiscounts() {
		ArrayList<String> discounts = new ArrayList<String>();
		discounts.addAll(Repository.discountsRepository.values());
		return discounts;
	}
	
	/**
	 * ͨ������Value���Ҹ����Ӧ��Key
	 * @param name
	 * @return
	 */
	public String getClassName(String name, String repository) {
		Iterator<Map.Entry<String, String>> iterator = null;
		switch (repository) {
			case "discounts":
				iterator = Repository.discountsRepository.entrySet().iterator();
				break;
			case "printers":
				iterator = Repository.printersRepository.entrySet().iterator();
				break;
		}
		while (iterator.hasNext()) {
			Map.Entry<String, String> entry = iterator.next();
			if (entry.getValue().equals(name))
				return entry.getKey();
		}
		return null;
	}
}