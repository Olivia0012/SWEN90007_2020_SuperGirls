package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

import database.QueryExecutor;
import domain.DomainObject;
import domain.Exam;
import domain.Question;
import domain.Subject;
import domain.User;
import enumeration.ExamStatus;
import enumeration.Role;

public class ExamMappeNewDBC extends DataMapper{
	private QueryExecutor executor;

    public ExamMappeNewDBC() {
        executor = QueryExecutor.getInstance();
    }

	@Override
	public int insert(DomainObject obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Boolean update(DomainObject obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(DomainObject obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DomainObject findById(int id) {
        return null;
	}
	
	public ResultSet findByIdTesting(int id) {
		try {
            String sql = "SELECT * FROM exam WHERE examid = ?";
            return executor.getResultSet(sql, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
	}
	
	private Exam createEntity(ResultSet rs) {
		QuestionMapper questisonMapper = new QuestionMapper();
		SubjectMapper subjectMapper = new SubjectMapper();
		UserMapper instructorMapper = new UserMapper();
        try {
        	Integer id = rs.getInt("examId");
			Integer subjectId = rs.getInt("subjectId");
			Integer instructorId = rs.getInt("instructorId");
			String createTime = rs.getString("createTime");
			// Date updateTime = rs.getDate("updatetime");
			String title = rs.getString("examTitle");
			String status = rs.getString("examStatus");
			boolean isLocked = rs.getBoolean("isLock");
			List<Question> questionList = questisonMapper.findQuestionByExamId(id);
			Subject subject = subjectMapper.findById(subjectId);
			User instrctor = instructorMapper.findById(instructorId);
			Exam exam = new Exam(id, subject, instrctor, createTime, null, title, ExamStatus.valueOf(status), isLocked,
					questionList);
			return exam;
           
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
	
	// test
		public static void main(String args[]) {
			ExamMappeNewDBC em = new ExamMappeNewDBC();
			Subject s = new Subject(4, "", "");
			User u = new User(4, "", "", Role.INSTRUCTOR);
			// em.FindAllExams();
			/*
			 * Exam e = new Exam(-1, s, u, "2020-09-21:01:22:30", null, "test exam1",
			 * ExamStatus.CREATED, false,null);
			 */
			ResultSet rs = em.findByIdTesting(1);
			List<Exam> exams = new ArrayList<Exam>();
            try {
				while (rs.next()) {
					exams.add(em.createEntity(rs));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// em.insert(e);
			 String result = JSONObject.toJSONString(exams);
			 System.out.println(result);
		}

}
