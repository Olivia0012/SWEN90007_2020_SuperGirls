package lazyload;

import java.util.List;

import domain.Answer;

public interface AnswerList {
	public List<Answer> getAnswerList(int submissionId);
}
