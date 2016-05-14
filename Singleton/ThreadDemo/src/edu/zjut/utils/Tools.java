package edu.zjut.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Tools {
	
	private static final String PATH = "InstanceLimit.cfg";
	
	/**
	 * 读取cfg配置文件
	 * @return
	 */
	public Integer getCount() {
		BufferedReader reader = null;
		String cotnt = "";
		try {
			FileInputStream fileInputStream = new FileInputStream(PATH);
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
			reader = new BufferedReader(inputStreamReader);
			String line = null;
			while ((line = reader.readLine()) != null)
				cotnt += line;
			reader.close();
		} catch (IOException exception) {
			exception.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException exception) {
					exception.printStackTrace();
				}
			}
		}
		return Integer.valueOf(cotnt);
	}
}