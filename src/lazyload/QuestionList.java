package lazyload;

import java.util.List;

import domain.Question;

public interface QuestionList {
	public List<Question> getQuestionList(int examId);
}
