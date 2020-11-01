/**
 * This class is used for DB connection.
 * 
 * @author Lu Wang
 */

package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {

		private static final String driver = "org.postgresql.Driver";
	private static final String username = "xypeqopncqvina";
	private static final String password = "e59de30378729846a33d6de2f20ff79c037deec5aac182b4fec3d5c2724e8bb0";
	private static final String url = "jdbc:postgresql://ec2-52-7-15-198.compute-1.amazonaws.com:5432/d2rqtviirotd8i";
	/*
	  private static final String driver = "org.postgresql.Driver";
	  private static final String username = "postgres"; 
	  private static final String password ="123"; 
	  private static final String url =
	  "jdbc:postgresql://localhost:5432/postgres"; */
	
	// minimum number
	private static final int minCount = 1;
	// Maximum number
	private static final int maxCount = 10;
	public static List<Connection> conPool = new ArrayList<Connection>();

	public static Connection connection() {
		try {
			Class.forName(driver);
			Connection c = DriverManager.getConnection(url, username, password);
			conPool.add(c);
			return c;
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
			return null;
		}
		// System.out.println("Opened database successfully");

	}

	public synchronized static Connection getConnection() {
		Connection conn = null;
		if (conPool.size() == 0) {
			conn = connection();

		} else {
			conn = conPool.remove(0);
		}
		System.err.println(conPool.size());
		return conn;
	}

	public synchronized static void closeConnection(Connection conn) {
		if (conPool.size() < maxCount) {
			conPool.add(conn);
		}
	}

	public static PreparedStatement prepareInsert(String statement) {
		PreparedStatement stmt = null;
		Connection c = null;
		try {
			c = getConnection();
			stmt = c.prepareStatement(statement, Statement.RETURN_GENERATED_KEYS);
			return stmt;
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		closeConnection(c);
		return null;
	}

	public static PreparedStatement prepare(String statement) {
		PreparedStatement stmt = null;
		Connection c = null;
		try {
			c = getConnection();
			stmt = c.prepareStatement(statement);
			return stmt;
		} catch (Exception e) {
			e.printStackTrace();

			System.exit(0);
		} finally {
			closeConnection(c);
		}

		return null;
	}

	public static void main(String args[]) {
		DatabaseConnection dbc = new DatabaseConnection();
		dbc.connection();

	}

}
