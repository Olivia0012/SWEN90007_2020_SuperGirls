package mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseConnection;
import domain.Answer;
import domain.DomainObject;
import domain.Question;
import domain.Relationship;
import domain.Submission;
import shared.IdentityMap;
import shared.UnitOfWork;

public class RelationshipMapper  extends DataMapper{

	@Override
	public int insert(DomainObject obj) {
		Relationship relationship = (Relationship) obj;

		String addNewStm = "INSERT INTO USERANDSUBJECT (subjectid,userid) "
				+ "VALUES (?,?)";

		try {
			PreparedStatement stmt = DatabaseConnection.prepareInsert(addNewStm);
			stmt.setInt(1, relationship.getSubjectId());
			stmt.setInt(2, relationship.getUser().getId());

			stmt.executeUpdate();
			ResultSet keys = stmt.getGeneratedKeys();
			keys.next();
			int id = keys.getInt(1);

			keys.close();
			stmt.close();
			return id;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		} finally {
			DatabaseConnection.closeConnection();
		}
	}

	@Override
	public Boolean update(DomainObject obj) {
		return null;
	}

	@Override
	public Boolean delete(DomainObject obj) {
		Relationship relationship = (Relationship) obj;

		String deleteAnswersStm = "DELETE FROM USERANDSUBJECT WHERE userid = ? AND subjectid=?";

		try {
			PreparedStatement stmt = DatabaseConnection.prepare(deleteAnswersStm);
			stmt.setInt(1, relationship.getUser().getId());
			stmt.setInt(2, relationship.getSubjectId());
			stmt.executeUpdate();

			stmt.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			DatabaseConnection.closeConnection();
		}
	}

	@Override
	public DomainObject findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
