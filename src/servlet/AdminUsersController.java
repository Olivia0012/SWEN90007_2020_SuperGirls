package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.User;
import enumeration.Role;
import serviceImp.ExamServiceImp;
import serviceImp.SubjectServiceImp;
import serviceImp.UserServiceImp;
import util.ResponseHeader;
import util.SSOLogin;

/**
 * Admin view and add subjects
 */
@WebServlet("/AdminUsersController")
public class AdminUsersController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ResponseHeader header = new ResponseHeader();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminUsersController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 *      
	 *  Admin view all users
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Login check.
		SSOLogin ssoCheck = new SSOLogin();
		User user = ssoCheck.checkLogin(request);

		if (user == null) {
			response.getWriter().write("false"); // invalid token.
		} else if (user.getRole() != Role.ADMIN) {
			response.getWriter().write("false"); // not admin.
		}else {
			String userType = new String(request.getParameter("userType").getBytes("ISO-8859-1"), "UTF-8");
			String subjectId = new String(request.getParameter("subjectId").getBytes("ISO-8859-1"), "UTF-8");
			// find all users
			UserServiceImp userService = new UserServiceImp();
			SubjectServiceImp subjectService = new SubjectServiceImp();
	//		subjectService.findAllUsersBySubjectId(Integer.parseInt(subjectId));
			String result = userService.findAllUsers(Role.valueOf(userType),Integer.parseInt(subjectId));

			response.getWriter().write(result);
		}
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
			UserServiceImp addExam = new UserServiceImp();
			boolean success = addExam.addNewUser(request);

			response.getWriter().write(success + "");

		}

		header.setResponseHeader(response);
	}

}
