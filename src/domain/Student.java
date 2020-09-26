package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import enumeration.Role;

public class Student extends User{
	
	private List<Submission> submissions = new ArrayList<Submission>();
	

	public Student(User user,List<Submission> submissions) {
		super(user.getId(), user.getUserName(), user.getPassWord(), Role.STUDENT);
		this.setSubmissions(submissions);
	}


	public Student() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * @return the submissions
	 */
	public List<Submission> getSubmissions() {
		return submissions;
	}


	/**
	 * @param submissions the submissions to set
	 */
	public void setSubmissions(List<Submission> submissions) {
		this.submissions = submissions;
	}


	




}
