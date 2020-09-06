/**
 * 
 */
package domain;

import java.util.ArrayList;

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
	private ArrayList<String> choices;

	/**
	 * 
	 */
	public Question() {
		// TODO Auto-generated constructor stub
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

}
