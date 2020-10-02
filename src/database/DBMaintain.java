package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBMaintain {
	
	//Create table
	
	public static void main(String args[]) {
	      Connection c = null;
	      Statement stmt = null;
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/postgres",
	            "postgres", "123");
	         System.out.println("connected succeeded！");
	         stmt = c.createStatement();
	         
	         // Create exam table
	         String sql1 = "CREATE TABLE exam " +
                   "(examid  SERIAL PRIMARY KEY     NOT NULL," +
                   " subjectid      INT    NOT NULL, " +
                   " instructorid            INT     NOT NULL, " +
                   " examtitle        VARCHAR, " +
                   " createtime        VARCHAR, " +
                   " examstatus        VARCHAR, " +
                   " islock          BOOLEAN NOT NULL DEFAULT FALSE)";
	         // Create subject table
	         String sql2 = "CREATE TABLE subject " +
                   "(subjectid  SERIAL PRIMARY KEY     NOT NULL," +
                   " subjectnum        VARCHAR, " +
                   " subjecttitle        VARCHAR)";
	         
	         // Create question table
	         String sql3 = "CREATE TABLE question " +
                   "(questionid  SERIAL PRIMARY KEY     NOT NULL," +
                   " examid      INT    NOT NULL, " +
                   " questionnum            INT     NOT NULL, " +
                   " questiondes        VARCHAR, " +
                   " questionmark        REAL, " +
                   " choice1        VARCHAR, " +
                   " choice2        VARCHAR, " +
                   " choice3        VARCHAR, " +
                   " choice4        VARCHAR, " +
                   " questiontype        VARCHAR)";
	         
	      // Create submissions table
	         String sql4 = "CREATE TABLE submissions " +
                   "(submissionid  SERIAL PRIMARY KEY     NOT NULL," +
                   " examid      INT    NOT NULL, " +
                   " studentid      INT    NOT NULL, " +
                   " totalmark        REAL, " +
                   " markerid      INT    NOT NULL, " +
                   " marktime        VARCHAR, " +
                   " islock          BOOLEAN NOT NULL DEFAULT FALSE," +
                   " subTime        VARCHAR)";
	         
	      // Create answer table
	         String sql5 = "CREATE TABLE answer " +
                   "(answerid  SERIAL PRIMARY KEY     NOT NULL," +
                   " studentid      INT    NOT NULL, " +
                   " examid      INT    NOT NULL, " +
                   " questionid      INT    NOT NULL, " +
                   " answer        VARCHAR, " +
                   " mark        REAL)";
	         
	      // Create userandsubject table
	         String sql = "CREATE TABLE userandsubject " +
                   "(id  SERIAL PRIMARY KEY     NOT NULL," +
                   " userid      INT    NOT NULL, " +
                   " subjectid      INT    NOT NULL)";
	         
	         stmt.executeUpdate(sql);
	         stmt.close();
	         c.close();
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	         System.err.println(e.getClass().getName()+": "+e.getMessage());
	         System.exit(0);
	      }
	      System.out.println("Create Table successfully！");
	   }


}
