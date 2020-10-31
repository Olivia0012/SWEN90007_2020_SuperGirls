/**
 *  @author Lu Wang
 *
 * */
package mapper;

import domain.DomainObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

import database.DatabaseConnection;
import domain.Exam;
import domain.Question;
import domain.Subject;
import domain.User;
import enumeration.ExamStatus;
import enumeration.Role;

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
	public int insert(DomainObject obj) {
		Exam exam = (Exam) obj;
		Date createTime = new Date();

		String addNewExamStm = "INSERT INTO EXAM (subjectid,instructorid,createtime,examtitle,examstatus,islock) "
				+ "VALUES (?,?,?,?,?,?)";

		try {
			PreparedStatement stmt = DatabaseConnection.prepareInsert(addNewExamStm);
			stmt.setInt(1, exam.getSubject().getId());
			stmt.setInt(2, exam.getCreator().getId());
			stmt.setString(3, createTime + "");
			stmt.setString(4, exam.getTitle());
			stmt.setString(5, exam.getStatus() + "");
			stmt.setBoolean(6, exam.isLocked());

			stmt.executeUpdate();
			
			ResultSet keys = stmt.getGeneratedKeys();
			keys.next();
			int id = keys.getInt(1);
			exam.setId(id);

			// add the new exam into the exam identity map.
		//	IdentityMap1<Exam> examMap = IdentityMap1.getInstance(exam);
		//	examMap.put(exam.getId(), exam);

			keys.close();
			stmt.close();
			return id;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		} finally {
		//	DatabaseConnection.closeConnection();
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
		Exam exam = (Exam) obj;

		String updateSubjectStm = "UPDATE exam SET subjectid = ?,examtitle = ?,islock = ?,examstatus = ? WHERE examid = ?";

		try {
			PreparedStatement stmt = DatabaseConnection.prepare(updateSubjectStm);
			stmt.setInt(1, exam.getSubject().getId());
			// stmt.setString(2, updateTime + "");
			stmt.setString(2, exam.getTitle());
			// stmt.setString(3, exam.getStatus() + "");
			stmt.setBoolean(3, exam.isLocked());
			stmt.setString(4, exam.getStatus() + "");
			stmt.setInt(5, exam.getId());
			stmt.executeUpdate();

		//	IdentityMap1<Exam> examMap = IdentityMap1.getInstance(exam);

			// add the updated subject into subject identity map if it is not there.
	/*		Exam examInMap = examMap.get(exam.getId());
			if (examInMap != null) {
				examMap.put(exam.getId(), null);
			}
			examMap.put(exam.getId(), exam);*/
			
			stmt.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
		//	DatabaseConnection.closeConnection();
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
		Exam exam = (Exam) obj;

		String deleteSubjectStm = "DELETE FROM exam WHERE examid = ?";

		try {
			PreparedStatement stmt = DatabaseConnection.prepare(deleteSubjectStm);
			stmt.setInt(1, exam.getId());
			stmt.executeUpdate();

	/*		IdentityMap1<Exam> examMap = IdentityMap1.getInstance(exam);
			Exam examInMap = examMap.get(exam.getId());
			if (examInMap != null) {
				examMap.put(exam.getId(), null);
			}*/

			stmt.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
		//	DatabaseConnection.closeConnection();
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

	/*	IdentityMap1<Exam> examMap = IdentityMap1.getInstance(exam);
		exam = examMap.get(examId);*/

		

		SubjectMapper subjectMapper = new SubjectMapper();
		QuestionMapper questisonMapper = new QuestionMapper();
		UserMapper instructorMapper = new UserMapper();

		// find from the DB when it is not in the identity map.
//		if (exam == null) {
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
					String title = rs.getString(4);
					String createTime = rs.getString(5);
					String status = rs.getString(6);
					boolean isLocked = rs.getBoolean(7);

					List<Question> questionList = questisonMapper.findQuestionByExamId(id);
					Subject subject = subjectMapper.findById(subjectId);
					User instrctor = instructorMapper.findById(instructorId);
					
					exam = new Exam(id, subject, instrctor, createTime, null, title, ExamStatus.valueOf(status),
							isLocked, questionList);
					result.add(exam);
				}
				String r1 = JSONObject.toJSONString(result);
				System.out.println("findById exam: " + r1);

		/*		if (result.size() > 0) {
					for (int i = 0; i < result.size(); i++) {
						Exam s = examMap.get(result.get(i).getId());
						if (s == null) {
							examMap.put(result.get(i).getId(), result.get(i));
						}

					}
					String result1 = JSONObject.toJSONString(result);
					System.out.println("examMap result : " + result1);
				}*/

				rs.close();
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}/* finally {
				DatabaseConnection.closeConnection();
			}
			if (result.size() > 0) {
				examMap.put(result.get(0).getId(), result.get(0));
				return result.get(0);
			}*/
	//	}

		return exam;

	}

	/**
	 * find all subjects record in the subject table
	 * 
	 *
	 * @return all the subject records.
	 */
	public List<Exam> FindAllExams() {
		Exam exam = new Exam();
		String queryAllExamStm = "SELECT * FROM exam"; // query all subjects
	//	IdentityMap1<Exam> examMap = IdentityMap1.getInstance(exam);
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
				String createTime = rs.getString("createTime");
				// Date updateTime = rs.getDate("updateTime");
				String title = rs.getString("examTitle");
				String status = rs.getString("examStatus");
				boolean isLocked = rs.getBoolean("isLock");

				List<Question> questionList = questisonMapper.findQuestionByExamId(id);
				
				Subject subject = subjectMapper.findById(subjectId);
				User instrctor = instructorMapper.findById(instructorId);
				exam = new Exam(id, subject, instrctor, createTime, null, title, ExamStatus.valueOf(status), isLocked,
						questionList);
				result.add(exam);
			}

		/*	if (result.size() > 0) {
				for (int i = 0; i < result.size(); i++) {
					Exam s = examMap.get(result.get(i).getId());
					if (s == null) {
						examMap.put(result.get(i).getId(), result.get(i));
					}
					System.out.println(result.get(i).getId() + "," + result.get(i).getTitle() + ","
							+ result.get(i).getStatus() + "," + result.get(i).getSubject().getSubjectCode() + ","
							+ result.get(i).getQuestionList());
				}

			}*/
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
		//	DatabaseConnection.closeConnection();
		}
		return result;

	}

	/**
	 * find all exams by subject Id
	 * 
	 *
	 * @return all the exams records.
	 */
	public List<Exam> FindAllExamsBySubjectId(int subjectid, Role role) {
		Exam exam = new Exam();

		String queryAllExamBySubjetIdStm = "SELECT * FROM exam WHERE subjectid=?"; // query all exams by subjectId
	//	IdentityMap1<Exam> examMap = IdentityMap1.getInstance(exam);
		List<Exam> result = new ArrayList<Exam>();
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
				String createTime = rs.getString("createTime");
				String title = rs.getString("examTitle");
				String status = rs.getString("examStatus");
				boolean isLocked = rs.getBoolean("isLock");

				User instrctor = new User();

				User instructor = instructorMapper.findById(instructorId);
				User creator = new User();
				creator.setId(instructor.getId());
				creator.setUserName(instructor.getUserName());
				
				Subject subject = subjectMapper.findById(subjectId);

				if(role.equals(Role.STUDENT)) {
					if(status.equals("PUBLISHED") || status.equals("RELEASED")) {
						exam = new Exam(id, subject, instrctor, createTime, null, title, ExamStatus.valueOf(status), isLocked,
								null);
						result.add(exam);
					}
				}else {
					exam = new Exam(id, subject, creator, createTime, null, title, ExamStatus.valueOf(status), isLocked,
							null);
					result.add(exam);
				}
				

			}

	
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
		//	DatabaseConnection.closeConnection();
		}
		return result;

	}

	

}
