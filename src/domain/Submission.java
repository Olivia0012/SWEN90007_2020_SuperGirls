package domain;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class Submission extends DomainObject{
	private User student;
	private Exam exam;
	private float totalMark;
	private String comment;
	private User marker;
	private Date markTime;
	private Date subTime;
	private boolean isLock;
	private List<Answer> answers;
	
	public Submission(Integer id, Exam exam, User student, float totalmark, String comment, User marker,
			Date marktime, Date subTime, boolean isLocked) {
		super(id);
		this.setExam(exam);
		this.setStudent(student);
		this.setTotalMark(totalMark);
		this.setComment(comment);
		this.setMarker(marker);
		this.setMarkTime(markTime);
		this.setSubTime(subTime);
		this.setAnswers(answers);
		
	}
	public Submission() {
		super();
	}
	/**
	 * @return the student
	 */
	public User getStudent() {
		return student;
	}
	/**
	 * @param student the student to set
	 */
	public void setStudent(User student) {
		this.student = student;
	}
	/**
	 * @return the exam
	 */
	public Exam getExam() {
		return exam;
	}
	/**
	 * @param exam the exam to set
	 */
	public void setExam(Exam exam) {
		this.exam = exam;
	}
	/**
	 * @return the totalMark
	 */
	public float getTotalMark() {
		return totalMark;
	}
	/**
	 * @param totalMark the totalMark to set
	 */
	public void setTotalMark(float totalMark) {
		this.totalMark = totalMark;
	}
	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}
	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	/**
	 * @return the marker
	 */
	public User getMarker() {
		return marker;
	}
	/**
	 * @param marker the marker to set
	 */
	public void setMarker(User marker) {
		this.marker = marker;
	}
	/**
	 * @return the markTime
	 */
	public Date getMarkTime() {
		return markTime;
	}
	/**
	 * @param markTime the markTime to set
	 */
	public void setMarkTime(Date markTime) {
		this.markTime = markTime;
	}

	/**
	 * @return the isLock
	 */
	public boolean isLock() {
		return isLock;
	}
	/**
	 * @param isLock the isLock to set
	 */
	public void setLock(boolean isLock) {
		this.isLock = isLock;
	}
	/**
	 * @return the answers
	 */
	public List<Answer> getAnswers() {
		return answers;
	}
	/**
	 * @param answers the answers to set
	 */
	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	/**
	 * @return the subTime
	 */
	public Date getSubTime() {
		return subTime;
	}
	/**
	 * @param subTime the subTime to set
	 */
	public void setSubTime(Date subTime) {
		this.subTime = subTime;
	}

}
