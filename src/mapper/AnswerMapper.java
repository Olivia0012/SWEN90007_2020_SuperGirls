/**
 * 
 */
package mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

import database.DatabaseConnection;
import domain.Answer;
import domain.DomainObject;
import domain.Exam;
import domain.Question;
import domain.Submission;
import domain.User;
import shared.IdentityMap;
import shared.UnitOfWork;

/**
 * @author manlo
 *
 */
public class AnswerMapper extends DataMapper{

	/**
	 * 
	 */
	public AnswerMapper() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AnswerMapper em = new AnswerMapper();
		// em.FindAllSubmission();
		em.findById(1);
		String result = JSONObject.toJSONString(em.findAnswersBySubmissionId(1));
		System.out.println(result);
		// ;

	}

	@Override
	public int insert(DomainObject obj) {
		Answer answer = (Answer) obj;

		String addNewStm = "INSERT INTO ANSWER (questionid,answer,mark,submissionid) "
				+ "VALUES (?,?,?,?)";

		try {
			PreparedStatement stmt = DatabaseConnection.prepareInsert(addNewStm);
			stmt.setInt(1, answer.getQuestion().getId());
			stmt.setString(2, answer.getAnswer());
			stmt.setFloat(3, answer.getMark());
			stmt.setInt(4, answer.getSubmissionId());

			stmt.executeUpdate();
			ResultSet keys = stmt.getGeneratedKeys();
			keys.next();
			int id = keys.getInt(1);

			answer.setId(id);

			IdentityMap<Answer> answerMap = IdentityMap.getInstance(answer);
			answerMap.put(answer.getId(), answer);
			System.out.println(id);

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
		Answer answer = (Answer) obj;

		String updateAnswerStm = "UPDATE answer SET answer = ?,mark = ? WHERE answerid = ?";

		try {
			PreparedStatement stmt = DatabaseConnection.prepare(updateAnswerStm);
			stmt.setString(1, answer.getAnswer());
			stmt.setFloat(2, answer.getMark());
			stmt.setInt(3, answer.getId());

			stmt.executeUpdate();

			IdentityMap<Answer> answerMap = IdentityMap.getInstance(answer);
			Answer answerInMap = answerMap.get(answer.getId());

			// add the updated submission into submission identity map if it is not there.
			if (answerInMap == null) {
				answerMap.put(answer.getId(), null);
			}

			answerMap.put(answer.getId(), answer);

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
	public Boolean delete(DomainObject obj) {
		UnitOfWork.newCurrent();
		Submission submission = (Submission) obj;
		AnswerMapper answerMapper = new AnswerMapper();
		List<Answer> answers = answerMapper.findAnswersBySubmissionId(submission.getId());

		String deleteAnswersStm = "DELETE FROM answer WHERE submissionid = ?";

		try {
			PreparedStatement stmt = DatabaseConnection.prepare(deleteAnswersStm);
			stmt.setInt(1, submission.getId());
			stmt.executeUpdate();

			for(Answer an: answers) {
				IdentityMap<Answer> answerMap = IdentityMap.getInstance(an);
				Answer examInMap = answerMap.get(an.getId());
				if (examInMap != null) {
					answerMap.put(an.getId(), null);
				}
			}
		
			UnitOfWork.getCurrent().commit();
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
	
	public List<Answer> findAnswersBySubmissionId(int submissionId) {
		List<Answer> result = new ArrayList<Answer>();
		Answer answer = new Answer();
		String queryAnswerStm = "SELECT * FROM answer WHERE submissionid = ?"; // query all subjects
		IdentityMap<Answer> answerMap = IdentityMap.getInstance(answer);
		QuestionMapper questionMapper = new QuestionMapper();
		ExamMapper examMapper = new ExamMapper();
		
		try {
			PreparedStatement stmt = DatabaseConnection.prepare(queryAnswerStm);
			stmt.setInt(1, submissionId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Integer id = rs.getInt(1);
				Integer studentid = rs.getInt(2);
				Integer examid = rs.getInt(3);
				Integer questionid = rs.getInt(4);
				String sanswer = rs.getString(5);
				Float mark = rs.getFloat(6);
				Integer submissionid = rs.getInt(7);
				
				Question q = questionMapper.findById(questionid);
				
				answer = new Answer(id,sanswer, q,mark,submissionid) ;
				result.add(answer);
			}

			if(result.size() > 0) {
				for (int i = 0; i < result.size(); i++) {
					Answer s = answerMap.get(result.get(i).getId());
					if (s == null) {
						answerMap.put(result.get(i).getId(), result.get(i));
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
		return result;
		
	}


	
	

}
