package serviceImp;

import java.util.ArrayList;
import java.util.List;

import domain.Answer;
import domain.Exam;
import domain.Question;
import domain.Subject;
import domain.Submission;
import domain.User;
import enumeration.QuestionType;
import enumeration.Role;
import mapper.AnswerMapper;
import mapper.ExamMapper;
import mapper.QuestionMapper;
import mapper.SubjectMapper;
import mapper.SubmissionMapper;
import mapper.UserMapper;
import service.ExamService;
import shared.IdentityMap;

import com.alibaba.fastjson.JSONObject;

public class ExamServiceImp implements ExamService {
	
	private ExamMapper examMapper = new ExamMapper();
	private SubjectMapper subjectMapper = new SubjectMapper();
	private UserMapper userMapper = new UserMapper();
	private SubmissionMapper sm = new SubmissionMapper();
	private AnswerMapper am = new AnswerMapper();

	public ExamServiceImp() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String findExamById(int examId) {
		ExamMapper examMapper = new ExamMapper();
		Exam e = examMapper.findById(examId);
		String result = JSONObject.toJSONString(e);
		System.out.println("findExamById: "+result);
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
		examMapper.update(exam);
		for(int i = 0; i < exam.getQuestionList().size(); i ++) {
			QuestionMapper question = new QuestionMapper();
			if(exam.getQuestionList().get(i).getId() == -1) {
				question.insert(exam.getQuestionList().get(i));
			}else {
				question.update(exam.getQuestionList().get(i));
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
		examMapper.insert(exam);
		return "OK";
	}

	@Override
	public String deleteExamById(int examId) {
		Exam e = new Exam();
		e.setId(examId);
		examMapper.delete(e);
		return "OK";
	}

	
	
	@Override
	public boolean markSubmission(Submission submission) {
		sm.update(submission);
		AnswerMapper am = new AnswerMapper();
		for(Answer an: submission.getAnswers()) {
			am.update(an);
		}
		return true;
	}
	
	@Override
	public String findSubmissionById(int submissionId) {
		
		Submission s = sm.findById(submissionId);
		List<Answer> answers = am.findAnswersBySubmissionId(submissionId);
		s.setAnswers(answers);
		String result = JSONObject.toJSONString(s);
		return result;
	}

	@Override
	public String publishExam(Exam exam) {
		ExamMapper examMapper = new ExamMapper();
		examMapper.update(exam);
		String result = JSONObject.toJSONString(examMapper.findById(exam.getId()));
		return result;
	}

	@Override
	public int addSubmission(Submission submission) {
		int subId = sm.insert(submission);
		
		for(Answer an: submission.getAnswers()) {
			an.setSubmissionId(subId);
			am.insert(an);
		}
		
		return subId;
		
	}

	@Override
	public boolean takeExam(Submission submission) {
		sm.update(submission);
		AnswerMapper am = new AnswerMapper();
		for(Answer an: submission.getAnswers()) {
			am.update(an);
		}
		return true;
	}

}
