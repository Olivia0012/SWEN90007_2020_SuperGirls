package serviceImp;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import domain.Exam;
import domain.Relationship;
import domain.Subject;
import domain.User;
import enumeration.Role;
import mapper.SubjectMapper;
import mapper.UserMapper;
import service.SubjectService;
import shared.UnitOfWork;
import util.JsonToObject;

public class SubjectServiceImp implements SubjectService {
	SubjectMapper subjectMapper = new SubjectMapper();
	UserMapper userMapper = new UserMapper();
	private JsonToObject jo = new JsonToObject();
	private UnitOfWork current;

	public SubjectServiceImp(UnitOfWork current) {
		this.current = current;
	}

	@Override
	public String findAllSubjectsByUserId(int userId, Role role) {
		List<Subject> subjectList = new ArrayList<Subject>();

		subjectList = subjectMapper.FindAllSubjectByUserId(userId, role);

		/*
		 * for(Subject s: subjectList) { s.getSubjectCode(); s.getTitle(); }
		 */

		String result = JSONObject.toJSONString(subjectList);
		System.out.println(result);
		return result;
	}

	/**
	 * Admin find all subjects
	 */
	@Override
	public String findAllSubjects() {
		List<Subject> subjectList = new ArrayList<Subject>();
		subjectList = subjectMapper.FindAllSubject();
		String result = JSONObject.toJSONString(subjectList);
		return result;
	}

	@Override
	public String findAllUsersBySubjectId(int subjectId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean usersInSubject(int subjectId, List<User> users, String role) {
	//	UnitOfWork.newCurrent();

		List<User> curUsers = userMapper.FindEnrolledUsers(Role.valueOf(role), subjectId);

		if (users.size() == 0) {
			for (int i = 0; i < curUsers.size(); i++) {
				Relationship relationship = new Relationship();
				relationship.setSubjectId(subjectId);
				relationship.setUser(curUsers.get(i));
				current.registerDeleted(relationship);
			}
		}

		else if (curUsers.size() == 0) {
			for (int i = 0; i < users.size(); i++) {
				Relationship relationship = new Relationship();
				relationship.setSubjectId(subjectId);
				relationship.setUser(users.get(i));
				current.registerNew(relationship);
			}
		}

		else {
			for (int i = 0; i < curUsers.size(); i++) {
				for (int j = 0; j < users.size(); j++) {
					if (curUsers.get(i).getId() == users.get(j).getId()) {
						curUsers.get(i).setUserName(null);
						users.get(j).setUserName(null);
						continue;
					}
				}
			}

			for (int i = 0; i < curUsers.size(); i++) {
				if (curUsers.get(i).getUserName() != null) {
					Relationship relationship = new Relationship();
					relationship.setSubjectId(subjectId);
					relationship.setUser(curUsers.get(i));
					current.registerDeleted(relationship);
				}
			}

			for (int i = 0; i < users.size(); i++) {
				if (users.get(i).getUserName() != null) {
					Relationship relationship = new Relationship();
					relationship.setSubjectId(subjectId);
					relationship.setUser(users.get(i));
					current.registerNew(relationship);
				}
			}
		}
		return current.commit();
	}

	@Override
	public boolean addSubject(HttpServletRequest request) {
	//	UnitOfWork.newCurrent();
		// get the new exam info from the request.
		JSONObject subjectJsonObject = jo.ReqJsonToObject(request);

		Subject subject = new Subject();
		subject = JSON.toJavaObject(subjectJsonObject, Subject.class);

		current.registerNew(subject);
		
		return current.commit();

	}

}
