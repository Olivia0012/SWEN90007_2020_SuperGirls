package domain;

import java.util.ArrayList;
import java.util.List;

import enumeration.Role;

public class Student extends User{
	private List<Submission> submissionList = new ArrayList<Submission>();
	private List<Exam> examList = new ArrayList<Exam>();
	

	public Student(User user,List<Exam> subjectList,List<Submission> submissionList) {
		super(user.getId(), user.getUserName(), user.getPassWord(), Role.STUDENT);
		this.setExamList(subjectList);
		this.setSubmissionList(submissionList);
	}


	public Student() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * @return the submissionList
	 */
	public List<Submission> getSubmissionList() {
		return submissionList;
	}


	/**
	 * @param submissionList the submissionList to set
	 */
	public void setSubmissionList(List<Submission> submissionList) {
		this.submissionList = submissionList;
	}


	/**
	 * @return the subjectList
	 */
	public List<Exam> getExamList() {
		return examList;
	}


	/**
	 * @param examList 
	 */
	public void setExamList(List<Exam> examList) {
		this.examList = examList;
	}



}
