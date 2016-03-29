package edu.lbz207.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConn {
	// //这里我们默认初始化变量来使用一些已经存在的变量和方法
	// 用户名
	private String user = "root";
	// 密码
	private String password = "123456";
	// 主机
	private String host = "192.168.130.55:3306";
	// 数据库名字
	private String database = "newscomment";
	private String url;
	private Connection conn = null;
	private static DBConn dbconn=null; 

	

	
	/**
	 * 无参数默认构造函数的设计
	 */
	public DBConn() {
		// 显示中文

		this.url = "jdbc:mysql://" + host + "/" + database;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url,user,password);
			if(conn!=null){
				System.out.println("数据库链接成功！");
			}
		} catch (ClassNotFoundException e) {
			System.err.println("class not found:" + e.getMessage());
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	
	
	///获取默认数据库
	public static DBConn getInstatnce(){
		if(dbconn==null){
			dbconn = new DBConn();
		}
		return dbconn;
		
	}
	////数据库链接
	public Connection getConn() {		
		return conn;
	}


}
