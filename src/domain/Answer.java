/**
 * 
 */
package domain;

import java.util.ArrayList;

/**
 * @author manlo
 *
 */
public class Answer extends DomainObject{
	private Submission submission;
	private Double mark;
	private String anwer;
	private Question question;

	/**
	 * 
	 */
	public Answer() {
		super();
	}

	/**
	 * @return the submission
	 */
	public Submission getSubmission() {
		return submission;
	}

	/**
	 * @param submission the submission to set
	 */
	public void setSubmission(Submission submission) {
		this.submission = submission;
	}

	/**
	 * @return the mark
	 */
	public Double getMark() {
		return mark;
	}

	/**
	 * @param mark the mark to set
	 */
	public void setMark(Double mark) {
		this.mark = mark;
	}

	/**
	 * @return the anwer
	 */
	public String getAnwer() {
		return anwer;
	}

	/**
	 * @param anwer the anwer to set
	 */
	public void setAnwer(String anwer) {
		this.anwer = anwer;
	}

	/**
	 * @return the question
	 */
	public Question getQuestion() {
		return question;
	}

	/**
	 * @param question the question to set
	 */
	public void setQuestion(Question question) {
		this.question = question;
	}

}
