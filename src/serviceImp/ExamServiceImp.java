package serviceImp;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import domain.Answer;
import domain.Exam;
import domain.Question;
import domain.Submission;
import domain.User;
import enumeration.ExamStatus;
import enumeration.Role;
import mapper.AnswerMapper;
import mapper.ExamMapper;
import mapper.QuestionMapper;
import mapper.SubjectMapper;
import mapper.SubmissionMapper;
import mapper.UserMapper;
import service.ExamService;
import shared.UnitOfWork;
import util.JsonToObject;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class ExamServiceImp implements ExamService {

	private ExamMapper examMapper;
	private SubjectMapper subjectMapper;
	private UserMapper userMapper;
	private SubmissionMapper submissionMapper;
	private AnswerMapper answerMapper;
	private QuestionMapper questionMapper;
	private JsonToObject jo = new JsonToObject();

	public ExamServiceImp() {
		examMapper = new ExamMapper();
		subjectMapper = new SubjectMapper();
		userMapper = new UserMapper();
		submissionMapper = new SubmissionMapper();
		answerMapper = new AnswerMapper();
		questionMapper = new QuestionMapper();
	}

	/**
	 * Find an exam by exam id.
	 */
	@Override
	public String findExamById(int examId) {
		Exam e = examMapper.findById(examId);

		// lazy loading question list.
		e.getQuestionList();
		String result = JSONObject.toJSONString(e);
		return result;
	}

	/**
	 * Edit existing exam
	 */
	@Override
	public boolean updateExam(HttpServletRequest request) {
		UnitOfWork.newCurrent();
		Exam exam = new Exam();
		JSONObject examJsonObject = jo.ReqJsonToObject(request);
		exam = JSON.toJavaObject(examJsonObject, Exam.class);

		// update the exam
		UnitOfWork.getCurrent().registerDirty(exam);

		// update questions
		for (int i = 0; i < exam.getQuestionList().size(); i++) {
			// add new questions
			if (exam.getQuestionList().get(i).getId() == -1) {
				UnitOfWork.getCurrent().registerNew(exam.getQuestionList().get(i));
			} else {
				// update existing questions
				UnitOfWork.getCurrent().registerDirty(exam.getQuestionList().get(i));
			}
		}

		return UnitOfWork.getCurrent().commit();

	}

	/**
	 * delete a question by its id
	 */
	@Override
	public boolean deleteQuestionById(int questionId) {
		UnitOfWork.newCurrent();

		Question question = questionMapper.findById(questionId);

		if (question == null) {
			return false;
		} else {
			UnitOfWork.getCurrent().registerDeleted(question);
			return UnitOfWork.getCurrent().commit();
		}

	}

	/**
	 * Adding new exam for a subject
	 */
	@Override
	public boolean addNewExam(HttpServletRequest request, User user) {
		UnitOfWork.newCurrent();

		// get the new exam info from the request.
		JSONObject examJsonObject = jo.ReqJsonToObject(request);

		Exam exam = new Exam();
		exam = JSON.toJavaObject(examJsonObject, Exam.class);
		exam.setCreator(user);

		UnitOfWork.getCurrent().registerNew(exam);

		return UnitOfWork.getCurrent().commit();

	}

	/**
	 * delete an exam by its id
	 */
	@Override
	public boolean deleteExamById(int examId) {
		UnitOfWork.newCurrent();

		Exam exam = examMapper.findById(examId);

		if (exam == null) {
			return false;
		} else {

			UnitOfWork.getCurrent().registerDeleted(exam);
			return UnitOfWork.getCurrent().commit();
		}
	}

	/**
	 * mark an exam submission
	 */
	@Override
	public boolean markSubmission(HttpServletRequest request, User user) {
		UnitOfWork.newCurrent();
		Submission submission = new Submission();
		JSONObject SubmissionJsonObject = jo.ReqJsonToObject(request);
		submission = JSON.toJavaObject(SubmissionJsonObject, Submission.class);
		submission.setMarker(user);

		// calculate the total mark
		if (submission.getTotalMark() == 0) {
			float totalMark = 0;
			for (Answer an : submission.getAnswers()) {
				totalMark += an.getMark();
			}
			submission.setTotalMark(totalMark);
		}

		UnitOfWork.getCurrent().registerDirty(submission);

		for (Answer an : submission.getAnswers()) {
			UnitOfWork.getCurrent().registerDirty(an);
		}
		return UnitOfWork.getCurrent().commit();
	}

	/**
	 * Finding submission by its id
	 */
	@Override
	public Submission findSubmissionById(int submissionId, User user) {
		Submission submission = submissionMapper.findById(submissionId);
		if (user.getRole().equals(Role.STUDENT)) {
			if (submission.getAnswers() == null) {
				addAnswers(submission);
				submission.setAnswers(answerMapper.findAnswersBySubmissionId(submissionId));
			}
		} else {
			return submission;
		}
		return submission;

	}

	@Override
	public boolean publishExam(String examId) {
		UnitOfWork.newCurrent();
		int examid = Integer.valueOf(examId);

		// update exam status
		Exam exam = examMapper.findById(examid);
		if (exam.getStatus().equals(ExamStatus.CREATED)) {
			exam.setStatus(ExamStatus.PUBLISHED);
		} else if (exam.getStatus().equals(ExamStatus.PUBLISHED)) {
			exam.setStatus(ExamStatus.CLOSED);
		} else if (exam.getStatus().equals(ExamStatus.CLOSED)) {
			exam.setStatus(ExamStatus.RELEASED);
		}

		UnitOfWork.getCurrent().registerDirty(exam);

		return UnitOfWork.getCurrent().commit();
	}

	@Override
	public boolean addSubmission(Exam exam, User user) {
		UnitOfWork.newCurrent();

		// check if this student has taken this exam.
		Submission checkSub = submissionMapper.FindSubmissionsByUserId_ExamId(user.getId(), exam.getId());

		// not taken this exam before
		if (checkSub.getId() == 0) {

			// create new submission and answers object
			Submission newSub = new Submission();
			newSub.setExam(exam);
			newSub.setLock(false);
			newSub.setStudent(user);

			// add new submission and its answers into database
			UnitOfWork.getCurrent().registerNew(newSub);
		} else {
			return false;
		}

		return UnitOfWork.getCurrent().commit();

	}

	/**
	 * Submit the exam
	 */
	@Override
	public boolean takeExam(HttpServletRequest request, User user) {
		UnitOfWork.newCurrent();
		Submission submission = new Submission();
		JsonToObject jo = new JsonToObject();
		JSONObject SubmissionJsonObject = jo.ReqJsonToObject(request);
		submission = JSON.toJavaObject(SubmissionJsonObject, Submission.class);
		submission.setStudent(user);

		Exam exam = examMapper.findById(submission.getExam().getId());

		// this exam cannot be submitted when exam has been closed.
		if (!exam.getStatus().equals(ExamStatus.PUBLISHED)) {
			return false;
		} else {
			// update submission info
			UnitOfWork.getCurrent().registerDirty(submission);

			if (submission.getAnswers().size() > 0)
				for (Answer an : submission.getAnswers()) {
					// update answers
					UnitOfWork.getCurrent().registerDirty(an);
				}

			return UnitOfWork.getCurrent().commit();
		}

	}

	@Override
	public Submission findSubmissionByUserId_ExamId(int userId, int examId) {
		Submission submission = submissionMapper.FindSubmissionsByUserId_ExamId(userId, examId);

		if (submission != null) {
			submission.getAnswers();
			return submission;
		} else
			return null;
	}

	/**
	 * Add new answers for an submission
	 */
	@Override
	public boolean addAnswers(Submission submission) {
		UnitOfWork.newCurrent();
		List<Question> questions = questionMapper.findQuestionByExamId(submission.getExam().getId());

		for (Question question : questions) {
			Answer answer = new Answer();
			answer.setQuestion(question);
			answer.setSubmissionId(submission.getId());
			UnitOfWork.getCurrent().registerNew(answer);
		}

		return UnitOfWork.getCurrent().commit();
	}

}
