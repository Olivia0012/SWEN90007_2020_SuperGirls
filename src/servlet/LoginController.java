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
import enumeration.Role;
import serviceImp.SubjectServiceImp;
import serviceImp.UserServiceImp;
import util.ResponseHeader;
import util.SSOLogin;

/**
 * User login controller
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
	 * User Login controller
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String result = null;
		
		String userName = new String(request.getParameter("userName").getBytes("ISO-8859-1"), "UTF-8");
		String passWord = new String(request.getParameter("passWord").getBytes("ISO-8859-1"), "UTF-8");
		
		// Authenticate the user info.
		UserServiceImp userlogin = new UserServiceImp();
		User user = userlogin.login(userName, passWord);
		
		// Authenticated successfully  
		if (user != null) {
			// Create a token and record the user info in the SSO login list.
			String token = UUID.randomUUID().toString(); 
			SSOLogin userLogin = new SSOLogin();
			userLogin.login(token, user);
			
			// return the token and userInfo
			result = JSONObject.toJSONString(user);
			response.setHeader("Token", token);
			response.getWriter().print(result);
		} else {
			response.getWriter().print("false");
		}

		ResponseHeader responseHeader = new ResponseHeader();
		responseHeader.setResponseHeader(response);
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