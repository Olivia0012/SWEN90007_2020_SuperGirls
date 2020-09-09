/**
 *  @author Lu Wang
 */
package mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import database.DatabaseConnection;
import domain.DomainObject;
import domain.Exam;
import domain.Subject;
import domain.Submission;
import domain.User;
import enumeration.ExamStatus;
import shared.IdentityMap;

/**
 * Submission Mapper class
 *
 */
public class SubmissionMapper extends DataMapper{

	/**
	 * Insert a new subject record into subject table
	 * 
	 * @param obj = subject to be inserted
	 * @return indication whether the insert is successful or not
	 */
	@Override
	public Boolean insert(DomainObject obj) {
		Submission submission = (Submission) obj;
		LocalDateTime createTime = LocalDateTime.now();

		String addNewSubmissionStm = "INSERT INTO SUBMISSION (studentid,examid,totalmark,comment,markerid,marktime,islock,subtime) "
				+ "VALUES (?,?,?,?,?,?,?,?)";

		try {
			PreparedStatement stmt = DatabaseConnection.prepare(addNewSubmissionStm);
			stmt.setInt(1, submission.getStudent().getId());
			stmt.setInt(2, submission.getExam().getId());
			stmt.setFloat(3, submission.getTotalMark());
			stmt.setString(4, submission.getComment());
			stmt.setInt(5, submission.getMarker().getId());
			stmt.setString(6, submission.getMarkTime() + "");
			stmt.setBoolean(7, submission.isLock());
			stmt.setString(8, submission.getSubTime() + "");

			stmt.executeUpdate();
			ResultSet keys = stmt.getGeneratedKeys();
			keys.next();
			int id = keys.getInt(1);

			submission.setId(id);

			IdentityMap<Submission> submissionMap = IdentityMap.getInstance(submission);
			submissionMap.put(submission.getId(), submission);
			keys.close();
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

	/**
	 * Update an submission record in the submission table
	 * 
	 * @param obj = submission to be updated
	 * @return indication whether the update is successful or not
	 */
	@Override
	public Boolean update(DomainObject obj) {
		Submission submission = (Submission) obj;
		LocalDateTime updateTime = LocalDateTime.now();

		String updateSubjectStm = "UPDATE submission SET studentid = ?,examid = ?,totalmark = ?,comment = ?,markerid = ?,marktime = ?,islock = ?,subtime = ? WHERE examid = ?";

		try {
			PreparedStatement stmt = DatabaseConnection.prepare(updateSubjectStm);
			stmt.setInt(1, submission.getStudent().getId());
			stmt.setInt(2, submission.getExam().getId());
			stmt.setFloat(3, submission.getTotalMark());
			stmt.setString(4, submission.getComment());
			stmt.setInt(5, submission.getMarker().getId());
			stmt.setString(6, submission.getMarkTime() + "");
			stmt.setBoolean(7, submission.isLock());
			stmt.setString(8, submission.getSubTime() + "");

			stmt.executeUpdate();

			IdentityMap<Submission> submissionMap = IdentityMap.getInstance(submission);
			Submission submissionInMap = submissionMap.get(submission.getId());

			// add the updated submission into submission identity map if it is not there.
			if (submissionInMap == null) {
				submissionMap.put(submission.getId(), submission);
			}
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

	/**
	 * Delete a subject record in the subject table
	 * 
	 * @param obj = subject to be deleted
	 * @return indication whether the delete is successful or not
	 */
	@Override
	public Boolean delete(DomainObject obj) {
		Submission submission = (Submission) obj;

		String deleteSubjectStm = "DELETE FROM submission WHERE submissionid = ?";

		try {
			PreparedStatement stmt = DatabaseConnection.prepare(deleteSubjectStm);
			stmt.setInt(1, submission.getId());
			stmt.executeUpdate();

			IdentityMap<Submission> submissionMap = IdentityMap.getInstance(submission);
			Submission submissionInMap = submissionMap.get(submission.getId());
			if (submissionInMap != null) {
				submissionMap.put(submission.getId(), null);
			}

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
	

	/**
	 * find a submission record in the submission table
	 * 
	 * @param submissionId = target submission id
	 * @return exam with the id.
	 */
	@Override
	public DomainObject findById(int submissionId) {
		// find the exam in the identity map.
				Submission submission = new Submission();

				IdentityMap<Submission> submissionMap = IdentityMap.getInstance(submission);
				submission = submissionMap.get(submissionId);
				ExamMapper examMapper = new ExamMapper();
				UserMapper userMapper = new UserMapper();

				// find from the DB when it is not in the identity map.
				if (submission == null) {
					List<Submission> result = new ArrayList<Submission>();
					// query a exam by examId
					String findSubmissionbyIdStm = "SELECT * FROM submission WHERE submissionid = ?";

					try {
						PreparedStatement stmt = DatabaseConnection.prepare(findSubmissionbyIdStm);
						stmt.setInt(1, submissionId);
						ResultSet rs = stmt.executeQuery();

						while (rs.next()) {
							Integer id = rs.getInt(1);
							Integer studentid = rs.getInt(2);
							Integer examid = rs.getInt(3);
							float totalmark = rs.getFloat(4);
							String comment = rs.getString(5);
							Integer markerid = rs.getInt(6);
							Date marktime = rs.getDate(7);
							boolean isLocked = rs.getBoolean(8);
							Date subTime = rs.getDate(9);
							
							Exam exam = examMapper.findById(examid);
							User student = userMapper.findById(studentid);
							User marker = userMapper.findById(markerid);
							
							
							submission = new Submission(id, exam, student, totalmark, comment, marker, marktime,subTime,
									isLocked);
							result.add(submission);
						}
						rs.close();
						stmt.close();
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						DatabaseConnection.closeConnection();
					}
					if (result.size() > 0) {
						submissionMap.put(result.get(0).getId(), result.get(0));
						return result.get(0);
					}
				}

				return submission;
	}
	
	/**
	 * find all submissions record in the submission table
	 * 
	 *
	 * @return all the submissions records.
	 * */
	public List<Submission> FindAllSubmission() {
		Submission submission = new Submission();
		String queryAllSubmissionStm = "SELECT * FROM submission"; // query all subjects
		IdentityMap<Submission> submissionMap = IdentityMap.getInstance(submission);
		List<Submission> result = new ArrayList<Submission>();
		ExamMapper examMapper = new ExamMapper();
		UserMapper userMapper = new UserMapper();
		
		try {
			PreparedStatement stmt = DatabaseConnection.prepare(queryAllSubmissionStm);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Integer id = rs.getInt(1);
				Integer studentid = rs.getInt(2);
				Integer examid = rs.getInt(3);
				Float totalmark = rs.getFloat(4);
				String comment = rs.getString(5);
				Integer markerid = rs.getInt(6);
				Date marktime = rs.getDate(7);
				boolean isLocked = rs.getBoolean(8);
				Date subTime = rs.getDate(9);
				
				Exam exam = examMapper.findById(examid);
				User student = userMapper.findById(studentid);
				User marker = userMapper.findById(markerid);
				
				
				submission = new Submission(id, exam, student, totalmark, comment, marker, marktime,subTime,
						isLocked);
				result.add(submission);
			}

			if(result.size() > 0) {
				for (int i = 0; i < result.size(); i++) {
					Submission s = submissionMap.get(result.get(i).getId());
					if (s == null) {
						submissionMap.put(result.get(i).getId(), result.get(i));
					}
					System.out.println(result.get(i).getId() + "," + result.get(i).getStudent().getUserName() + ","
							+ result.get(i).getTotalMark() + "," + result.get(i).getExam().getTitle() + "," + result.get(i).getExam().getSubject().getSubjectCode());
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

	// test
	public static void main(String args[]) {
		SubmissionMapper em = new SubmissionMapper();
		em.FindAllSubmission();
		em.findById(1);
	}


}
