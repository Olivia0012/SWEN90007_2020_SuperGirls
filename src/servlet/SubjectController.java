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
import service.UserService;
import serviceImp.SubjectServiceImp;
import serviceImp.UserServiceImp;
import util.ResponseHeader;

/**
 * Querying all enrolled subjects.
 */
@WebServlet("/SubjectController")
public class SubjectController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserServiceImp us = new UserServiceImp();
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
		// Checking the login session.
		String val = us.checkLogin(request);
		User user = new User();
		JSONObject jsonObject = JSONObject.parseObject(val);
	    user = JSON.toJavaObject(jsonObject,User.class);
		
		 System.out.println("val: "+user.getRole()+user.getUserName());
		
		if(val.equals("0") || val.equals("1")) {
			response.getWriter().write(val); // invalid session.
		}else {
			 // finding all enrolled subjects by userId.
			 
		     SubjectServiceImp a = new SubjectServiceImp();
		     String subjects = a.findAllSubjectsByUserId(user.getId(), user.getRole());
		     System.out.println("subjects； "+subjects);
		     String result = "{\"user\":"+JSONObject.toJSONString(user)+",\"subjects\":"+subjects+"}";
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
