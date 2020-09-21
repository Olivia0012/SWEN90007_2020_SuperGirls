/**
 * 
 */
package service;

import java.util.List;

import domain.Exam;
import domain.Question;
import domain.User;

/**
 * @author manlo
 *
 */
public interface ExamService {
	public String findExamById(int examId);
	public String findAllExams();
	public String updateExam(Exam exam);
	
	public String addNewExam(Exam exam);
	public boolean addNewExamCheckingUser(Exam exam, User instructor);
	
	public String deleteQuestionById(int questionId);
	public String deleteExamById(int examId);

}
