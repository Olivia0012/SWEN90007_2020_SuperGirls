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

	public Subject(int Id, String title, String subjectCode) {
		super(Id);
		this.setTitle(title);
		this.setSubjectCode(subjectCode);
	}

	public Subject() {
		// TODO Auto-generated constructor stub
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



	

}
