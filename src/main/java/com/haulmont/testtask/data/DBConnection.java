package com.haulmont.testtask.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	public static Connection getConnection() {
		
		Connection con = null;

		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			System.out.println("HSQLDB JDBCDriver Loaded");
			con = DriverManager.getConnection(
					"jdbc:hsqldb:file:db/car_service_db;shutdown=true;ifexists=false", "SA", "");
			System.out.println("HSQLDB Connection Created");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

}

