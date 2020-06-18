package com.flipkart.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;


/*
 * CLASS DESCRIPTION
 * Util for establishing connection with Database
 * 
 */
public class DBUtils {
	private static  Logger logger = Logger.getLogger(DBUtils.class);
	private static Connection connection = null;
	
	public static Connection getConnection() {

        if (connection != null)
            return connection;
        else {
            try {
                String driver = ("com.mysql.jdbc.Driver");
                String url = ("jdbc:mysql://localhost:3306/smssystem");
                String user = ("root");
                String password = ("root");
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);
            } catch (ClassNotFoundException e) {
                logger.error(e.getMessage());
            } catch (SQLException e) {
            	logger.error(e.getMessage());
            }
            return connection;
        }
    }
}
