package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import domain.Exam;
import domain.Submission;
import domain.User;
import enumeration.ExamStatus;
import enumeration.Role;
import mapper.ExamMapper;
import mapper.SubmissionMapper;
import service.UserService;
import serviceImp.ExamServiceImp;
import serviceImp.UserServiceImp;
import util.JsonToObject;
import util.ResponseHeader;
import util.SSOLogin;

/**
 * Servlet implementation class AddExamController
 */
@WebServlet("/AddExamController")
public class AddExamController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddExamController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
			
			Exam exam = new Exam();
			ExamMapper examMapper = new ExamMapper();
			ExamServiceImp addExam = new ExamServiceImp();
			JsonToObject jo = new JsonToObject();
			JSONObject examJsonObject = jo.ReqJsonToObject(request);
			
			exam = JSON.toJavaObject(examJsonObject, Exam.class);
			exam.setCreator(user);
			
			// Add this new exam into database and return its id.
			int examId = Integer.valueOf(addExam.addNewExam(exam));
			examMapper.findById(examId);
			SubmissionMapper sm = new SubmissionMapper();
			Submission newSub = sm.findByStudentId(user.getId());
			String result = JSONObject.toJSONString(newSub);
			response.getWriter().write(result);

		}

		header.setResponseHeader(response);
	}

}
