package edu.lbz207.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class RWFile {

	public static String getStrFromFile(String filePath) {

		/* 文件的读取 */
		StringBuffer sb = new StringBuffer();
		BufferedReader br = null;
		File file = new File(filePath);
		// /判断文件是否存在
		if (!file.exists()) {
			System.out.println(filePath + "文件没有找到 ");
			return null;
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

		return sb.toString();
	}
}
