package service;

import domain.Question;
import mapper.QuestionMapper;

public class QuestionServiceImp implements QuestionService {

	@Override
	public String insertNewQuestion(Question question) {
		QuestionMapper questionMapper = new QuestionMapper();
		questionMapper.insert(question);
		return "OK";
	}

}
