/**
 * 
 */
package service;

import java.util.List;

import domain.Exam;

/**
 * @author manlo
 *
 */
public interface ViewExam {
	public Exam findExamById(int examId);
	public String findAllExams();

}
