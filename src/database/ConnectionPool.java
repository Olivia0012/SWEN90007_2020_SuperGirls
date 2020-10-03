package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPool {
	private static final String driver = "org.postgresql.Driver";
	private static final String username = "xypeqopncqvina";
	private static final String password = "e59de30378729846a33d6de2f20ff79c037deec5aac182b4fec3d5c2724e8bb0";
	private static final String url = "jdbc:postgresql://ec2-52-7-15-198.compute-1.amazonaws.com:5432/d2rqtviirotd8i";

//    private static final String username = "postgres";
//    private static final String password = "admin";
//    private static final String dbUrl = "jdbc:postgresql://localhost:5433/postgres";

    private ConnectionPool(){
    }

    private Connection connection = getConnection();

    private static ConnectionPool instance = null;

    public static ConnectionPool getInstance(){
        if (instance==null)
            instance = new ConnectionPool();
        return instance;
    }

    Connection getConnection() {

        try {
            Class.forName(driver).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}

