package domain;

import enumeration.Role;

public class Instructor extends User{
	private int subjectId;
	private int examId;
	private int submissionId;

	public Instructor(int uId, String userName, String passWord, Role role) {
		super(uId, userName, passWord, Role.INSTRUCTOR);
		this.setSubjectId(subjectId);
		this.setExamId(examId);
		this.setSubmissionId(submissionId);
	}

	/**
	 * @return the subjectId
	 */
	public int getSubjectId() {
		return subjectId;
	}

	/**
	 * @param subjectId the subjectId to set
	 */
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	/**
	 * @return the examId
	 */
	public int getExamId() {
		return examId;
	}

	/**
	 * @param examId the examId to set
	 */
	public void setExamId(int examId) {
		this.examId = examId;
	}

	/**
	 * @return the submissionId
	 */
	public int getSubmissionId() {
		return submissionId;
	}

	/**
	 * @param submissionId the submissionId to set
	 */
	public void setSubmissionId(int submissionId) {
		this.submissionId = submissionId;
	}

}
