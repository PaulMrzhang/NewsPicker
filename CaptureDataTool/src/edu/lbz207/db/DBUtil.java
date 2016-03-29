package edu.lbz207.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import edu.lbz207.model.CommentData;

public class DBUtil {

	public static void writeToDB(List<CommentData> cdList) {
		DBConn dbc = DBConn.getInstatnce();
		Connection conn = dbc.getConn();
		String inSql = "insert into commentdatas(newsid,source,username,location,content,dtime) values(?,?,?,?,?,?)";
		PreparedStatement pstmt = null;
		int i = 0;
		for(CommentData cd:cdList){
			 try {
				 pstmt = (PreparedStatement) conn.prepareStatement(inSql);
				 pstmt.setString(1,cd.getNewsid());
			     pstmt.setString(2,cd.getSource());
			     pstmt.setString(3,cd.getUsername());
			     pstmt.setString(4,cd.getLocation());
			     pstmt.setString(5,cd.getContent());
			     pstmt.setString(6,cd.getDateTime());
			    
			     i = pstmt.executeUpdate();
			     System.out.println(i);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	   
	    
	}

	public static void testSelectDB() {
		// /数据库链接的获取
		DBConn dbc = DBConn.getInstatnce();
		Connection conn = dbc.getConn();
		try {
			Statement stmt = conn.createStatement();
			//执行SQL  
	        String sql = "select * from commentdatas";  
	        java.sql.ResultSet res = stmt.executeQuery(sql);  
	          
	        //5.打印结果集里的数据  
	        while(res.next()) {  
	            System.out.print("the id: ");  
	            System.out.println(res.getString("newsid"));  
	            System.out.print("the user: ");  
	            System.out.println(res.getString("source"));  
	            System.out.print("the address: ");  
	            System.out.println(res.getString("content"));  
	            System.out.println();  
	        }  
	        
	        res.close();  
	        stmt.close();  
	        conn.close();  
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		testSelectDB();
	}

	public static void writeToDB(List<CommentData> cdList, Connection conn,
			PreparedStatement pstmt, String inSql) {
		for(CommentData cd:cdList){
			 try {
				 pstmt = (PreparedStatement) conn.prepareStatement(inSql);
				 pstmt.setString(1,cd.getNewsid());
			     pstmt.setString(2,cd.getSource());
			     pstmt.setString(3,cd.getUsername());
			     pstmt.setString(4,cd.getLocation());
			     pstmt.setString(5,cd.getContent().trim());
			     pstmt.setString(6,cd.getDateTime());
			     System.out.println("插入数据库："+cd.getNewsid()+cd.getSource()+cd.getUsername()+cd.getLocation()+cd.getContent().trim()+cd.getDateTime());
			     pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		try {
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
