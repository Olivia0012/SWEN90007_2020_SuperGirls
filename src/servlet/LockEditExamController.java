package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.User;
import enumeration.Role;
import mapper.ExclusiveWriteLockManager;
import mapper.LockManager;
import serviceImp.ExamServiceImp;
import util.ResponseHeader;
import util.SSOLogin;

/**
 * Add new exam controller
 */
@WebServlet("/LockEditExamController")
public class LockEditExamController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LockEditExamController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ResponseHeader header = new ResponseHeader();
		String data = new String(request.getParameter("examId").getBytes("ISO-8859-1"), "UTF-8");
		String reqlock = new String(request.getParameter("lock").getBytes("ISO-8859-1"), "UTF-8");
		int examId = Integer.valueOf(data);
		boolean islock = reqlock.equals("true");
		
		// Login check.
		SSOLogin ssoCheck = new SSOLogin();
		User user = ssoCheck.checkLogin(request);
		
		String result = "";
		boolean isValid = true;
		String msg = "";

		if (user == null) {
			isValid = false; // invalid token.
		}else if(!user.getRole().equals(Role.INSTRUCTOR)) {
			isValid = false; // invalid user
		}
		else {
			LockManager lock = ExclusiveWriteLockManager.getInstance();
			String token = request.getHeader("token");
			if(islock)
				msg = lock.acquireLock(examId, "exam", token);
			else {
				lock.releaseLock(examId, "exam", token);
				msg = "true";
			}
		}
		result =  "{\"valid\":"+isValid+",\"acquirelock\":\""+msg+"\"}";
		response.getWriter().write(result);
		header.setResponseHeader(response);
	}
	

	/**
	 * This post function is used for creating new exam.
	 * 
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
			ExamServiceImp addExam = new ExamServiceImp();
			boolean success = addExam.addNewExam(request,user);
			
			response.getWriter().write(success+"");

		}

		header.setResponseHeader(response);
	}

}
