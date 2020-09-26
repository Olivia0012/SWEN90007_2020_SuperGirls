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

	@Override
	public String findAllStudentsAndSubmissions(int subjectId, int examId) {
		
		
		studentList = studentMapper.findAllStudentsAndSubmissions(subjectId, examId);
		
		String result = JSONObject.toJSONString(studentList);
		System.out.println(result);
		return result;
	}

}
