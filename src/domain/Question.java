/**
 * 
 */
package domain;

import java.util.ArrayList;
import java.util.List;

import enumeration.QuestionType;

/**
 * @author manlo
 *
 */
public class Question extends DomainObject{
	private int questionNum;
	private QuestionType qType;
	private String qDescription;
	private Double qMark;
	private Exam exam;
	private ArrayList<String> choices;

	/**
	 * 
	 */
	public Question() {
		super();
	}

	public Question(int id, int questionnum, QuestionType questionType, String questiondes, Double questionmark, Exam exam, List<String> choice) {
		super(id);
		this.setQuestionNum(questionNum);
		this.setqType(questionType);
		this.setqDescription(questiondes);
		this.setExam(exam);
		this.setqMark(questionmark);
		this.setChoices(choices);
		
	}

	/**
	 * @return the questionNum
	 */
	public int getQuestionNum() {
		return questionNum;
	}

	/**
	 * @param questionNum the questionNum to set
	 */
	public void setQuestionNum(int questionNum) {
		this.questionNum = questionNum;
	}

	/**
	 * @return the qType
	 */
	public QuestionType getqType() {
		return qType;
	}

	/**
	 * @param qType the qType to set
	 */
	public void setqType(QuestionType qType) {
		this.qType = qType;
	}

	/**
	 * @return the qDescription
	 */
	public String getqDescription() {
		return qDescription;
	}

	/**
	 * @param qDescription the qDescription to set
	 */
	public void setqDescription(String qDescription) {
		this.qDescription = qDescription;
	}

	/**
	 * @return the qMark
	 */
	public Double getqMark() {
		return qMark;
	}

	/**
	 * @param qMark the qMark to set
	 */
	public void setqMark(Double qMark) {
		this.qMark = qMark;
	}

	/**
	 * @return the choices
	 */
	public ArrayList<String> getChoices() {
		return choices;
	}

	/**
	 * @param choices the choices to set
	 */
	public void setChoices(ArrayList<String> choices) {
		this.choices = choices;
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

	

}
