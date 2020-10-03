/**
 * 
 */
package domain;

import java.sql.ResultSet;
import java.util.List;

import mapper.SubjectMapper;

/**
 * @author manlo
 *
 */
public class Subject extends DomainObject {
	private String title;
	private String subjectCode;
	private List<Exam> exams;

	public Subject(int Id, String title, String subjectCode) {
		super(Id);
		this.setTitle(title);
		this.setSubjectCode(subjectCode);
	}

	public Subject(int Id, String title, String subjectCode, List<Exam> exams) {
		super(Id);
		this.setTitle(title);
		this.setSubjectCode(subjectCode);
		this.setExams(exams);
	}

	public Subject() {
		super();
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
	 * @return the exams
	 */
	public List<Exam> getExams() {
		return exams;
	}

	/**
	 * @param exams the exams to set
	 */
	public void setExams(List<Exam> exams) {
		this.exams = exams;
	}

	

}
