/**
 * 
 */
package service;

import domain.Exam;
import mapper.ExamMapper;
import mapper.QuestionMapper;

/**
 * @author manlo
 *
 */
public class CreateNewExamImp implements CreateNewExam {

	/**
	 * 
	 */
	public CreateNewExamImp() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean CreateNewExam(Exam exam) {
		ExamMapper examMapper = new ExamMapper();
		QuestionMapper questionMapper = new QuestionMapper();
		examMapper.insert(exam);
		
		for(int i = 0; i < exam.getQuestionList().size(); i++)
			questionMapper.insert(exam.getQuestionList().get(i));
		return false;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
