package com.management;

import java.io.IOException;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnectionManager 
{
	private static Connection con = null;
	private static Properties props = new Properties();
   
	public  static Connection getConnection() throws SQLException
	{
		try(FileInputStream fis = new FileInputStream("database.properties"))
		{
			props.load(fis);
			
			String driverName = props.getProperty("DB_DRIVER_CLASS");
			String url = props.getProperty("DB_URL");
			String userName = props.getProperty("DB_USERNAME");
			String passWord = props.getProperty("DB_PASSWORD");
			
			Class.forName(driverName);
			con = DriverManager.getConnection(url, userName, passWord);
		}
		catch(ClassNotFoundException | IOException e)
		{
			e.printStackTrace();
		}
		return con;
	}
}