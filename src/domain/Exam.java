package domain;

import java.util.ArrayList;
import java.util.Date;

import enumeration.ExamStatus;

public class Exam extends DomainObject{
	private int subjectId;
	private int creatorId;
	private int updatorId;
	private Date createdTime;
	private Date updatedTime;
	private String title;
	private ExamStatus status;
	private boolean isLocked;
	private ArrayList<User> userList;
	private ArrayList<Submission> submissionList;
	

	public Exam(int Id, int subjectId, int creatorId,Date createdTime, int updatorId,Date updatedTime,String title, ExamStatus status,boolean isLocked) {
		super(Id);
		this.setSubjectId(subjectId);
		this.setCreatorId(creatorId);
		this.setUpdatorId(updatorId);
		this.setCreatedTime(createdTime);
		this.setUpdatedTime(updatedTime);
		this.setTitle(title);
		this.setStatus(status);
		this.setLocked(isLocked);
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
	 * @return the createdTime
	 */
	public Date getCreatedTime() {
		return createdTime;
	}


	/**
	 * @param createdTime the createdTime to set
	 */
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}


	/**
	 * @return the updatedTime
	 */
	public Date getUpdatedTime() {
		return updatedTime;
	}


	/**
	 * @param updatedTime the updatedTime to set
	 */
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}


	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}


	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}


	/**
	 * @return the status
	 */
	public ExamStatus getStatus() {
		return status;
	}


	/**
	 * @param status the status to set
	 */
	public void setStatus(ExamStatus status) {
		this.status = status;
	}


	/**
	 * @return the isLocked
	 */
	public boolean isLocked() {
		return isLocked;
	}


	/**
	 * @param isLocked the isLocked to set
	 */
	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}


	/**
	 * @return the creatorId
	 */
	public int getCreatorId() {
		return creatorId;
	}


	/**
	 * @param creatorId the creatorId to set
	 */
	public void setCreatorId(int creatorId) {
		this.creatorId = creatorId;
	}


	/**
	 * @return the updatorId
	 */
	public int getUpdatorId() {
		return updatorId;
	}


	/**
	 * @param updatorId the updatorId to set
	 */
	public void setUpdatorId(int updatorId) {
		this.updatorId = updatorId;
	}


	/**
	 * @return the userList
	 */
	public ArrayList<User> getUserList() {
		return userList;
	}


	/**
	 * @param userList the userList to set
	 */
	public void setUserList(ArrayList<User> userList) {
		this.userList = userList;
	}


	/**
	 * @return the submissionList
	 */
	public ArrayList<Submission> getSubmissionList() {
		return submissionList;
	}


	/**
	 * @param submissionList the submissionList to set
	 */
	public void setSubmissionList(ArrayList<Submission> submissionList) {
		this.submissionList = submissionList;
	}

}
