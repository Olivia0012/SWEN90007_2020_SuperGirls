package lazyload;

import java.util.List;

import domain.Question;
import mapper.QuestionMapper;

public class QuestionListImp implements QuestionList{
	private List<Question> questionList;
	private QuestionMapper questionMapper = new QuestionMapper();
	
	
	@Override
	public List<Question> getQuestionList(int examId) {
		return getQuestions(examId);
	}
	
	private List<Question> getQuestions(int examId){
		questionList = questionMapper.findQuestionByExamId(examId);
	
		return questionList;
		
	}
	
}
