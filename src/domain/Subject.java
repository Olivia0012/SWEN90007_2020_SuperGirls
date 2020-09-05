/**
 * 
 */
package domain;

import java.util.ArrayList;

/**
 * @author manlo
 *
 */
public class Subject extends DomainObject{
	private String title;
	private String subjectCode;
	private ArrayList<Exam> examList;
	private ArrayList<User> userList;

	public Subject(int Id, String title, String subjectCode) {
		super(Id);
		this.title = title;
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
	 * @return the subjectCode
	 */
	public String getSubjectCode() {
		return subjectCode;
	}

	/**
	 * @param subjectCode the subjectCode to set
	 */
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	/**
	 * @return the examList
	 */
	public ArrayList<Exam> getExamList() {
		return examList;
	}

	/**
	 * @param examList the examList to set
	 */
	public void setExamList(ArrayList<Exam> examList) {
		this.examList = examList;
	}

	/**
	 * @return the userList
	 */
	public ArrayList<User> getUserList() {
		return userList;
	}

	/**
	 * @param userList the userList to set
	 */
	public void setUserList(ArrayList<User> userList) {
		this.userList = userList;
	}

	

}
