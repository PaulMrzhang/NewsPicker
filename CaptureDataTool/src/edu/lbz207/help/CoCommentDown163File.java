package edu.lbz207.help;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.lbz207.db.DBConn;
import edu.lbz207.db.DBUtil;
import edu.lbz207.model.CommentData;
import edu.lbz207.util.CommentDownload;
import edu.lbz207.util.JsonComFileProcess;
import edu.lbz207.util.JsonRead;

public class CoCommentDown163File {

	public List<CommentData> CoComDownAnalyze(String comturl, int pages) {
		///等待下载的评论页面列表
		List<String> commUrlList = new ArrayList<String>();
		int totalPages = 1;	
		String tempCommUrl = "";
		String CommUrlArr = comturl.substring(0, comturl.length()-5);
		totalPages = pages;
		for(int i=1;i<=totalPages;i++){
			tempCommUrl=CommUrlArr+"_"+i+".html";
			commUrlList.add(tempCommUrl);		
		}
        //获取响应的评论的链接		
		for(int i=0;i<commUrlList.size();i++){
			System.out.println(commUrlList.get(i));
		}
		
		 /////时间延迟
	    try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("---------------step4----------------");		
		//step4: 通过评论的链接来下载数据
		String downFileName="CommentFile";
		for(int i=0;i<totalPages;i++){
			CommentDownload.download(commUrlList.get(i), "utf-8", downFileName+(i+1)+".html");
		}
		
		
		 /////时间延迟
	    try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("---------------step5----------------");		
		//step5: 解析 评论的链接下载的数据
		String file ="temp/CommentFile1.html";////默认初始化
		List<CommentData> cdLists = new ArrayList<CommentData>();
		for(int i=0;i<totalPages;i++){
			file="temp/"+downFileName+(i+1)+".html";
			String str = JsonComFileProcess.getJsonStrFromFile(file);	
			List<CommentData> cdList = new ArrayList<CommentData>();
			cdList=JsonRead.parseJsonToObjectList(str);
			cdLists.addAll(cdList);
			
		}
		
		return cdLists;
		/**
		 * 数据库操作 暂时不执行
		 */
//		DBConn dbc = DBConn.getInstatnce();
//		Connection conn = dbc.getConn();
//		String inSql = "insert into commentdatas(newsid,source,username,location,content,dtime) values(?,?,?,?,?,?)";
//		PreparedStatement pstmt = null;
//		for(int i=0;i<totalPages;i++){
//			file="temp/"+downFileName+(i+1)+".html";
//			String str = JsonComFileProcess.getJsonStrFromFile(file);
//			List<CommentData> cdList = new ArrayList<CommentData>();
//			cdList=JsonRead.parseJsonToObjectList(str);			
//			DBUtil.writeToDB(cdList,conn,pstmt,inSql);
//		}
//		////关闭数据库链接
//		try {
//			//pstmt.close();
//			conn.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println("数据获取完毕!");	
		
		/**
		 * 数据库操作 暂时不执行
		 */
		
		
		
	}

}
