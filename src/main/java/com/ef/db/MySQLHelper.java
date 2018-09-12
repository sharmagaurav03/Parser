package com.ef.db;

import static com.ef.Constant.CONNECTION_STRING;
import static com.ef.Constant.MYSQL_DRIVER;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLHelper implements JDBCHelper {
	
	public MySQLHelper() {
		try {
			Class.forName(MYSQL_DRIVER).newInstance();
		} catch (Exception exception) {
			throw new RuntimeException("Unable to load jdbc driver.", exception);
		}
	}

	public Connection getConnection() {
		Connection conn = null;
		try {
			//TODO: load password from encrypted property file. Decrypt it on the fly before using it. Discard port usage.
			conn = DriverManager.getConnection(CONNECTION_STRING);

		} catch (SQLException exception) {
			throw new RuntimeException("Unable to get jdbc connection.", exception);
		}
		return conn;
	}
}