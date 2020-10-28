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
import domain.Question;
import domain.Submission;
import domain.User;
import enumeration.ExamStatus;
import enumeration.Role;
import mapper.ExamMapper;
import mapper.ExclusiveWriteLockManager;
import mapper.LockManager;
import mapper.SubmissionMapper;
import serviceImp.ExamServiceImp;
import util.JsonToObject;
import util.ResponseHeader;
import util.SSOLogin;

/**
 * Edit exam controller
 * 
 * GET method: update exam status ; POST method: update exam content
 */
@WebServlet("/EditExamController")
public class EditExamController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ResponseHeader header = new ResponseHeader();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditExamController() {
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
		String token = request.getHeader("token");

		if (user == null) {
			response.getWriter().write("false"); // invalid token.

		} else {
			String data = new String(request.getParameter("id").getBytes("ISO-8859-1"), "UTF-8");

			// update exam status.
			ExamServiceImp a = new ExamServiceImp(SSOLogin.uowList.get(token));
			boolean success = a.publishExam(data);

			response.getWriter().write(success+"");
		}
		header.setResponseHeader(response);
	}

	/**
	 * Update exam
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
			response.getWriter().write("false"); // invalid token.

		} else {
			// Only instructor can edit exams.
			if (!user.getRole().equals(Role.INSTRUCTOR)) {
				response.getWriter().write("false"); // invalid session.
			} else {
				//get edited exam from request
				Exam exam = new Exam();
				JsonToObject jo = new JsonToObject();
				JSONObject examJsonObject = jo.ReqJsonToObject(request);
				exam = JSON.toJavaObject(examJsonObject, Exam.class);
				
				// Update exam
				ExamServiceImp markExam = new ExamServiceImp(SSOLogin.uowList.get(token));
				boolean success = markExam.updateExam(exam);
				
				//release the edit exam lock
				LockManager lock = ExclusiveWriteLockManager.getInstance();
				lock.releaseLock(exam.getId(), "exam", request.getHeader("token"));
				
				response.getWriter().write(success + "");
			}

		}

		header.setResponseHeader(response);
	}

}
