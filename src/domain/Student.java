package domain;

import java.util.List;

import enumeration.Role;

public class Student extends User{
	private List<Submission> submission;
	private Answer answer;

	public Student(int uId, String userName, String passWord) {
		super(uId, passWord, passWord, Role.STUDENT);
		
	}



}
