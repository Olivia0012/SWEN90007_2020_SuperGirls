package servlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import domain.Exam;
import domain.Subject;
import domain.User;
import enumeration.Role;
import mapper.ExamMapper;
import mapper.SubjectMapper;
import service.UserService;
import serviceImp.ExamServiceImp;
import serviceImp.StudentServiceImp;
import serviceImp.SubjectServiceImp;
import serviceImp.UserServiceImp;
import util.ResponseHeader;

/**
 * Servlet implementation class SubjectController
 */
@WebServlet("/StudentController")
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService us = new UserServiceImp();
	private ResponseHeader header = new ResponseHeader();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// getting subjectId and examId from the request
		String examIdStr = new String(request.getParameter("exam").getBytes("ISO-8859-1"), "UTF-8");
		String subjectIdStr = new String(request.getParameter("subject").getBytes("ISO-8859-1"), "UTF-8");
		int examId = Integer.valueOf(examIdStr);
		int subjectId = Integer.valueOf(subjectIdStr);

		// Checking the login session.
		String val = us.checkLogin(request);
		User user = new User();
		
		if (val.equals("0") || val.equals("1")) {
			response.getWriter().write(val); // invalid session.
		} else {
			// finding all enrolled subjects by userId.
			JSONObject jsonObject = JSONObject.parseObject(val);
		    user = JSON.toJavaObject(jsonObject,User.class);
		    if(user.getRole().equals(Role.INSTRUCTOR)) {
		    	StudentServiceImp a = new StudentServiceImp();
		    	SubjectMapper subjectMapper = new SubjectMapper();
		    	Subject subject = subjectMapper.findById(subjectId);
		    	ExamMapper examMapper = new ExamMapper();
		    	Exam exam = examMapper.findById(examId);
		    	String resultSubject = JSONObject.toJSONString(subject);
				String resultSubmissions = a.findAllStudentsAndSubmissions(subjectId,examId);
				String result = "{\"subject\":"+resultSubject+",\"submissions\":"+resultSubmissions+",\"exam\":\""+exam.getTitle()+"\"}";
				response.getWriter().write(result);
		    }else {
		    	response.getWriter().write("false");
		    }
			
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
