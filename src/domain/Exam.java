package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import enumeration.ExamStatus;
import shared.UnitOfWorkImp;

public class Exam extends DomainObject{
	private Subject subject;
	private User creator;
	private Date createdTime;
	private Date updatedTime;
	private String title;
	private ExamStatus status;
	private boolean isLocked;
	private List<Question> questionList = new ArrayList<Question>();
	
	public Exam() {
		super();
		UnitOfWorkImp.newCurrent();
		UnitOfWorkImp.getCurrent().registerNew(this);
	}


	public Exam(int Id, Subject subject, User creator,Date createdTime,Date updatedTime,String title, ExamStatus status,boolean isLocked, List<Question> questionList) {
		super(Id);
		this.setSubject(subject);
		this.setCreator(creator);
		this.setCreatedTime(createdTime);
		this.setUpdatedTime(updatedTime);
		this.setTitle(title);
		this.setStatus(status);
		this.setLocked(isLocked);
		this.setQuestionList(questionList);
		UnitOfWorkImp.getCurrent().registerNew(this);
	}

	public Exam(Integer id, String title) {
		super(id);
		this.setTitle(title);
		UnitOfWorkImp.getCurrent().registerNew(this);
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
		UnitOfWorkImp.getCurrent().registerDirty(this);
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
		UnitOfWorkImp.getCurrent().registerDirty(this);
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
		UnitOfWorkImp.getCurrent().registerDirty(this);
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
		UnitOfWorkImp.getCurrent().registerDirty(this);
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
		UnitOfWorkImp.getCurrent().registerDirty(this);
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
		UnitOfWorkImp.getCurrent().registerDirty(this);
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
		UnitOfWorkImp.getCurrent().registerDirty(this);
	}

	/**
	 * @return the questionList
	 */
	public List<Question> getQuestionList() {
		return this.questionList; 
	}

	/**
	 * @param questionList the questionList to set
	 */
	public void setQuestionList(List<Question> questionList) {
		this.questionList = questionList;
		UnitOfWorkImp.getCurrent().registerDirty(this);
	}



}
