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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserService us = new UserServiceImp();
		ResponseHeader header = new ResponseHeader();
		// Checking the login session.
		String val = us.checkLogin(request);
		User user = new User();
		if (val.equals("0") || val.equals("1")) {
			response.getWriter().write(val); // invalid session.
		} else {
			// finding all enrolled subjects by userId.
			JSONObject jsonObject = JSONObject.parseObject(val);
			user = JSON.toJavaObject(jsonObject, User.class);
			
			Submission submission = new Submission();
			JsonToObject jo = new JsonToObject();
			JSONObject submissionJsonObject = jo.ReqJsonToObject(request);
			submission = JSON.toJavaObject(submissionJsonObject, Submission.class);
			
			ExamMapper examMapper = new ExamMapper();

			if (examMapper.findById(submission.getExam().getId()).getStatus() != ExamStatus.PUBLISHED) {
				response.getWriter().write("This exam is not awailable. "); // invalid session.
			} else {

				submission.setStudent(user);
				ExamServiceImp addSubmission = new ExamServiceImp();
				addSubmission.addSubmission(submission);
				SubmissionMapper sm = new SubmissionMapper();
				Submission newSub = sm.findByStudentId(user.getId());
				String result = JSONObject.toJSONString(newSub);
				response.getWriter().write(result);
			}

		}
		
		header.setResponseHeader(response);
	}

}
