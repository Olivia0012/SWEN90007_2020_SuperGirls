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
import serviceImp.ExamServiceImp;
import serviceImp.SubjectServiceImp;
import util.JsonToObject;
import util.ResponseHeader;
import util.SSOLogin;

import java.io.BufferedReader;

/**
 * GET method: find exam by examId; POST method: create new submission when a
 * student takes an exam.
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
	 * find exam by examId;
	 * 
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
			// get examId from request.
			String data = new String(request.getParameter("examId").getBytes("ISO-8859-1"), "UTF-8");
			int examId = Integer.valueOf(data);

			// find exam info
			ExamServiceImp examService = new ExamServiceImp();
			String result = examService.findExamById(examId);

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

			// add new submission into database
			ExamServiceImp examService = new ExamServiceImp();
			
			boolean success = examService.addSubmission(exam, user);
			if(success) {
				int newSubmissionId = examService.findSubmissionByUserId_ExamId(user.getId(), exam.getId()).getId();
				response.getWriter().write(newSubmissionId + "");
			}else {
				response.getWriter().write("-1");
			}
		}

		header.setResponseHeader(response);
	}

}
