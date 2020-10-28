package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import domain.User;
import enumeration.Role;
import serviceImp.SubjectServiceImp;
import util.JsonToObject;
import util.ResponseHeader;
import util.SSOLogin;

/**
 * Admin view and add subjects
 */
@WebServlet("/AdminUserInSubjectController")
public class AdminUserInSubjectController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ResponseHeader header = new ResponseHeader();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminUserInSubjectController() {
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
		// Login check.
		SSOLogin ssoCheck = new SSOLogin();
		User user = ssoCheck.checkLogin(request);

		if (user == null) {
			response.getWriter().write("false"); // invalid token.
		} else if (user.getRole() != Role.ADMIN) {
			response.getWriter().write("false"); // not admin.
		} else {
			// find all subjects
			SubjectServiceImp subjectService = new SubjectServiceImp();
			String result = subjectService.findAllSubjects();

			response.getWriter().write(result);
		}
		header.setResponseHeader(response);
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

		// Login check.
		SSOLogin ssoCheck = new SSOLogin();
		User user = ssoCheck.checkLogin(request);

		if (user == null) {
			response.getWriter().write("false"); // invalid token.
		} else if (user.getRole() != Role.ADMIN) {
			response.getWriter().write("false"); // not admin.
		} else {
			String data = new String(request.getParameter("subjectId").getBytes("ISO-8859-1"), "UTF-8");
			String role = new String(request.getParameter("role").getBytes("ISO-8859-1"), "UTF-8");
			int subjectId = Integer.valueOf(data);

			JsonToObject jo = new JsonToObject();
			List<User> users = jo.ReqJsonToObjectList(request, user);

			SubjectServiceImp addExam = new SubjectServiceImp();

			boolean success = addExam.usersInSubject(subjectId, users,role);

			response.getWriter().write(success + "");

		}

		header.setResponseHeader(response);
	}

}
