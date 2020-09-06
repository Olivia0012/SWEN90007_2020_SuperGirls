package domain;

import java.util.ArrayList;

import enumeration.Role;

public class Student extends User{
	private ArrayList<Submission> submission;
	private ArrayList<Exam> Exams;
	

	public Student(int uId, String userName, String passWord) {
		super(uId, passWord, passWord, Role.STUDENT);
		
	}



}
