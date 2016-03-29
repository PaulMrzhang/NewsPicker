package edu.lbz207.db;

import java.sql.DriverManager;
import java.sql.Connection;
 
 
public class MysqlDemo {
    public static void main(String[] args) throws Exception {
    	 try
    	    {
    	      String url="jdbc:mysql://192.168.130.55:3306/test";
    		  String user="root";
    	      String pwd="123456";
    	      
    	     //加载驱动，这一句也可写为：Class.forName("com.mysql.jdbc.Driver");
    	     Class.forName("com.mysql.jdbc.Driver").newInstance();
    	     //建立到MySQL的连接
    	     Connection conn = DriverManager.getConnection(url,user, pwd);
    	     if(conn!=null){
    	    	 System.out.println("CHENGGONG");
    	     }
    	     System.out.println("--------------");
    	     conn.close();
    	    }
    	    catch (Exception ex)
    	    {
    	      System.out.println("Error : " + ex.toString());
    	    }
 
    }
 
}
