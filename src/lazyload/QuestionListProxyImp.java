package lazyload;

import java.util.List;

import domain.Question;

public class QuestionListProxyImp implements QuestionList{
	private QuestionList questionList;
	
	@Override
	public List<Question> getQuestionList(int examId) {
		if(questionList == null) {
			questionList = new QuestionListImp();
		}
		
		return questionList.getQuestionList(examId);
	}

}
