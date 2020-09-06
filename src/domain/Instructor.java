package domain;

import java.util.ArrayList;

import enumeration.Role;

public class Instructor {
	private ArrayList<Exam> Exams;

	public Instructor(int uId, String userName, String passWord, String role) {
	//	super(uId, userName, passWord, Role.INSTRUCTOR);
	//	this.setSubjectId(subjectId);
	//	this.setExamId(examId);
	//	this.setSubmissionId(submissionId);
	}

	public Instructor() {
		// TODO Auto-generated constructor stub
		super();
	}

	/**
	 * @return the exams
	 */
	public ArrayList<Exam> getExams() {
		return Exams;
	}

	/**
	 * @param exams the exams to set
	 */
	public void setExams(ArrayList<Exam> exams) {
		Exams = exams;
	}

}
