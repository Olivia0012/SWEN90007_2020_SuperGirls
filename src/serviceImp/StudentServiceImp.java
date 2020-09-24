package service;

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

public class StudentServiceImp implements StudentService {

	public StudentServiceImp() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String findAllStudentsBySubjectId(int subjectId) {
		List<Student> studentList = new ArrayList<Student>();
		List<User> userList = new ArrayList<User>();
		List<Exam> examList = new ArrayList<Exam>();
		UserMapper studentMapper = new UserMapper();
		List<Submission> submissionList = new ArrayList<Submission>();
		SubmissionMapper submissionMapper = new SubmissionMapper();
		ExamMapper examMapper = new ExamMapper();
		
		userList = studentMapper.FindAllStudentsBySubjectId(subjectId);
		examList = examMapper.FindAllExamsBySubjectId(subjectId);
		submissionList = submissionMapper.FindAllSubmission();
		
		for(int i = 0; i < userList.size(); i++) {
			
			Student student = new Student(userList.get(i),examList,submissionList);
			studentList.add(student);
			
		}
		String result = JSONObject.toJSONString(studentList);
		System.out.println(result);
		return result;
	}

}
