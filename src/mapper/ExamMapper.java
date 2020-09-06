/**
 * 
 */
package mapper;

import domain.DomainObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import database.DatabaseConnection;
import domain.Exam;
import domain.Instructor;
import domain.Subject;
import domain.User;
import enumeration.ExamStatus;
import shared.IdentityMap;

/**
 * @author manlo
 *
 */
public class ExamMapper extends DataMapper {
	private String findExambyIdStm = "select * from exam where examid = ?";

	/**
	 * 
	 */
	public ExamMapper() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Boolean insert(DomainObject obj) {
		Exam newExam = (Exam) obj;
		String statement = "INSERT INTO Exam (subjectId,instructorId,examTitle,createTime,updateTime,examStatus,isLock) VALUES (?,?,?,?,?,?,?)";

		try {

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			DatabaseConnection.closeConnection();
		}

		return true;
	}

	@Override
	protected Boolean update(DomainObject obj) {
		Exam newExam = (Exam) obj;
		String statement = "INSERT INTO Exam (subjectId,instructorId,examTitle,createTime,updateTime,examStatus,isLock) VALUES (?,?,?,?,?,?,?)";

		try {

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			DatabaseConnection.closeConnection();
		}

		return true;
	}

	@Override
	protected Boolean delete(DomainObject obj) {
		Exam newExam = (Exam) obj;
		String statement = "INSERT INTO Exam (subjectId,instructorId,examTitle,createTime,updateTime,examStatus,isLock) VALUES (?,?,?,?,?,?,?)";

		try {

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			DatabaseConnection.closeConnection();
		}

		return true;
	}

	@Override
	protected Exam findById(int examId) {
		// find the exam in the identity map.
		Exam exam = new Exam();
		IdentityMap<Exam> examMap = IdentityMap.getInstance(exam);
		exam = examMap.get(examId);

		// find from the DB when it is not in the identity map.
		if (exam == null) {
			List<Exam> result = new ArrayList<Exam>();
			SubjectMapper subjectMapper = new SubjectMapper();
			UserMapper instructorMapper = new UserMapper();

			try {
				PreparedStatement stmt = DatabaseConnection.prepareInsert(findExambyIdStm);
				stmt.setInt(1, examId);
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					Integer id = rs.getInt(1);
					Integer subjectId = rs.getInt(2);
					Integer instructorId = rs.getInt(3);
					Date createTime = rs.getDate(4);
					Date updateTime = rs.getDate(5);
					String title = rs.getString(6);
					String status = rs.getString(7);
					boolean isLocked = rs.getBoolean(8);

					Subject subject = subjectMapper.findById(subjectId);
					User instrctor = instructorMapper.findById(instructorId);
					exam = new Exam(id, subject, instrctor, createTime, updateTime, title, ExamStatus.valueOf(status),
							isLocked);
					result.add(exam);
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
		return exam;

	}

	protected void FindAllExams() {
		Exam exam = new Exam();
		IdentityMap<Exam> examMap = IdentityMap.getInstance(exam);
		// exam = examMap.get(examId);

		// find from the DB when it is not in the identity map.
		// if (exam == null) {
		String statement = "SELECT * FROM exam";
		List<Exam> result = new ArrayList<Exam>();
		SubjectMapper subjectMapper = new SubjectMapper();
		InstructorMapper instructorMapper = new InstructorMapper();

		try {
			PreparedStatement stmt = DatabaseConnection.prepare(statement);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Integer id = rs.getInt("examId");
				Integer subjectId = rs.getInt("subjectId");
				Integer instructorId = rs.getInt("instructorId");
				Date createTime = rs.getDate("createTime");
				Date updateTime = rs.getDate("updateTime");
				String title = rs.getString("examTitle");
				String status = rs.getString("examStatus");
				boolean isLocked = rs.getBoolean("isLock");

				Subject subject = subjectMapper.findById(subjectId);
				User instrctor = instructorMapper.findById(instructorId);
				exam = new Exam(id, subject, instrctor, createTime, updateTime, title, ExamStatus.valueOf(status),
						isLocked);
				result.add(exam);
			}
			rs.close();
			// stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.closeConnection();
		}
		if (result.size() > 0) {

			for (int i = 0; i < result.size(); i++) {
				System.out.println(
						result.get(i).getId() + "," + result.get(i).getTitle() + "," + result.get(i).getStatus()+ "," + result.get(i).getSubject().getSubjectCode());
			}
		}
	}

	// test
	public static void main(String args[]) {
		ExamMapper em = new ExamMapper();
		em.FindAllExams();
		em.findById(1);
	}

}
