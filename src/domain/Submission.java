package domain;

import java.util.List;

import mapper.AnswerMapper;
import mapper.QuestionMapper;

public class Submission extends DomainObject{
	private User student;
	private Exam exam;
	private float totalMark;
	private String comment;
	private User marker;
	private String markTime;
	private String subTime;
	private boolean isLock;
	private List<Answer> answers;
	
	public Submission(Integer id, Exam exam, User student, float totalMark, String comment, User marker,
			String markTime, String subTime, boolean isLocked, List<Answer> answers) {
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
	public String getMarkTime() {
		return markTime;
	}
	/**
	 * @param markTime the markTime to set
	 */
	public void setMarkTime(String markTime) {
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
		if(answers == null) {
			load();
		}
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
	public String getSubTime() {
		return subTime;
	}
	/**
	 * @param subTime the subTime to set
	 */
	public void setSubTime(String subTime) {
		this.subTime = subTime;
	}
	

	void load() {
		try {
			AnswerMapper answerMapper = new AnswerMapper();
			List<Answer> answerList = answerMapper.findAnswersBySubmissionId(Id);
			answers = answerList;
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	
}
