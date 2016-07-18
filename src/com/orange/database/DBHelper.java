package com.orange.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBHelper {

	private String url = null;
	private String path = System.getProperty("user.dir") + "/config/config.mdb";
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	
	public DBHelper(){
		url = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=" + path + ";UID=;PWD=orangeversion1";
	}
	
	public void Connect(){
		try {
			conn = DriverManager.getConnection(url);
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("数据库连接失败");
		}
	}
	
	public String queryPWD(){
		String result = null;
		try {
			rs = stmt.executeQuery("SELECT key_value FROM config WHERE ID=2");
			if(rs != null){
				rs.next();
				result = rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public String queryEM(){
		String result = null;
		try {
			rs = stmt.executeQuery("SELECT key_value FROM config WHERE ID=1");
			if(rs != null){
				rs.next();
				result = rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public String queryOP(){
		String result = null;
		try {
			rs = stmt.executeQuery("SELECT key_value FROM config WHERE ID=3");
			if(rs != null){
				rs.next();
				result = rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public String queryEMPWD(){
		String result = null;
		try {
			rs = stmt.executeQuery("SELECT key_value FROM config WHERE ID=4");
			if(rs != null){
				rs.next();
				result = rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public void updateEM(String email){
		try {
			stmt.execute("UPDATE config set key_value='" + email + "' WHERE ID=1");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updatePWD(String pwd){
		try {
			stmt.execute("UPDATE config set key_value='" + pwd + "' WHERE ID=2");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateOP(String op){
		try {
			stmt.execute("UPDATE config set key_value='" + op + "' WHERE ID=3");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateEMPWD(String pwd){
		try {
			stmt.execute("UPDATE config set key_value='" + pwd + "' WHERE ID=4");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void close(){
		try {
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
