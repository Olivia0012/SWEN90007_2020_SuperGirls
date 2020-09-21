package service;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

import domain.Subject;
import mapper.SubjectMapper;

public class SubjectServiceImp implements SubjectService {

	public SubjectServiceImp() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String findAllSubjectsByUserId(int userId) {
		List<Subject> subjectList = new ArrayList<Subject>();
		SubjectMapper subjectMapper = new SubjectMapper();
		subjectList = subjectMapper.FindAllSubjectByUserId(userId);
			
		
		String result = JSONObject.toJSONString(subjectList);
		System.out.println(result);
		return result;
	}

}
