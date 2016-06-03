package edu.zjut;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import edu.zjut.utils.Utils;

public class Main {

	public static void main(String[] args) throws IOException {
		File file = new File("output.txt");
		if (!file.exists())
			file.createNewFile();
		FileWriter fileWritter = new FileWriter(file.getName(), false);
		ReplaceWriter replaceWriter = new ReplaceWriter(fileWritter);
		char[] textContent = new Utils().getTextContent();
		replaceWriter.write(textContent, 0, textContent.length);
		replaceWriter.flush();
		replaceWriter.close();
	}
}