/**
 * 
 */
package service;


import javax.servlet.http.HttpServletRequest;

import domain.Exam;
import domain.Submission;
import domain.User;

/**
 * @author manlo
 *
 */
public interface ExamService {
	
	// Exam
	public boolean updateExam(Exam exam);//update exam
	public boolean publishExam(String examId); // edit exam status
	
	public boolean addNewExam(HttpServletRequest request,User user);//add new exam
	
	public boolean deleteExamById(int examId);//delete exam
	public boolean deleteQuestionById(int questionId);//delete question
	
	public String findExamById(int examId);//find exam
	public boolean takeExam(HttpServletRequest request,User user);//take exam
	
	
	// Submission
	public Submission findSubmissionById(int submissionId,User user);//find submission by its id
	public Submission findSubmissionByUserId_ExamId(int userId, int examId);
	public boolean markSubmission(Submission submission);//mark a submission
	public boolean addSubmission(Exam exam, User user);//add a new submission
	public boolean addAnswers(Submission submission);//add new answers for a submission
	

}
