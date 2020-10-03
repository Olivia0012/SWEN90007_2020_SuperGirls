package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import domain.Answer;
import domain.Exam;
import domain.Submission;
import domain.User;
import enumeration.Role;
import mapper.ExamMapper;
import mapper.SubmissionMapper;
import service.UserService;
import serviceImp.ExamServiceImp;
import serviceImp.SubjectServiceImp;
import serviceImp.UserServiceImp;
import util.JsonToObject;
import util.ResponseHeader;
import util.SSOLogin;

/**
 * view and mark a submission controller
 */
@WebServlet("/MarkExamController")
public class MarkExamController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MarkExamController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * view a submission by its id
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ResponseHeader header = new ResponseHeader();

		String data = new String(request.getParameter("id").getBytes("ISO-8859-1"), "UTF-8");
		int submissionId = Integer.valueOf(data);

		// Login check.
		SSOLogin ssoCheck = new SSOLogin();
		User user = ssoCheck.checkLogin(request);

		if (user == null) {
			response.getWriter().write("false"); // invalid token.
		} else {
			ExamServiceImp examService = new ExamServiceImp();
			Submission submission = examService.findSubmissionById(submissionId,user);
			String result = JSONObject.toJSONString(submission);
			response.getWriter().write(result);
		}
		header.setResponseHeader(response);
	}

	/**
	 * 
	 * mark a submission
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
			// Only instructor can mark exams.
			if (!user.getRole().equals(Role.INSTRUCTOR)) {
				response.getWriter().write("false"); // invalid user.
			} else {
				//mark a submission
				ExamServiceImp markExam = new ExamServiceImp();
				boolean success = markExam.markSubmission(request, user);
				response.getWriter().write(success + "");
			}

		}
		header.setResponseHeader(response);

	}

}
