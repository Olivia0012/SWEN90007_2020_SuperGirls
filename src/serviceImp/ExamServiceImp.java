package service;

import java.util.ArrayList;
import java.util.List;

import domain.Exam;
import domain.Question;
import domain.Subject;
import domain.User;
import enumeration.QuestionType;
import enumeration.Role;
import mapper.ExamMapper;
import mapper.QuestionMapper;
import mapper.SubjectMapper;
import mapper.UserMapper;
import shared.IdentityMap;

import com.alibaba.fastjson.JSONObject;

public class ExamServiceImp implements ExamService {
	
	private ExamMapper examMapper = new ExamMapper();
	private SubjectMapper subjectMapper = new SubjectMapper();
	private UserMapper userMapper = new UserMapper();

	public ExamServiceImp() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String findExamById(int examId) {
		Exam exam = new Exam();
		ExamMapper examMapper = new ExamMapper();
		Exam e = examMapper.findById(examId);
		String result = JSONObject.toJSONString(e);
		return result;
	}

	@Override
	public String findAllExams() {
		List<Exam> examList = new ArrayList<Exam>();
		ExamMapper examMapper = new ExamMapper();
		Exam e = examMapper.findById(1);
	//	examJson.add(examList);
		User u = new User(1,"1","1",Role.ADMIN);
	//	Question a = new Question(1, 1, null, "11", 1.4, null);
		Subject s = new Subject(1,"S","s");
	//	Exam e = new Exam(1,a,s);
			
		
		String result = JSONObject.toJSONString(e);
		String result1 = JSONObject.toJSONString(s);
		System.out.println(result);
		System.out.println(result1);
		return result;
	}
	
	public static void main(String[] args) {
		ExamServiceImp a = new ExamServiceImp();
		a.findAllExams();
	}

	@Override
	public String updateExam(Exam exam) {
		ExamMapper examMapper = new ExamMapper();
		examMapper.update(exam);
		for(int i = 0; i < exam.getQuestionList().size(); i ++) {
			QuestionMapper question = new QuestionMapper();
			if(exam.getQuestionList().get(i).getId() == -1) {
				question.insert(exam.getQuestionList().get(i));
			}
		}
		IdentityMap<Exam> examMap = IdentityMap.getInstance(exam);
		examMap.put(exam.getId(), null);
		Exam newExam = examMapper.findById(exam.getId());
		examMap.put(exam.getId(), newExam);
		String result = JSONObject.toJSONString(newExam);
		System.out.println(result);
		return result;
	}

	@Override
	public String deleteQuestionById(int questionId) {
		QuestionMapper questionMapper = new QuestionMapper();
		Question q = new Question();
		q.setId(questionId);
		
		// find the question whose id equals questionId in the IdentityMap
		IdentityMap<Question> questionMap = IdentityMap.getInstance(q);
		Question deleteQ = questionMap.get(questionId);
		
		// delete the old exam in the IdentityMap
		int examId = deleteQ.getExamId();
		ExamMapper examMapper = new ExamMapper();
		Exam exam = examMapper.findById(examId);
		IdentityMap<Exam> examMap = IdentityMap.getInstance(exam);
		examMap.put(examId, null);
		
		// delete the target question
		questionMapper.delete(q);
		
		// add new exam into the IndentityMap
		Exam newExam = examMapper.findById(deleteQ.getExamId());
		examMap.put(exam.getId(), newExam);
		
		String result = JSONObject.toJSONString(newExam);
		System.out.println(result);
		return "OK";
	}

	//Adding new exam (only title) for a subject
	@Override
	public String addNewExam(Exam exam) {
		ExamMapper examMapper = new ExamMapper();
		examMapper.insert(exam);
		return "OK";
	}

	@Override
	public String deleteExamById(int examId) {
		ExamMapper examMapper = new ExamMapper();
		Exam e = new Exam();
		e.setId(examId);
		examMapper.delete(e);
		return "OK";
	}

	//Adding new exam (only title) for a subject with the creator
	@Override
	public boolean addNewExamCheckingUser(Exam exam, User creator) {
		// checking whether this exam is already existed.
		User user = userMapper.findById(creator.getId());
		Subject subject = subjectMapper.findById(exam.getSubject().getId());
		
		if(user == null) {
			return false;
		}
		
		if(user.getRole() != Role.INSTRUCTOR) {
			return false;
		}
		
		if(subject == null) {
			return false;
		}
		
		if(exam.getId() != -1) {
			
		}
		
		// TODO Auto-generated method stub
		return true;
	}

}
