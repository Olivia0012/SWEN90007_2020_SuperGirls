package mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

	@Override
	public String acquireLock(int id, String table,  String owner) {
		String result = hasLock(id,table, owner);

		if (result.equals("false")) {

			String sql = "INSERT INTO LOCK (id,tablename,owner) VALUES(?,?,?)";
			try {
				PreparedStatement stmt = DatabaseConnection.prepareInsert(sql);
				stmt.setInt(1, id);
				stmt.setString(2, table);
				stmt.setString(3, owner);

				stmt.executeUpdate();
				
				result = "true";
				
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result = "Errors happened during acquiring the lock.";
			} finally {
				DatabaseConnection.closeConnection();
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
					result = "Others are holding the lock.";
				}
			}
			
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.closeConnection();
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
			DatabaseConnection.closeConnection();
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
			DatabaseConnection.closeConnection();
		}

	}
}
