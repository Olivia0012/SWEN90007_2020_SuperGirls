package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import domain.Answer;
import domain.Exam;
import domain.Question;
import domain.Submission;
import domain.User;
import enumeration.Role;
import mapper.AnswerMapper;
import mapper.ExamMapper;
import mapper.SubmissionMapper;
import service.UserService;
import serviceImp.ExamServiceImp;
import serviceImp.UserServiceImp;
import util.JsonToObject;
import util.ResponseHeader;
import util.SSOLogin;

/**
 * Servlet implementation class TakeExamController
 */
@WebServlet("/TakeExamController")
public class TakeExamController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ResponseHeader header = new ResponseHeader();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TakeExamController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Students view the released exam result details.
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String token = request.getHeader("token");
		ExamServiceImp examService = new ExamServiceImp(SSOLogin.uowList.get(token));
		String data = new String(request.getParameter("examId").getBytes("ISO-8859-1"), "UTF-8");
		int examId = Integer.valueOf(data);

		// Login check.
		SSOLogin ssoCheck = new SSOLogin();
		User user = ssoCheck.checkLogin(request);

		if (user == null) {
			response.getWriter().write("false"); // invalid token.

		} else {
			// find this submission by the user's id and exam id
			Submission submission = examService.findSubmissionByUserId_ExamId(user.getId(), examId);
			String result = JSONObject.toJSONString(submission);
			response.getWriter().write(result);
		}
		header.setResponseHeader(response);
	}

	/**
	 * Student submits an exam
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Login check.
		SSOLogin ssoCheck = new SSOLogin();
		User user = ssoCheck.checkLogin(request);
		String token = request.getHeader("token");

		if (user == null) {
			response.getWriter().write("invalid"); // invalid token.
		} else {

			if (user.getRole().equals(Role.INSTRUCTOR)) {
				response.getWriter().write("-1"); // invalid session.
			} else {

				ExamServiceImp takeExam = new ExamServiceImp(SSOLogin.uowList.get(token));
				boolean success = takeExam.takeExam(request,user);
				response.getWriter().write(success+"");

			}

		}
		header.setResponseHeader(response);
	}

}
