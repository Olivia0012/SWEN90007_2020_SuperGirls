package mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import database.DatabaseConnection;

public class ExclusiveWriteLockManager implements LockManager {

	private static ExclusiveWriteLockManager eInstance;

	public static ExclusiveWriteLockManager getInstance() {
		synchronized (ExclusiveWriteLockManager.class) {
			if (eInstance == null) {
				eInstance = new ExclusiveWriteLockManager();
			}
		}
		return eInstance;

	}

	/**
	 * Obtain a lock for 30 min.
	 */
	@Override
	public String acquireLock(int id, String table,  String owner) {
		String result = hasLock(id,table, owner);
		SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss"); 
        Date date = new Date(); 

		if (result.equals("false")) {

			String sql = "INSERT INTO LOCK (id,tablename,owner,time) VALUES(?,?,?,?)";
			try {
				PreparedStatement stmt = DatabaseConnection.prepareInsert(sql);
				stmt.setInt(1, id);
				stmt.setString(2, table);
				stmt.setString(3, owner);
				stmt.setString(4, sdf.format(date));

				stmt.executeUpdate();
				
				result = "true";
				
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result = "Errors happened during acquiring the lock.";
			} finally {
			//	DatabaseConnection.closeConnection();
			}
		}
		return result;
	}

	// check if the user or other user is holding the lock
	private String hasLock(int id, String table, String owner) {
		String result = "false";

		String findExambyIdStm = "SELECT * FROM lock WHERE id = ? AND tablename = ?";

		try {
			PreparedStatement stmt = DatabaseConnection.prepare(findExambyIdStm);
			stmt.setInt(1, id);
			stmt.setString(2, table);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				String token = rs.getString(4);
				if(token.equals(owner)) {
					result = "true";
				}else {
					result = "Sorry, other instructors are editing now, please try again later.";
				}
			}
			
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		//	DatabaseConnection.closeConnection();
		}

		return result;
	}
	

	@Override
	public void releaseLock(int id, String table, String owner) {
		String deleteSubjectStm = "DELETE FROM lock WHERE id = ? AND tablename = ? AND owner = ?";

		try {
			PreparedStatement stmt = DatabaseConnection.prepare(deleteSubjectStm);
			stmt.setInt(1, id);
			stmt.setString(2, table);
			stmt.setString(3, owner);
			stmt.executeUpdate();

			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		//	DatabaseConnection.closeConnection();
		}

	}
	
	

	public void releaseAllLocks(String owner) {
		String deleteSubjectStm = "DELETE FROM lock WHERE owner=?";

		try {
			PreparedStatement stmt = DatabaseConnection.prepare(deleteSubjectStm);
			stmt.setString(1, owner);
			stmt.executeUpdate();

			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		//	DatabaseConnection.closeConnection();
		}

	}
	
	/**
	 *  Delete expired locks
	 */
	@Override
	public void releaseExpiredLocks() {
		SimpleDateFormat sdf = new SimpleDateFormat(); 
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String time = sdf.format(date);
       
		String deleteSubjectStm = "DELETE FROM lock WHERE timestamp '"+ time+"' - to_timestamp(time,'yyyy-MM-dd HH24:MI:ss') > interval '30 min' ";

		try {
			PreparedStatement stmt = DatabaseConnection.prepare(deleteSubjectStm);
			
			stmt.executeUpdate();

			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		//	DatabaseConnection.closeConnection();
		}
		
	}
}
