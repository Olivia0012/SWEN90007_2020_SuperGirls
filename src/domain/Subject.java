/**
 * 
 */
package domain;

import java.util.List;

import shared.UnitOfWorkImp;

/**
 * @author manlo
 *
 */
public class Subject extends DomainObject{
	private String title;
	private String subjectCode;
	private List<Student> students;
	private List<Exam> exams;

	public Subject(int Id, String title, String subjectCode) {
		super(Id);
		this.setTitle(title);
		this.setSubjectCode(subjectCode);
		UnitOfWorkImp.newCurrent();
		UnitOfWorkImp.getCurrent().registerNew(this);
	}
	
	public Subject(int Id, String title, String subjectCode, List<Student> students, List<Exam> exams) {
		super(Id);
		UnitOfWorkImp.newCurrent();
		UnitOfWorkImp.getCurrent().registerNew(this);
		this.setTitle(title);
		this.setSubjectCode(subjectCode);
		this.setExams(exams);
		this.setStudents(students);
	}

	public Subject() {
		UnitOfWorkImp.newCurrent();
		UnitOfWorkImp.getCurrent().registerNew(this);
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
	//	UnitOfWorkImp.getCurrent().registerNew(this);
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
	//	UnitOfWorkImp.getCurrent().registerNew(this);
	}

	/**
	 * @return the students
	 */
	public List<Student> getStudents() {
		return students;
	}

	/**
	 * @param students the students to set
	 */
	public void setStudents(List<Student> students) {
		this.students = students;
		UnitOfWorkImp.getCurrent().registerNew(this);
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
		UnitOfWorkImp.getCurrent().registerNew(this);
	}



	

}
