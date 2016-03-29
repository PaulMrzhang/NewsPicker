package edu.lbz207.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

public class CommentDownload {
	
	public static void main(String[] args) {
		String url="http://news.163.com/14/0114/19/9IISVTCG0001124J.html";
		String url0="http://comment.news.163.com/news_guonei8_bbs/9IISVTCG0001124J.html";
		String url1="http://comment.news.163.com/data/news_guonei8_bbs/df/9IISVTCG0001124J_1.html";
		String url01="http://comment.news.163.com/cache/newlist/news_guonei8_bbs/9IISVTCG0001124J_1.html";
		String url02="http://comment.news.163.com/cache/newlist/news_guonei8_bbs/9IISVTCG0001124J_126.html";
		
		String uurl = "http://www.tuandai.com/pages/invest/invest_list.aspx";
		CommentDownload.download(uurl,"utf-8","llfile.html");
	}

	public static void download(String url,String fileEncode,String fileName) {
		URL httpUrl = null;
		BufferedReader in = null;
		BufferedWriter Buff=null;  
		try {
			httpUrl = new URL(url);
			URLConnection httpConn = httpUrl.openConnection();
			
			httpConn.setDoOutput(true);
			httpConn.connect(); //发出连接
			String temp;
			StringBuffer sb = new StringBuffer();
			////数据读入流的设置
			in = new BufferedReader(new InputStreamReader(httpUrl.openStream(),fileEncode));
			//final BufferedReader in = new BufferedReader(new InputStreamReader(httpUrl.openStream(),"utf-8"));
			
			
			while ((temp = in.readLine()) != null) {			
				sb.append(temp+"\n");
			}
			in.close();
			Buff=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("temp/"+fileName)),fileEncode));   
			
            long begin0 = System.currentTimeMillis();   
            Buff.write(sb.toString());   
            Buff.flush();   
            Buff.close();   
            long end0 = System.currentTimeMillis();   
            System.out.println("BufferedOutputStream执行耗时: "+ (end0 - begin0) + " 豪秒"); 
			
			
			//System.out.println(sb);
			
			
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			////检查文件是否存在
			File file=new File("temp/"+fileName);    
			if(file.exists())    
			{    
				System.out.println("exec final! ");
			}    
		}
		
		
	}

}
