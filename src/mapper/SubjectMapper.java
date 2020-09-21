/**
 *  @author Lu Wang
 *
 * */

package mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

import database.DatabaseConnection;
import domain.DomainObject;
import domain.Exam;
import domain.Subject;
import domain.User;
import shared.IdentityMap;

/**
 * This class is the data mapper for subject.
 * */
public class SubjectMapper extends DataMapper {

	/**
	 * Insert a new subject record into subject table
	 * 
	 * @param obj = subject to be inserted
	 * @return indication whether the insert is successful or not
	 * */
	@Override
	public Boolean insert(DomainObject obj) {
		Subject newSubject = (Subject) obj;
		
		String addNewSubjectStm = "INSERT INTO SUBJECT (SUBJECTNUM,SUBJECTTITLE) " + "VALUES (?,?);";
		
		try {
			PreparedStatement stmt = DatabaseConnection.prepare(addNewSubjectStm);
			stmt.setString(1, newSubject.getSubjectCode());
			stmt.setString(2, newSubject.getTitle());
			
			stmt.executeUpdate();
			ResultSet keys = stmt.getGeneratedKeys();
			keys.next();
			int id = keys.getInt(1);
			
			newSubject.setId(id);
			
			IdentityMap<Subject> subjectMap = IdentityMap.getInstance(newSubject);
			subjectMap.put( newSubject.getId(), newSubject);
			keys.close();
			stmt.close();
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally {
			DatabaseConnection.closeConnection();
		}

		
	}

	/**
	 * Update a subject record in the subject table
	 * 
	 * @param obj = subject to be updated
	 * @return indication whether the update is successful or not
	 * */
	@Override
	public Boolean update(DomainObject obj) {
		Subject subject = (Subject) obj;

		String updateSubjectStm = "UPDATE subject SET subjectCode = ?, subjectTitle = ? WHERE subjectid = ?";

		try {
			PreparedStatement stmt = DatabaseConnection.prepare(updateSubjectStm);
			stmt.setString(1, subject.getSubjectCode());
			stmt.setString(2, subject.getTitle());
			stmt.setInt(3, subject.getId());
			stmt.executeUpdate();
						
			IdentityMap<Subject> subjectMap = IdentityMap.getInstance(subject);
			Subject subjectInMap = subjectMap.get(subject.getId());

			// add the updated subject into subject identity map if it is not there.
			if (subjectInMap == null) {
				subjectMap.put(subject.getId(), subject);
			}
			stmt.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally {
			DatabaseConnection.closeConnection();
		}
	}
	
	/**
	 * Delete a subject record in the subject table
	 * 
	 * @param obj = subject to be deleted
	 * @return indication whether the delete is successful or not
	 * */
	@Override
	public Boolean delete(DomainObject obj) {
		Subject subject = (Subject) obj;

		String deleteSubjectStm = "DELETE FROM subject WHERE subjectid = ?";

		try {
			PreparedStatement stmt = DatabaseConnection.prepare(deleteSubjectStm);
			stmt.setInt(1, subject.getId());
			stmt.executeUpdate();
						
			IdentityMap<Subject> subjectMap = IdentityMap.getInstance(subject);
			Subject subjectInMap = subjectMap.get(subject.getId());
			if (subjectInMap != null) {
				subjectMap.put(subject.getId(), null);
			}

			stmt.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally {
			DatabaseConnection.closeConnection();
		}
	}


	/**
	 * find a subject record in the subject table
	 * 
	 * @param subjectId = target subject id
	 * @return subject with the id.
	 * */
	@Override
	public Subject findById(int subjectId) {
		
		// find the subject in the identity map.
		Subject subject = new Subject();
		
		IdentityMap<Subject> subjectMap = IdentityMap.getInstance(subject);
		subject = subjectMap.get(subjectId);

		// find from the DB when it is not in the identity map.
		if (subject == null) {
			List<Subject> result = new ArrayList<Subject>();
			// query a subject by subjectId
			String findSubjectbyIdStm = "SELECT * FROM subject WHERE subjectid = ?";
			
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
			} finally {
				DatabaseConnection.closeConnection();
			}
			if (result.size() > 0) {
				subjectMap.put( result.get(0).getId(), result.get(0));
				return result.get(0);
			}
		}
		return subject;
	}

	/**
	 * find all subjects record in the subject table
	 * 
	 *
	 * @return all the subject records.
	 * */
	public List<Subject> FindAllSubject() {
		Subject subject = new Subject();
		// query all subjects
		String queryAllSubjectStm = "SELECT * FROM subject"; 
		IdentityMap<Subject> subjectMap = IdentityMap.getInstance(subject);
		List<Subject> result = new ArrayList<Subject>();
		
		try {
			PreparedStatement stmt = DatabaseConnection.prepare(queryAllSubjectStm);

			ResultSet rs = stmt.executeQuery();
		//	Subject subject = new Subject();

			while (rs.next()) {
				Integer id = rs.getInt("subjectId");
				String subjectTitle = rs.getString("subjectTitle");
				String subjectCode = rs.getString("subjectNum");
				subject = new Subject(id, subjectTitle, subjectCode);
				result.add(subject);
			}

			if(result.size() > 0) {
				for (int i = 0; i < result.size(); i++) {
					Subject s = subjectMap.get(result.get(i).getId());
					if (s == null) {
						subjectMap.put(result.get(i).getId(), result.get(i));
					}
					System.out.println(
							result.get(i).getId() + "," + result.get(i).getTitle() + "," + result.get(i).getSubjectCode());
				}
				
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			DatabaseConnection.closeConnection();
		}
		return result;
	}
	
	/**
	 * find all subjects record in the subject table
	 * 
	 *
	 * @return all the subject records.
	 * */
	public List<Subject> FindAllSubjectByUserId(int userId) {
		Subject subject = new Subject();
		// query all subjects by user Id
		String queryAllSubjectStm = "SELECT * FROM userandsubject WHERE userid = ?"; 
		IdentityMap<Subject> subjectMap = IdentityMap.getInstance(subject);
		SubjectMapper subjectMapper = new SubjectMapper();
		List<Subject> result = new ArrayList<Subject>();
		Exam exam = new Exam();
	//	IdentityMap<Exam> examMapper = IdentityMap.getInstance(exam);
		ExamMapper examMapper = new ExamMapper();
		
		try {
			PreparedStatement stmt = DatabaseConnection.prepare(queryAllSubjectStm);
			stmt.setInt(1, userId);
			ResultSet rs = stmt.executeQuery();
		//	Subject subject = new Subject();

			while (rs.next()) {
				Integer subjectId = rs.getInt("subjectid");
				Subject subjectResult = subjectMapper.findById(subjectId);
				List<Exam> examList = examMapper.FindAllExamsBySubjectId(subjectId);
				subject = new Subject(subjectId,subjectResult.getSubjectCode(),subjectResult.getTitle(),null,examList);
				result.add(subject);
			}
			
			
			if(result.size() > 0) {
				
				for (int i = 0; i < result.size(); i++) {
					Subject s = subjectMap.get(result.get(i).getId());
					if (s == null) {
						subjectMap.put(result.get(i).getId(), result.get(i));
					}
				}
				String result1 = JSONObject.toJSONString(result);
				System.out.println(result1);
				
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			DatabaseConnection.closeConnection();
		}
		return result;
	}
	

	// Testing the subject mapper.
	public static void main(String args[]) {
		SubjectMapper sm = new SubjectMapper();
		Subject newSubject = new Subject();
		newSubject.setSubjectCode("SWEN90013");
		newSubject.setTitle("Year Long Project");
		// sm.insert(newSubject);
	//	sm.findById(1);
	//	sm.FindAllSubject();
		sm.FindAllSubjectByUserId(4);

	}

}
