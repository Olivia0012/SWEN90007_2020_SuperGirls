/**
 * 
 */
package domain;


/**
 * @author manlo
 *
 */
public class Answer extends DomainObject{
	private float mark;
	private String answer;
	private int questionId;
	private int submissionId;

	/**
	 * @param mark 
	 * @param sm 
	 * @param q 
	 * @param student 
	 * @param id 
	 * @param sanswer 
	 * 
	 */
	public Answer(Integer id,String answer, int q, float mark, int submissionId) {
		super(id);
		this.setAnswer(answer);
		this.setMark(mark);
		this.setQuestionId(q);
		this.setSubmissionId(submissionId);
	}
	public Answer() {
		// TODO Auto-generated constructor stub
	}



	/**
	 * @return the mark
	 */
	public float getMark() {
		return mark;
	}

	/**
	 * @param mark the mark to set
	 */
	public void setMark(float mark) {
		this.mark = mark;
	}
	/**
	 * @return the answer
	 */
	public String getAnswer() {
		return answer;
	}
	/**
	 * @param answer the answer to set
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
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
	/**
	 * @return the questionId
	 */
	public int getQuestionId() {
		return questionId;
	}
	/**
	 * @param questionId the questionId to set
	 */
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

}
