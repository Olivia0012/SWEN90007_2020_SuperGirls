package serviceImp;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

import domain.Exam;
import domain.Student;
import domain.Subject;
import domain.Submission;
import domain.User;
import mapper.ExamMapper;
import mapper.SubjectMapper;
import mapper.SubmissionMapper;
import mapper.UserMapper;
import service.StudentService;

public class StudentServiceImp implements StudentService {
	private List<Student> studentList = new ArrayList<Student>();
	private UserMapper studentMapper = new UserMapper();
	private ExamMapper examMapper = new ExamMapper();
	private SubjectMapper subjectMapper = new SubjectMapper();
	

	public StudentServiceImp() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String findAllStudentsBySubjectId(int subjectId) {
		List<Student> studentList = new ArrayList<Student>();
		UserMapper studentMapper = new UserMapper();
		
		studentList = studentMapper.FindAllStudentsBySubjectId(subjectId);
		
		String result = JSONObject.toJSONString(studentList);
		System.out.println(result);
		return result;
	}

	@Override
	public String findAllStudentsByExamId(int examId) {
		List<Student> studentList = new ArrayList<Student>();
		UserMapper studentMapper = new UserMapper();
		
	//	studentList = studentMapper.findAllStudentsByExamId(examId);
		
		String result = JSONObject.toJSONString(studentList);
		System.out.println(result);
		return result;
	}

	/**
	 * @param subject id; exam id
	 * 
	 * @return all enrolled in the subject students with submission details.
	 */
	@Override
	public String findAllStudentsAndSubmissions(int subjectId, int examId) {
		
    	Subject subject = subjectMapper.findById(subjectId);
    	if(subject == null) {
    		return "false";
    	}
    	
    	Exam exam = examMapper.findById(examId);
    	if(exam == null) {
    		return "false";
    	}
    	// find all students and their submissions
    	String resultSubject = JSONObject.toJSONString(subject);
    	String resultExam = JSONObject.toJSONString(exam);
		studentList = studentMapper.findAllStudentsAndSubmissions(subjectId, examId);
		String resultSubmissions = JSONObject.toJSONString(studentList);
		
		String result = "{\"subject\":"+resultSubject+",\"submissions\":"+resultSubmissions+",\"exam\":"+resultExam+",\"examState\":\""+exam.getStatus()+"\"}";
		return result;
	}

}
