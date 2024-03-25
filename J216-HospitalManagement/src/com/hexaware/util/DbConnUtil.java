package com.hexaware.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnUtil {

	static Connection con;

	public static Connection getDBConn() {

		try {
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hospitalmanagement", "root", "mysqljiv");
		} catch (SQLException e) {
	
			e.printStackTrace();
		}

		return con;
	}

	
		
	}



