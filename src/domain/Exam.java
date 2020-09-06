package domain;

import java.util.ArrayList;
import java.util.Date;

import enumeration.ExamStatus;

public class Exam extends DomainObject{
	private Subject subject;
	private User creator;
	private Date createdTime;
	private Date updatedTime;
	private String title;
	private ExamStatus status;
	private boolean isLocked;
	private ArrayList<Question> questionList;
	
	public Exam() {
		super();
	}

	public Exam(int Id, Subject subject, User creator,Date createdTime,Date updatedTime,String title, ExamStatus status,boolean isLocked) {
		super(Id);
		this.setSubject(subject);
		this.setCreator(creator);
		this.setCreatedTime(createdTime);
		this.setUpdatedTime(updatedTime);
		this.setTitle(title);
		this.setStatus(status);
		this.setLocked(isLocked);
	}


	/**
	 * @return the subject
	 */
	public Subject getSubject() {
		return subject;
	}


	/**
	 * @param subject the subject to set
	 */
	public void setSubject(Subject subject) {
		this.subject = subject;
	}


	/**
	 * @return the creator
	 */
	public User getCreator() {
		return creator;
	}


	/**
	 * @param creator the creator to set
	 */
	public void setCreator(User creator) {
		this.creator = creator;
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
	 * @return the questionList
	 */
	public ArrayList<Question> getQuestionList() {
		return questionList;
	}

	/**
	 * @param questionList the questionList to set
	 */
	public void setQuestionList(ArrayList<Question> questionList) {
		this.questionList = questionList;
	}


	

}
