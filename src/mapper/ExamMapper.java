/**
 *  @author Lu Wang
 *
 * */
package mapper;

import domain.DomainObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import database.DatabaseConnection;
import domain.Exam;
import domain.Question;
import domain.Subject;
import domain.User;
import enumeration.ExamStatus;
import shared.IdentityMap;
import shared.UnitOfWorkImp;

/**
 * This class is the data mapper for exam.
 */
public class ExamMapper extends DataMapper {

	/**
	 * Insert a new subject record into subject table
	 * 
	 * @param obj = subject to be inserted
	 * @return indication whether the insert is successful or not
	 */
	@Override
	public Boolean insert(DomainObject obj) {
		Exam exam = (Exam) obj;
		LocalDateTime createTime = LocalDateTime.now();

		String addNewExamStm = "INSERT INTO EXAM (subjectid,creatorid,createdTime,updatedTime,title,status,islock) "
				+ "VALUES (?,?,?,?,?,?,?)";

		try {
			PreparedStatement stmt = DatabaseConnection.prepare(addNewExamStm);
			stmt.setInt(1, exam.getSubject().getId());
			stmt.setInt(2, exam.getCreator().getId());
			stmt.setString(3, createTime + "");
			stmt.setString(4, "");
			stmt.setString(5, exam.getTitle());
			stmt.setString(6, exam.getStatus() + "");
			stmt.setBoolean(7, exam.isLocked());

			stmt.executeUpdate();
			ResultSet keys = stmt.getGeneratedKeys();
			keys.next();
			int id = keys.getInt(1);

			exam.setId(id);

			IdentityMap<Exam> examMap = IdentityMap.getInstance(exam);
			examMap.put(exam.getId(), exam);
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
	 * Update an exam record in the subject table
	 * 
	 * @param obj = subject to be updated
	 * @return indication whether the update is successful or not
	 */
	@Override
	public Boolean update(DomainObject obj) {
		UnitOfWorkImp.newCurrent();
		Exam exam = (Exam) obj;
		LocalDateTime updateTime = LocalDateTime.now();

		String updateSubjectStm = "UPDATE exam SET subjectid = ?,updatedTime = ?,title = ?,status = ?,isLocked = ? WHERE examid = ?";

		try {
			PreparedStatement stmt = DatabaseConnection.prepare(updateSubjectStm);
			stmt.setInt(1, exam.getSubject().getId());
			stmt.setString(2, updateTime + "");
			stmt.setString(3, exam.getTitle());
			stmt.setString(4, exam.getStatus() + "");
			stmt.setBoolean(5, exam.isLocked());
			stmt.executeUpdate();

			IdentityMap<Exam> examMap = IdentityMap.getInstance(exam);
			Exam examInMap = examMap.get(exam.getId());

			// add the updated subject into subject identity map if it is not there.
			if (examInMap == null) {
				examMap.put(exam.getId(), exam);
			}
			UnitOfWorkImp.getCurrent().commit();
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
		UnitOfWorkImp.newCurrent();
		Exam exam = (Exam) obj;

		String deleteSubjectStm = "DELETE FROM exam WHERE examid = ?";

		try {
			PreparedStatement stmt = DatabaseConnection.prepare(deleteSubjectStm);
			stmt.setInt(1, exam.getId());
			stmt.executeUpdate();

			IdentityMap<Exam> examMap = IdentityMap.getInstance(exam);
			Exam examInMap = examMap.get(exam.getId());
			if (examInMap != null) {
				examMap.put(exam.getId(), null);
			}

			UnitOfWorkImp.getCurrent().commit();
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
	 * find a exam record in the exam table
	 * 
	 * @param examId = target exam id
	 * @return exam with the id.
	 */
	@Override
	public Exam findById(int examId) {
		// find the exam in the identity map.
		Exam exam = new Exam();

		IdentityMap<Exam> examMap = IdentityMap.getInstance(exam);
		exam = examMap.get(examId);
		SubjectMapper subjectMapper = new SubjectMapper();
		QuestionMapper questisonMapper = new QuestionMapper();
		UserMapper instructorMapper = new UserMapper();

		// find from the DB when it is not in the identity map.
		if (exam == null) {
			List<Exam> result = new ArrayList<Exam>();
			// query a exam by examId
			String findExambyIdStm = "SELECT * FROM exam WHERE examid = ?";

			try {
				PreparedStatement stmt = DatabaseConnection.prepare(findExambyIdStm);
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
					
					List<Question> questionList =  questisonMapper.findQuestionByExamId(id);
					Subject subject = subjectMapper.findById(subjectId);
					User instrctor = instructorMapper.findById(instructorId);
					exam = new Exam(id, subject, instrctor, createTime, updateTime, title, ExamStatus.valueOf(status),
							isLocked,questionList);
					result.add(exam);
				}
				
				if(result.size() > 0) {
					for (int i = 0; i < result.size(); i++) {
						Exam s = examMap.get(result.get(i).getId());
						if (s == null) {
							examMap.put(result.get(i).getId(), result.get(i));
						}
						if(result.get(i).getQuestionList() != null)
						System.out.println(result.get(i).getId() + "," + result.get(i).getTitle() + ","
								+ result.get(i).getStatus() + "," + result.get(i).getSubject().getSubjectCode()+ ",");
								//		+ result.get(i).getQuestionList().get(0).getQuestionDescription());
						else {
							System.out.println(result.get(i).getId() + "," + result.get(i).getTitle() + ","
									+ result.get(i).getStatus() + "," + result.get(i).getSubject().getSubjectCode());
											
						}
					}
				}
				
				rs.close();
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DatabaseConnection.closeConnection();
			}
			if (result.size() > 0) {
				examMap.put(result.get(0).getId(), result.get(0));
				return result.get(0);
			}
		}

		return exam;

	}

	
	/**
	 * find all subjects record in the subject table
	 * 
	 *
	 * @return all the subject records.
	 * */
	public List<Exam> FindAllExams() {
		Exam exam = new Exam();
		String queryAllExamStm = "SELECT * FROM exam"; // query all subjects
		IdentityMap<Exam> examMap = IdentityMap.getInstance(exam);
		List<Exam> result = new ArrayList<Exam>();
		QuestionMapper questisonMapper = new QuestionMapper();
		SubjectMapper subjectMapper = new SubjectMapper();
		UserMapper instructorMapper = new UserMapper();
		
		try {
			PreparedStatement stmt = DatabaseConnection.prepare(queryAllExamStm);

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

				List<Question> questionList =  questisonMapper.findQuestionByExamId(id);
				Subject subject = subjectMapper.findById(subjectId);
				User instrctor = instructorMapper.findById(instructorId);
				exam = new Exam(id, subject, instrctor, createTime, updateTime, title, ExamStatus.valueOf(status),
						isLocked,questionList);
				result.add(exam);
			}

			if(result.size() > 0) {
				for (int i = 0; i < result.size(); i++) {
					Exam s = examMap.get(result.get(i).getId());
					if (s == null) {
						examMap.put(result.get(i).getId(), result.get(i));
					}
					System.out.println(result.get(i).getId() + "," + result.get(i).getTitle() + ","
							+ result.get(i).getStatus() + "," + result.get(i).getSubject().getSubjectCode()+ ","
									+ result.get(i).getQuestionList());
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
	 * find all exams by subject Id
	 * 
	 *
	 * @return all the exams records.
	 * */
	public List<Exam> FindAllExamsBySubjectId(int subjectid) {
		Exam exam = new Exam();

		
		
		String queryAllExamBySubjetIdStm = "SELECT * FROM exam WHERE subjectid=?"; // query all exams by subjectId
		IdentityMap<Exam> examMap = IdentityMap.getInstance(exam);
		List<Exam> result = new ArrayList<Exam>();
		QuestionMapper questisonMapper = new QuestionMapper();
		SubjectMapper subjectMapper = new SubjectMapper();
		UserMapper instructorMapper = new UserMapper();
		
		try {
			PreparedStatement stmt = DatabaseConnection.prepare(queryAllExamBySubjetIdStm);
			stmt.setInt(1, subjectid);
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

			//	List<Question> questionList =  questisonMapper.findQuestionByExamId(id);
				Subject subject = subjectMapper.findById(subjectId);
				User instrctor = instructorMapper.findById(instructorId);
				exam = new Exam(id, subject, instrctor, createTime, updateTime, title, ExamStatus.valueOf(status),
						isLocked,null);
				result.add(exam);
			}

			if(result.size() > 0) {
				for (int i = 0; i < result.size(); i++) {
					Exam s = examMap.get(result.get(i).getId());
					if (s == null) {
						examMap.put(result.get(i).getId(), result.get(i));
					}
				/*	System.out.println(result.get(i).getId() + "," + result.get(i).getTitle() + ","
							+ result.get(i).getStatus() + "," + result.get(i).getSubject().getSubjectCode()+ ","
									+ result.get(i).getQuestionList());
				*/}
				
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
		ExamMapper em = new ExamMapper();
	//	em.FindAllExams();
		em.findById(1);
	}

}
