package servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import domain.Answer;
import domain.Exam;
import domain.Question;
import domain.Submission;
import domain.User;
import enumeration.ExamStatus;
import mapper.ExamMapper;
import mapper.SubmissionMapper;
import service.UserService;
import serviceImp.ExamServiceImp;
import serviceImp.SubjectServiceImp;
import serviceImp.UserServiceImp;
import util.JsonToObject;
import util.ResponseHeader;
import util.SSOLogin;

import java.io.BufferedReader;

/**
 * GET method: find exam by examId;
 * POST method: create new submission when a student takes an exam.
 */
@WebServlet("/ExamController")
public class ExamController extends HttpServlet {
	private static final long serialVersionUID = 102831973239L;
	private ResponseHeader header = new ResponseHeader();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExamController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Login check.
		SSOLogin ssoCheck = new SSOLogin();
		User user = ssoCheck.checkLogin(request);

		if (user == null) {
			response.getWriter().write("false"); // invalid token.
		} else {
			// finding exam by examId.
			String data = new String(request.getParameter("examId").getBytes("ISO-8859-1"), "UTF-8");
			int examId = Integer.valueOf(data);

			ExamServiceImp a = new ExamServiceImp();
			String exam = a.findExamById(examId);

			String result = JSONObject.toJSONString(exam);
			response.getWriter().write(result);
		}
		header.setResponseHeader(response);

	}

	/**
	 * Create new submissions when students click the "take" exam button.
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		SubmissionMapper sm = new SubmissionMapper();
		ExamMapper em = new ExamMapper();
		ResponseHeader header = new ResponseHeader();
		
		// Login check.
		SSOLogin ssoCheck = new SSOLogin();
		User user = ssoCheck.checkLogin(request);

		if (user == null) {
			response.getWriter().write("false"); // invalid token.
		} else {
			// get exam from the request.
			Exam exam = new Exam();
			JsonToObject jo = new JsonToObject();
			JSONObject examJsonObject = jo.ReqJsonToObject(request);
			exam = JSON.toJavaObject(examJsonObject, Exam.class);

			// check if this student has taken this exam.
			Submission checkSub = sm.FindSubmissionsByUserId_ExamId(user.getId(), exam.getId());

			// not taken this exam before
			if (checkSub.getId() == 0) {
				
				// create new submission and answers object
				Submission newSub = new Submission();
				newSub.setExam(exam);
				newSub.setLock(false);
				newSub.setStudent(user);

				List<Answer> answers = new ArrayList<Answer>();
				List<Question> questions = em.findById(exam.getId()).getQuestionList();
				
				for (Question q : questions) {
					Answer an = new Answer();
					an.setQuestion(q);
					answers.add(an);
				}

				newSub.setAnswers(answers);

				// add new submission and its answers into database
				ExamServiceImp esi = new ExamServiceImp();
				int newSubmissionId = esi.addSubmission(newSub);
				
				// add new submission successfully
				if (newSubmissionId != 0) {
					Submission newSubmission = sm.findById(newSubmissionId);
					String result = JSONObject.toJSONString(newSubmission);
					response.getWriter().write(result);
				} else
					response.getWriter().write("0");
			} else
				response.getWriter().write("1");

		}

		header.setResponseHeader(response);
	}



}
