package edu.lbz207.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
/**
 * 文本内容获取字符串
 * @author MrLBZ
 *
 */
public class JsonComFileProcess {
	public static void main(String[] args) {
		String file ="temp/CommentFile2.html";
		String str = JsonComFileProcess.getJsonStrFromFile(file);	
		System.out.println(str);
		JsonRead.parseJson(str);
	}
	///重命名！！！
	public static String getJsonStrFromFile(String filePath) {
		/* 文件的读取 */
		StringBuffer sb=new StringBuffer();
		BufferedReader br = null;
		File file = new File(filePath);
		// /判断文件是否存在
		if (!file.exists()) {
			System.out.println(filePath + "文件没有找到 ");
			return "";
		}
		// /boardId = "
		try {
			br = new BufferedReader(new FileReader(filePath));
			String tempString = "";
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = br.readLine()) != null) {
				sb.append(tempString);
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		
		int begin = 0;
		int end = 0;
		begin = sb.toString().indexOf("{");
		end = sb.toString().lastIndexOf("}");
		//System.out.println(begin+"--->"+end);
		////获得符合json格式的字符串
		String str = sb.toString().substring(begin, end+1);	
		return str;
		
	}


}
