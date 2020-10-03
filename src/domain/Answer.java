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
	private Question question;
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
	public Answer(Integer id,String answer, Question q, float mark, int submissionId) {
		super(id);
		this.setAnswer(answer);
		this.setMark(mark);
		this.setQuestion(q);
		this.setSubmissionId(submissionId);
	}
	public Answer() {
		// TODO Auto-generated constructor stub
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

}
