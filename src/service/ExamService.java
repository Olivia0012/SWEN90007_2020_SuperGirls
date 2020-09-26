/**
 * 
 */
package service;

import java.util.List;

import domain.Exam;
import domain.Question;
import domain.Submission;
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
	
	public String deleteQuestionById(int questionId);
	public String deleteExamById(int examId);
	boolean markSubmission(Submission submission);
	String findSubmissionById(int submissionId);
	String publishExam(Exam exam);
	int addSubmission(Submission submission);
	boolean takeExam(Submission submission);

}
