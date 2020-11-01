package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import domain.Submission;
import domain.User;
import enumeration.Role;
import mapper.ExclusiveWriteLockManager;
import mapper.LockManager;
import serviceImp.SubjectServiceImp;
import serviceImp.SubmissionServiceImp;
import util.JsonToObject;
import util.ResponseHeader;
import util.SSOLogin;

/**
 * Admin view and add subjects
 */
@WebServlet("/InstructorEditMarkController")
public class InstructorEditMarkController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ResponseHeader header = new ResponseHeader();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InstructorEditMarkController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * 
	 *      Admin view all subjects
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

	/**
	 * This post function is used for updating users in subject.
	 * 
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ResponseHeader header = new ResponseHeader();
		String token = request.getHeader("token");

		// Login check.
		SSOLogin ssoCheck = new SSOLogin();
		User user = ssoCheck.checkLogin(request);

		if (user == null) {
			response.getWriter().write("false"); // invalid token.
		} else if (user.getRole() != Role.INSTRUCTOR) {
			response.getWriter().write("false"); // not instructor.
		} else {
			SubmissionServiceImp addExam = new SubmissionServiceImp(SSOLogin.uowList.get(token));
			
			Submission submission = new Submission();
			
			JsonToObject jo = new JsonToObject();
			JSONObject submissionJsonObject = jo.ReqJsonToObject(request);
			submission = JSON.toJavaObject(submissionJsonObject, Submission.class);
			
			boolean success = addExam.updateSubmission(submission);
			
			LockManager lock = ExclusiveWriteLockManager.getInstance();
			
			//release the lock
			lock.releaseLock(submission.getId(), "submissions", token);
			response.getWriter().write(success + "");

		}

		header.setResponseHeader(response);
	}

}
