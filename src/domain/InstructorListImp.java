package domain;

import java.util.List;

import enumeration.Role;
import mapper.UserMapper;

public class InstructorListImp implements InstructorList{
	UserMapper userMapper;

	@Override
	public List<User> getInstructorList(int subjectId) {
		// TODO Auto-generated method stub
		return findInstructors(subjectId);
	}
	
	private List<User> findInstructors(int subjectId){
		userMapper.FindAllUsers(Role.INSTRUCTOR, subjectId);
		return null;
		
	}

	public boolean setInstructorList(List<User> instructors) {
		// TODO Auto-generated method stub
		return false;
	}
}
