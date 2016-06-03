package edu.zjut;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

import edu.zjut.utils.Utils;

public class ReplaceWriter extends Writer{
	
	public static ArrayList<String> badWords = new Utils().getBadWords();
	
	protected Writer out;
	
	protected ReplaceWriter(Writer out) {
        super(out);
        this.out = out;
    }
	
	/**
	 * 重写父类的方法并且替换敏感词，然后再写入到文件
	 */
	@Override
	public void write(char[] cbuf, int off, int len) throws IOException {
		String content = "";
		for (int i = 0; i < cbuf.length; i++)
			content += cbuf[i];
		System.out.println(content);
		System.out.println("============");
		System.out.println("敏感词处理后");
		System.out.println("============");
		for(String word : badWords)
            if(content.contains(word))
                content = content.replaceAll(word, "*");
		out.write(content);
		System.out.println(content);
	}

	@Override
	public void flush() throws IOException {
		out.flush();
	}

	@Override
	public void close() throws IOException {
		out.close();
	}
}