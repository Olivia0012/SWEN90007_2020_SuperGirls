package domain;

import java.util.ArrayList;
import java.util.List;


public class Instructor extends User{
	private List<Exam> exams = new ArrayList<Exam>();
	private List<Subject> subjects = new ArrayList<Subject>();

	public Instructor(User user, List<Exam> exams, List<Subject> subjects) {
		super(user.getId(), user.getUserName(), user.getPassWord(), user.getRole());
		this.setSubjects(subjects);
		this.setExams(exams);
	}

	public Instructor() {
		// TODO Auto-generated constructor stub
		super();
	}

	/**
	 * @return the exams
	 */
	public List<Exam> getExams() {
		return exams;
	}

	/**
	 * @param exams the exams to set
	 */
	public void setExams(List<Exam> exams) {
		this.exams = exams;
	}

	/**
	 * @return the subjects
	 */
	public List<Subject> getSubjects() {
		return subjects;
	}

	/**
	 * @param subjects the subjects to set
	 */
	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

	

}
