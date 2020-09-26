/**
 * This class is used for testing the dev environment.
 * 
 * @author Lu Wang
 */

package servlet;

import java.io.IOException;
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
@WebServlet("/CheckLoginController")
public class CheckLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckLoginController() {
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
		Cookie cookie = null;
		Cookie[] cookies = request.getCookies();
		if( cookies != null ){
            for (int i = 0; i < cookies.length; i++){
               cookie = cookies[i];
               if((cookie.getName()).equals("token") ){
            	   String val = cookie.getValue();
            	   result = val;
                   break;
               }
            }
		}else {
			result = "Session has been expired.";
		}
	
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=UTF-8");
		response.getWriter().write(result);
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