package edu.zjut.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadJson {
	
	/**
	 * 读取json文件
	 * @param Path
	 * @return
	 */
	public String readJsonFile(String Path) {
		BufferedReader reader = null;
		String jsonString = "";
		try {
			FileInputStream fileInputStream = new FileInputStream(Path);
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
			reader = new BufferedReader(inputStreamReader);
			String tempString = null;
			while ((tempString = reader.readLine()) != null)
				jsonString += tempString;
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
		return jsonString;
	}
}