package mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import database.DatabaseConnection;
import domain.DomainObject;
import domain.Exam;
import domain.Subject;
import domain.User;
import enumeration.ExamStatus;
import shared.IdentityMap;

public class SubjectMapper extends DataMapper {

	private String queryAllSubject = "select * from subject"; // query all subjects

	private String addNewSubjectStm = "INSERT INTO SUBJECT (SUBJECTNUM,SUBJECTTITLE) " + "VALUES (?,?);";// insert new

	// query all subjects
	private String findSubjectbyIdStm = "SELECT * FROM subject WHERE subjectid = ?";

	@Override
	protected Boolean insert(DomainObject obj) {
		Subject newSubject = (Subject) obj;
		PreparedStatement stmt = DatabaseConnection.prepare(addNewSubjectStm);

		try {
			stmt.setString(1, newSubject.getSubjectCode());
			stmt.setString(2, newSubject.getTitle());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	protected Boolean update(DomainObject obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Boolean delete(DomainObject obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Subject findById(int subjectId) {

		// find the subject in the identity map.
		Subject subject = new Subject();
		IdentityMap<Subject> subjectMap = IdentityMap.getInstance(subject);
		subject = subjectMap.get(subjectId);

		// find from the DB when it is not in the identity map.
		if (subject == null) {
			List<Subject> result = new ArrayList<Subject>();

			try {
				PreparedStatement stmt = DatabaseConnection.prepare(findSubjectbyIdStm);
				stmt.setInt(1, subjectId);
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					Integer id = rs.getInt(1);
					String subjectCode = rs.getString(2);
					String subjectTitle = rs.getString(3);

					subject = new Subject(id, subjectCode, subjectTitle);
					result.add(subject);
				}
				rs.close();
				stmt.close();

			} catch (Exception e) {
				e.printStackTrace();
				return null;
			} finally {
				DatabaseConnection.closeConnection();
			}
			if (result.size() > 0) {
				return result.get(0);
			}
		}
		return subject;
	}

	protected void FindAllSubject() {
		PreparedStatement stmt = DatabaseConnection.prepare(queryAllSubject);

		List<Subject> result = new ArrayList<Subject>();
		ResultSet rs;
		try {
			rs = stmt.executeQuery();
			Subject subject = new Subject();

			while (rs.next()) {
				Integer id = rs.getInt("subjectId");
				String subjectTitle = rs.getString("subjectTitle");
				String subjectCode = rs.getString("subjectNum");
				subject = new Subject(id, subjectTitle, subjectCode);
				result.add(subject);
			}

			for (int i = 0; i < result.size(); i++) {
				System.out.println(
						result.get(i).getId() + "," + result.get(i).getTitle() + "," + result.get(i).getSubjectCode());
			}

			rs.close();

			stmt.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DatabaseConnection.closeConnection();
		}

	}

	public static void main(String args[]) {
		SubjectMapper sm = new SubjectMapper();
		Subject newSubject = new Subject();
		newSubject.setSubjectCode("SWEN90013");
		newSubject.setTitle("Year Long Project");
	//	sm.insert(newSubject);
		sm.findById(1);
		sm.FindAllSubject();

	}

}
