/**
 * This class is used for testing the dev environment.
 * 
 * @author Lu Wang
 */

package servlet;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;

import domain.User;
import serviceImp.SubjectServiceImp;
import serviceImp.UserServiceImp;

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String result = null;
		
		String userName = new String(request.getParameter("userName").getBytes("ISO-8859-1"), "UTF-8");
		String passWord = new String(request.getParameter("passWord").getBytes("ISO-8859-1"), "UTF-8");
		
		// check the login info.
		UserServiceImp userlogin = new UserServiceImp();
		User user = userlogin.login(userName, passWord);
		
		// if login successfully, then adding a token to cookie and return the user Id and role.
		if (user != null) {
		//	String id = UUID.randomUUID().toString(); // Create a token
			Cookie cookie = new Cookie("token", user.getId()+"");  // Create a token
			response.addCookie(cookie); // add cookie
			result = JSONObject.toJSONString(user);
			response.getWriter().print(result);
		} else {
			response.getWriter().print("false");
		}

		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=UTF-8");
		// response.getWriter().write(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}