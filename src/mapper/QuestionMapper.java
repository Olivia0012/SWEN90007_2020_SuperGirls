/**
 * @author Lu Wang
 */
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
import domain.Question;
import domain.User;
import enumeration.QuestionType;
import enumeration.Role;
import shared.IdentityMap;

/**
 * This class is the question mapper.
 *
 */
public class QuestionMapper extends DataMapper {

	/**
	 * Insert a new question record into users table
	 * 
	 * @param obj = user to be inserted
	 * @return indication whether the insert is successful or not
	 */
	@Override
	public int insert(DomainObject obj) {
		Question question = (Question) obj;
		String addNewQuestionStm = "INSERT INTO QUESTION (questionnum,questionType,questiondes,questionmark,examid, choice1, choice2, choice3, choice4) "
				+ "VALUES (?,?,?,?,?,?,?,?,?)";// insert new subject 1
		// into subject table
		try {
			PreparedStatement stmt = DatabaseConnection.prepareInsert(addNewQuestionStm);
			stmt.setInt(1, question.getQuestionNum());
			stmt.setString(2, question.getQuestionType() + "");
			stmt.setString(3, question.getQuestionDescription());
			stmt.setDouble(4, question.getQuestionMark());
			stmt.setInt(5, question.getExamId());
			stmt.setString(6, question.getChoices().get(0));
			stmt.setString(7, question.getChoices().get(1));
			stmt.setString(8, question.getChoices().get(2));
			stmt.setString(9, question.getChoices().get(3));

			stmt.executeUpdate();
			ResultSet keys = stmt.getGeneratedKeys();
			keys.next();
			int id = keys.getInt(1);
			question.setId(id);
			IdentityMap<Question> questionMap = IdentityMap.getInstance(question);
			questionMap.put(question.getId(), question);

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

	/**
	 * Update a question record in the question table
	 * 
	 * @param obj = question to be updated
	 * @return indication whether the update is successful or not
	 */
	@Override
	public Boolean update(DomainObject obj) {
		Question question = (Question) obj;

		String updateQuestionStm = "UPDATE question SET questionnum = ?,questionType = ?,questiondes = ?,questionmark = ?,examid = ?, choice1 = ?, choice2 = ?, choice3 = ?, choice4 = ? WHERE questionid = ?";

		try {
			PreparedStatement stmt = DatabaseConnection.prepare(updateQuestionStm);
			stmt.setInt(1, question.getQuestionNum());
			stmt.setString(2, question.getQuestionType() + "");
			stmt.setString(3, question.getQuestionDescription());
			stmt.setDouble(4, question.getQuestionMark());
			stmt.setInt(5, question.getExamId());
			stmt.setString(6, question.getChoices().get(0));
			stmt.setString(7, question.getChoices().get(1));
			stmt.setString(8, question.getChoices().get(2));
			stmt.setString(9, question.getChoices().get(3));
			stmt.setDouble(10, question.getId());

			stmt.executeUpdate();

			IdentityMap<Question> questionMap = IdentityMap.getInstance(question);

			// add the updated question into question identity map if it is not there.
			questionMap.put(question.getId(), question);

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
	 * Delete a question record in the user table
	 * 
	 * @param obj = questionquestion to be deleted
	 * @return indication whether the delete is successful or not
	 */
	@Override
	public Boolean delete(DomainObject obj) {
		Question question = (Question) obj;

		String deleteUserStm = "DELETE FROM question WHERE questionid = ?";

		try {
			PreparedStatement stmt = DatabaseConnection.prepare(deleteUserStm);
			stmt.setInt(1, question.getId());
			stmt.executeUpdate();

			IdentityMap<Question> questionMap = IdentityMap.getInstance(question);
			Question questionInMap = questionMap.get(question.getId());
			if (questionInMap != null) {
				questionMap.put(question.getId(), null);
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
	 * find a user record in the user table
	 * 
	 * @param userId = target user id
	 * @return user with the id.
	 */
	@Override
	public Question findById(int questionid) {
		// find the subject in the identity map.
		Question question = new Question();

		IdentityMap<Question> questionMap = IdentityMap.getInstance(question);
		question = questionMap.get(questionid);

		// find from the DB when it is not in the identity map.
		if (question == null) {
			List<Question> result = new ArrayList<Question>();
			// query a subject by subjectId
			String findQuestionbyIdStm = "SELECT * FROM question WHERE questionid = ?";

			try {
				PreparedStatement stmt = DatabaseConnection.prepare(findQuestionbyIdStm);
				stmt.setInt(1, questionid);
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					Integer id = rs.getInt(1);
					Integer examId = rs.getInt(2);
					int questionnum = rs.getInt(3);
					String questiondes = rs.getString(4);
					Double questionmark = (double) rs.getFloat(5);
					String choice1 = rs.getString(6);
					String choice2 = rs.getString(7);
					String choice3 = rs.getString(8);
					String choice4 = rs.getString(9);
					String questionType = rs.getString(10);

					List<String> choices = new ArrayList<String>();
					choices.add(choice1);
					choices.add(choice2);
					choices.add(choice3);
					choices.add(choice4);

					question = new Question(id, questionnum, QuestionType.valueOf(questionType), questiondes,
							questionmark, choices, examId);
					result.add(question);
				}
				rs.close();
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DatabaseConnection.closeConnection();
			}
			if (result.size() > 0) {
				questionMap.put(result.get(0).getId(), result.get(0));
				return result.get(0);
			}
		}
		return question;
	}

	/**
	 * find all subjects record in the subject table
	 * 
	 *
	 * @return all the subject records.
	 */
	public List<Question> FindAllQuestion() {
		String queryAllQuestion = "select * from question"; // query all users

		Question question = new Question();
		// query all questions

		IdentityMap<Question> questionMap = IdentityMap.getInstance(question);
		List<Question> result = new ArrayList<Question>();

		try {
			PreparedStatement stmt = DatabaseConnection.prepare(queryAllQuestion);

			ResultSet rs = stmt.executeQuery();
			// Subject subject = new Subject();

			while (rs.next()) {
				Integer id = rs.getInt(1);
				Integer examId = rs.getInt(2);
				int questionnum = rs.getInt(3);
				String questiondes = rs.getString(4);
				Double questionmark = (double) rs.getFloat(5);
				String choice1 = rs.getString(6);
				String choice2 = rs.getString(7);
				String choice3 = rs.getString(8);
				String choice4 = rs.getString(9);
				String questionType = rs.getString(10);

				List<String> choices = new ArrayList<String>();
				choices.add(choice1);
				choices.add(choice2);
				choices.add(choice3);
				choices.add(choice4);

				question = new Question(id, questionnum, QuestionType.valueOf(questionType), questiondes, questionmark,
						choices, examId);
				result.add(question);
			}

			if (result.size() > 0) {
				for (int i = 0; i < result.size(); i++) {
					Question s = questionMap.get(result.get(i).getId());
					if (s == null) {
						questionMap.put(result.get(i).getId(), result.get(i));
					}
					System.out.println(result.get(i).getId() + "," + result.get(i).getQuestionNum() + ","
							+ result.get(i).getQuestionType() + "," + result.get(i).getQuestionDescription());
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

	public List<Question> findQuestionByExamId(Integer examId) {
		String queryAllQuestion = "select * from question where examid=?"; // query all users

		Question question = new Question();
		// query all questions

		IdentityMap<Question> questionMap = IdentityMap.getInstance(question);
		List<Question> result = new ArrayList<Question>();

		try {
			PreparedStatement stmt = DatabaseConnection.prepare(queryAllQuestion);
			stmt.setInt(1, examId);
			ResultSet rs = stmt.executeQuery();
			// Subject subject = new Subject();

			while (rs.next()) {
				Integer id = rs.getInt(1);
				Integer examid = rs.getInt(2);
				int questionnum = rs.getInt(3);
				String questiondes = rs.getString(4);
				Double questionmark = (double) rs.getFloat(5);
				String choice1 = rs.getString(6);
				String choice2 = rs.getString(7);
				String choice3 = rs.getString(8);
				String choice4 = rs.getString(9);
				String questionType = rs.getString(10);

				List<String> choices = new ArrayList<String>();
				choices.add(choice1);
				choices.add(choice2);
				choices.add(choice3);
				choices.add(choice4);

				question = new Question(id, questionnum, QuestionType.valueOf(questionType), questiondes, questionmark,
						choices, examId);
				result.add(question);
			}

			if (result.size() > 0) {
				for (int i = 0; i < result.size(); i++) {
					Question s = questionMap.get(result.get(i).getId());
					if (s == null) {
						questionMap.put(result.get(i).getId(), result.get(i));
					}
					System.out.println(result.get(i).getId() + "," + result.get(i).getQuestionNum() + ","
							+ result.get(i).getQuestionType() + "," + result.get(i).getQuestionDescription());
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

	public static void main(String args[]) {
		QuestionMapper sm = new QuestionMapper();
		Question question = sm.findById(5);
		// Question question = new Question(-1,1, QuestionType.ANSWER, "Insert a new
		// question", 1.3, null,1);
		// sm.insert(question);

		// (Question) sm.findById(1);
		// newUser.setUserName("Olivia");
		// newUser.setPassWord("123");
		// newUser.setRole(role);
		// sm.insert(newUser);
//		sm.FindAllQuestion();
		List<Question> questions = sm.findQuestionByExamId(1);
		String result = JSONObject.toJSONString(question);
		System.out.println(result);

	}

}
