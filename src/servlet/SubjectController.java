package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import domain.User;
import mapper.ExclusiveWriteLockManager;
import mapper.LockManager;
import service.UserService;
import serviceImp.SubjectServiceImp;
import serviceImp.UserServiceImp;
import util.ResponseHeader;
import util.SSOLogin;

/**
 * Querying all enrolled subjects.
 */
@WebServlet("/SubjectController")
public class SubjectController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SSOLogin ssoCheck = new SSOLogin();
	private ResponseHeader header = new ResponseHeader();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SubjectController() {
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
		User user = ssoCheck.checkLogin(request);
		String token = request.getHeader("token");
		
		if(user == null) {
			response.getWriter().write("false"); //invalid token.
		}else {
			 // finding all enrolled subjects for the user.
		     SubjectServiceImp a = new SubjectServiceImp(SSOLogin.uowList.get(token));
		     String subjects = a.findAllSubjectsByUserId(user.getId(), user.getRole());
		     System.out.println("subjects； "+subjects);
		     String result = "{\"user\":"+JSONObject.toJSONString(user)+",\"subjects\":"+subjects+"}";
		     LockManager lock = ExclusiveWriteLockManager.getInstance();
				
			 //release the lock
			 lock.releaseAllLocks(token);
		     response.getWriter().write(result);
		}
		header.setResponseHeader(response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
