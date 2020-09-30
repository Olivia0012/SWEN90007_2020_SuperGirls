package lazyload;

import java.util.List;

import domain.Answer;

public class AnswerListProxyImp implements AnswerList {
	private AnswerList answerList;

	@Override
	public List<Answer> getAnswerList(int submissionId) {
		if(answerList == null) {
			answerList = new AnswerListImp();
		}
		
		return answerList.getAnswerList(submissionId);
	}
	
}
