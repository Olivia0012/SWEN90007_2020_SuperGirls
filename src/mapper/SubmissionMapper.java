/**
 *  @author Lu Wang
 */
package mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseConnection;
import domain.Answer;
import domain.DomainObject;
import domain.Exam;
import domain.Submission;
import domain.User;
import shared.IdentityMap;

/**
 * Submission Mapper class
 *
 */
public class SubmissionMapper extends DataMapper {

	/**
	 * Insert a new subject record into subject table
	 * 
	 * @param obj = subject to be inserted
	 * @return indication whether the insert is successful or not
	 */
	@Override
	public int insert(DomainObject obj) {
		Submission submission = (Submission) obj;
		int id = 0;

		String addNewSubmissionStm = "INSERT INTO SUBMISSIONS (studentid,examid,totalmark,islock) "
				+ "VALUES (?,?,?,?)";

		try {
			PreparedStatement stmt = DatabaseConnection.prepareInsert(addNewSubmissionStm);
			stmt.setInt(1, submission.getStudent().getId());
			stmt.setInt(2, submission.getExam().getId());
			stmt.setFloat(3, submission.getTotalMark());
			stmt.setBoolean(4, submission.isLock());

			int affectedRows = stmt.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("Creating user failed, no rows affected.");
			}
			try (ResultSet keys = stmt.getGeneratedKeys()) {
				if (keys.next()) {
					id = keys.getInt(1);
				}
				keys.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			submission.setId(id);

			IdentityMap<Submission> submissionMap = IdentityMap.getInstance(submission);
			submissionMap.put(submission.getId(), submission);

			stmt.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			DatabaseConnection.closeConnection();
		}
		return id;

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

		String updateSubjectStm = "UPDATE submissions SET studentid = ?,examid = ?,totalmark = ?,comment = ?,markerid = ?,marktime = ?,islock = ?,subtime = ? WHERE submissionid = ?";

		try {
			PreparedStatement stmt = DatabaseConnection.prepare(updateSubjectStm);
			stmt.setInt(1, submission.getStudent().getId());
			stmt.setInt(2, submission.getExam().getId());
			stmt.setFloat(3, submission.getTotalMark());
			stmt.setString(4, submission.getComment());
			if (submission.getMarker() != null)
				stmt.setInt(5, submission.getMarker().getId());
			else
				stmt.setInt(5, 0);
			stmt.setString(6, submission.getMarkTime() + "");
			stmt.setBoolean(7, submission.isLock());
			stmt.setString(8, submission.getSubTime() + "");
			stmt.setInt(9, submission.getId());

			stmt.executeUpdate();

			IdentityMap<Submission> submissionMap = IdentityMap.getInstance(submission);

			// add the updated submission into submission identity map if it is not there.
			submissionMap.put(submission.getId(), submission);

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
	public Submission findById(int submissionId) {
		// find the exam in the identity map.
		Submission submission = new Submission();

		IdentityMap<Submission> submissionMap = IdentityMap.getInstance(submission);
		submission = submissionMap.get(submissionId);
		ExamMapper examMapper = new ExamMapper();
		UserMapper userMapper = new UserMapper();
		AnswerMapper answerMapper = new AnswerMapper();

		// find from the DB when it is not in the identity map.
		if (submission == null) {
			List<Submission> result = new ArrayList<Submission>();
			// query a exam by examId
			String findSubmissionbyIdStm = "SELECT * FROM submissions WHERE submissionid = ?";

			try {
				PreparedStatement stmt = DatabaseConnection.prepare(findSubmissionbyIdStm);
				stmt.setInt(1, submissionId);
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					Integer id = rs.getInt(1);
					Integer examid = rs.getInt(2);
					Integer studentid = rs.getInt(3);
					Float totalmark = rs.getFloat(4);
					Integer markerid = rs.getInt(5);
					String marktime = rs.getString(6);
					boolean isLocked = rs.getBoolean(7);
					String subTime = rs.getString(8);
					String comment = rs.getString(9);

					Exam exam = examMapper.findById(examid);
					User student = userMapper.findById(studentid);
					User marker = userMapper.findById(markerid);
					List<Answer> answers = answerMapper.findAnswersBySubmissionId(submissionId);

					submission = new Submission(id, exam, student, totalmark, comment, marker, marktime, subTime,
							isLocked, answers);
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
	 * find a submission record in the submission table
	 * 
	 * @param submissionId = target submission id
	 * @return exam with the id.
	 */
	public List<Submission> findByStudentId(int studentId) {
		// find the exam in the identity map.
		Submission submission = new Submission();

		ExamMapper examMapper = new ExamMapper();
		UserMapper userMapper = new UserMapper();

		// find from the DB when it is not in the identity map.
		List<Submission> result = new ArrayList<Submission>();
		// query a exam by examId
		String findSubmissionbyIdStm = "SELECT * FROM submissions WHERE studentid = ?";

		try {
			PreparedStatement stmt = DatabaseConnection.prepare(findSubmissionbyIdStm);
			stmt.setInt(1, studentId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Integer id = rs.getInt(1);
				Integer examid = rs.getInt(2);
				Integer studentid = rs.getInt(3);
				Float totalmark = rs.getFloat(4);
				Integer markerid = rs.getInt(5);
				String marktime = rs.getString(6);
				boolean isLocked = rs.getBoolean(7);
				String subTime = rs.getString(8);
				String comment = rs.getString(9);

				// Exam exam = examMapper.findById(examid);

				// exam.setId(examId);
				// User student = userMapper.findById(studentid);
				// User marker = userMapper.findById(markerid);

				submission = new Submission(id, null, null, totalmark, comment, null, marktime, subTime, isLocked,
						null);
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
			IdentityMap<Submission> submissionMap = IdentityMap.getInstance(submission);
			submission = submissionMap.get(submission.getId());
			submissionMap.put(result.get(0).getId(), result.get(0));
			
		}

		return result;
	}

	/**
	 * find all submissions record in the submission table
	 * 
	 *
	 * @return all the submissions records.
	 */
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
				Integer examid = rs.getInt(2);
				Integer studentid = rs.getInt(3);
				Float totalmark = rs.getFloat(4);
				Integer markerid = rs.getInt(5);
				String marktime = rs.getString(6);
				boolean isLocked = rs.getBoolean(7);
				String subTime = rs.getString(8);
				String comment = rs.getString(9);

				Exam exam = examMapper.findById(examid);

				// exam.setId(examId);
				User student = userMapper.findById(studentid);
				User marker = userMapper.findById(markerid);

				submission = new Submission(id, exam, student, totalmark, comment, marker, marktime, subTime, isLocked,
						null);
				result.add(submission);
			}

			if (result.size() > 0) {
				for (int i = 0; i < result.size(); i++) {
					Submission s = submissionMap.get(result.get(i).getId());
					if (s == null) {
						submissionMap.put(result.get(i).getId(), result.get(i));
					}
					System.out.println(result.get(i).getId() + "," + result.get(i).getStudent().getUserName() + ","
							+ result.get(i).getTotalMark() + "," + result.get(i).getExam().getTitle() + ","
							+ result.get(i).getExam().getSubject().getSubjectCode());
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

	public Submission FindSubmissionsByUserId_ExamId(int userId, int ExamId) {
		List<Submission> result = new ArrayList<Submission>();
		Submission submission = new Submission();
		String querySubmissionStm = "SELECT * FROM submissions WHERE studentid = ? AND examid = ?"; // query all
																									// subjects
		IdentityMap<Submission> submissionMap = IdentityMap.getInstance(submission);
		UserMapper userMapper = new UserMapper();
		ExamMapper examMapper = new ExamMapper();
		AnswerMapper answerMapper = new AnswerMapper();

		try {
			PreparedStatement stmt = DatabaseConnection.prepare(querySubmissionStm);
			stmt.setInt(1, userId);
			stmt.setInt(2, ExamId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Integer id = rs.getInt(1);
				Integer examid = rs.getInt(2);
				Integer studentid = rs.getInt(3);
				Float totalmark = rs.getFloat(4);
				Integer markerid = rs.getInt(5);
				String marktime = rs.getString(6);
				boolean isLocked = rs.getBoolean(7);
				String subTime = rs.getString(8);
				String comment = rs.getString(9);

				Exam exam = examMapper.findById(examid);
				List<Answer> answers = null;//answerMapper.findAnswersBySubmissionId(id);

				User student = userMapper.findById(studentid);
				User marker1 = userMapper.findById(markerid);
				User marker = new User();
				if (marker1 != null)
					marker.setUserName(marker1.getUserName());

				submission = new Submission(id, exam, student, totalmark, comment, marker, marktime, subTime, isLocked,
						answers);
				result.add(submission);
			}

			if (result.size() > 0) {
				for (int i = 0; i < result.size(); i++) {
					Submission s = submissionMap.get(result.get(i).getId());
					if (s == null) {
						submissionMap.put(result.get(i).getId(), result.get(i));
					}
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
		return submission;
	}

	// test
	public static void main(String args[]) {
		Submission s = new Submission();
		Exam e = new Exam();
		User u = new User();
		u.setId(7);
		e.setId(5);
		s.setExam(e);
		s.setStudent(u);
		SubmissionMapper sm = new SubmissionMapper();
		sm.insert(s);
	}

	public Submission FindSubmissionsByExamId(int examId) {
		List<Submission> result = new ArrayList<Submission>();
		Submission submission = new Submission();
		String querySubmissionStm = "SELECT * FROM submissions WHERE examid = ?"; // query all subjects
		IdentityMap<Submission> submissionMap = IdentityMap.getInstance(submission);
		UserMapper userMapper = new UserMapper();
		ExamMapper examMapper = new ExamMapper();

		try {
			PreparedStatement stmt = DatabaseConnection.prepare(querySubmissionStm);
			stmt.setInt(1, examId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Integer id = rs.getInt(1);
				Integer examid = rs.getInt(2);
				Integer studentid = rs.getInt(3);
				Float totalmark = rs.getFloat(4);
				Integer markerid = rs.getInt(5);
				String marktime = rs.getString(6);
				boolean isLocked = rs.getBoolean(7);
				String subTime = rs.getString(8);
				String comment = rs.getString(9);

				Exam exam = examMapper.findById(examid);

				// exam.setId(examId);
				User student = userMapper.findById(studentid);
				User marker = userMapper.findById(markerid);

				submission = new Submission(id, exam, student, totalmark, comment, marker, marktime, subTime, isLocked,
						null);
				result.add(submission);
			}

			if (result.size() > 0) {
				for (int i = 0; i < result.size(); i++) {
					Submission s = submissionMap.get(result.get(i).getId());
					if (s == null) {
						submissionMap.put(result.get(i).getId(), result.get(i));
					}
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
		return submission;
	}

}
