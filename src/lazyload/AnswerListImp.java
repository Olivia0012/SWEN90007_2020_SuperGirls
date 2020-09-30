package lazyload;

import java.util.List;

import domain.Answer;
import mapper.AnswerMapper;

public class AnswerListImp implements AnswerList{
	private List<Answer> answerList;
	private AnswerMapper answerMapper = new AnswerMapper();

	@Override
	public List<Answer> getAnswerList(int submissionId) {
		return getAnswers(submissionId);
	}
	
	private List<Answer> getAnswers(int submissionId){
		answerList = answerMapper.findAnswersBySubmissionId(submissionId);
	
		return answerList;
		
	}

}
