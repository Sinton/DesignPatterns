package edu.zjut.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Utils {
	
	private static final String BADWORDS_PATH = "badwords.txt";
	private static final String INPUT_PATH = "input.txt";
	
	/**
	 * 获取全部敏感词集合
	 * @return
	 */
	public ArrayList<String> getBadWords() {
		ArrayList<String> badWords = new ArrayList<String>();
		File fileName = new File(BADWORDS_PATH);
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			isr = new InputStreamReader(new FileInputStream(fileName));
			br = new BufferedReader(isr);
			String line;
			while ((line = br.readLine()) != null)
				badWords.add(line);
			br.close();
			isr.close();
		} catch (IOException exception) {
			System.out.println("读取敏感词文件出错");
			exception.printStackTrace();
		}
		return badWords;
	}
	
	/**
	 * 获取输入的敏感词内容
	 * @return
	 */
	public char[] getTextContent() {
		File fileName = new File(INPUT_PATH);
		InputStreamReader isr = null;
		BufferedReader br = null;
		StringBuilder textContent = null;
		try {
			isr = new InputStreamReader(new FileInputStream(fileName));
			br = new BufferedReader(isr);
			textContent = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null)
				textContent.append(line);
			br.close();
			isr.close();
		} catch (IOException exception) {
			System.out.println("读取内容文本文件出错");
			exception.printStackTrace();
		}
		return textContent.toString().toCharArray();
	}
}