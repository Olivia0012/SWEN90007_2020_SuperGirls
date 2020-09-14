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
import com.alibaba.fastjson.JSONObject;

public class ViewExamImp implements ViewExam {

	public ViewExamImp() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Exam findExamById(int examId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findAllExams() {
		List<Exam> examList = new ArrayList<Exam>();
		ExamMapper examMapper = new ExamMapper();
		Exam e = examMapper.findById(1);
	//	examJson.add(examList);
		User u = new User(1,"1","1",Role.ADMIN);
		Question a = new Question(1, 1, null, "11", 1.4, null);
		Subject s = new Subject(1,"S","s");
	//	Exam e = new Exam(1,a,s);
			
		
		String result = JSONObject.toJSONString(e);
		String result1 = JSONObject.toJSONString(s);
		System.out.println(result);
		System.out.println(result1);
		return result;
	}
	
	public static void main(String[] args) {
		ViewExamImp a = new ViewExamImp();
		a.findAllExams();
	}

}
