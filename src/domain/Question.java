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
	private QuestionType questionType;
	private String questionDescription;
	private Double questionMark;
	private List<String> choices;

	/**
	 * 
	 */
	public Question() {
		super();
	}

	public Question(int id, int questionnum, QuestionType questionType, String questiondes, Double questionmark, List<String> choice) {
		super(id);
		this.setQuestionNum(questionnum);
		this.setQuestionType(questionType);
		this.setQuestionDescription(questiondes);
		this.setQuestionMark(questionmark);
		this.setChoices(choice);
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
	 * @return the choices
	 */
	public List<String> getChoices() {
		return choices;
	}

	/**
	 * @param choices the choices to set
	 */
	public void setChoices(List<String> choices) {
		this.choices = choices;
	}

	/**
	 * @return the questionType
	 */
	public QuestionType getQuestionType() {
		return questionType;
	}

	/**
	 * @param questionType the questionType to set
	 */
	public void setQuestionType(QuestionType questionType) {
		this.questionType = questionType;
	}

	/**
	 * @return the questionDescription
	 */
	public String getQuestionDescription() {
		return questionDescription;
	}

	/**
	 * @param questionDescription the questionDescription to set
	 */
	public void setQuestionDescription(String questionDescription) {
		this.questionDescription = questionDescription;
	}

	/**
	 * @return the questionMark
	 */
	public Double getQuestionMark() {
		return questionMark;
	}

	/**
	 * @param questionMark the questionMark to set
	 */
	public void setQuestionMark(Double questionMark) {
		this.questionMark = questionMark;
	}


}
