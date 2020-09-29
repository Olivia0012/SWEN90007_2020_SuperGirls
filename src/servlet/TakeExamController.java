package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import domain.Answer;
import domain.Exam;
import domain.Question;
import domain.Submission;
import domain.User;
import enumeration.Role;
import mapper.AnswerMapper;
import mapper.ExamMapper;
import mapper.SubmissionMapper;
import service.UserService;
import serviceImp.ExamServiceImp;
import serviceImp.UserServiceImp;
import util.JsonToObject;
import util.ResponseHeader;

/**
 * Servlet implementation class TakeExamController
 */
@WebServlet("/TakeExamController")
public class TakeExamController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TakeExamController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Students view the released exam result details.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserService us = new UserServiceImp();
		SubmissionMapper sm = new SubmissionMapper();
		ExamMapper em = new ExamMapper();
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

		
			String data =new String(request.getParameter("examId").getBytes("ISO-8859-1"),"UTF-8");
		    int examId = Integer. valueOf(data);

			Submission submission = sm.FindSubmissionsByUserId_ExamId(user.getId(), examId);

			if (submission.getId() != 0) {
				Exam exam = em.findById(examId);
				submission.setExam(exam);
				String result = JSONObject.toJSONString(submission);
				response.getWriter().write(result);
			} else
				response.getWriter().write("0");
		}

		header.setResponseHeader(response);
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

			if (user.getRole().equals(Role.INSTRUCTOR)) {
				response.getWriter().write("Instructor cannot take exam."); // invalid session.
			} else {

				Submission submission = new Submission();
				JsonToObject jo = new JsonToObject();
				JSONObject SubmissionJsonObject = jo.ReqJsonToObject(request);
				submission = JSON.toJavaObject(SubmissionJsonObject, Submission.class);
				submission.setStudent(user);

				ExamServiceImp takeExam = new ExamServiceImp();
				if (takeExam.takeExam(submission)) {
					SubmissionMapper sm = new SubmissionMapper();
					Submission markedSubmission = sm.findById(submission.getId());
					String result = JSONObject.toJSONString(markedSubmission);
					response.getWriter().write(result);
				}

			}

		}
		header.setResponseHeader(response);
	}

}
