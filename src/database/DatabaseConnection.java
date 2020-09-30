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


public class DatabaseConnection {
	private static final String driver = "org.postgresql.Driver";
	private static final String username = "xypeqopncqvina";
	private static final String password = "e59de30378729846a33d6de2f20ff79c037deec5aac182b4fec3d5c2724e8bb0";
	private static final String url = "jdbc:postgresql://ec2-52-7-15-198.compute-1.amazonaws.com:5432/d2rqtviirotd8i";
	
/*	private static final String driver = "org.postgresql.Driver";
	private static final String username = "postgres";
	private static final String password = "123";
	private static final String url = "jdbc:postgresql://localhost:5432/postgres";
	*/
	
	
	public static Connection c = null;

	public static boolean connection() {
		try {
			Class.forName(driver);
			c = DriverManager.getConnection(url, username, password);

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
			return false;
		}
	//	System.out.println("Opened database successfully");
		return true;
	}

	public static void closeConnection() {
		try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public static PreparedStatement prepareInsert(String statement) {
		PreparedStatement stmt = null;
		try {
			if(connection())
				stmt = c.prepareStatement(statement,Statement.RETURN_GENERATED_KEYS);
			return stmt;
		} catch (Exception e) {
			return null;
		}
	}

	public static PreparedStatement prepare(String statement) {
		PreparedStatement stmt = null;
		try {
			if(connection())
				stmt = c.prepareStatement(statement);
			return stmt;
		} catch (Exception e) {
			return null;
		}

	}

	public static void main(String args[]) {
		DatabaseConnection dbc = new DatabaseConnection();
		dbc.connection();

	}

	
}
