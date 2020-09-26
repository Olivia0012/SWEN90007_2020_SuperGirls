package servlet;

import java.io.IOException;
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
 * Servlet implementation class EditExamController
 */
@WebServlet("/EditExamController")
public class EditExamController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditExamController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserService us = new UserServiceImp();
		ResponseHeader header = new ResponseHeader();
		String val = us.checkLogin(request);

		if (val.equals("0") || val.equals("1")) {
			response.getWriter().write(val); // invalid session.
		} else {
			// finding exam by examId.
			String data = new String(request.getParameter("id").getBytes("ISO-8859-1"), "UTF-8");
			int examId = Integer.valueOf(data);

			// String r = JSONObject.toJSONString(subject);
			System.out.println("examId: " + data);

			ExamMapper em = new ExamMapper();
			Exam exam = em.findById(examId);
			if(exam.getStatus().equals(ExamStatus.CREATED)) {
				exam.setStatus(ExamStatus.PUBLISHED);
			}
			else if(exam.getStatus().equals(ExamStatus.PUBLISHED)) {
				exam.setStatus(ExamStatus.CLOSED);
			}else if(exam.getStatus().equals(ExamStatus.CLOSED)) {
				exam.setStatus(ExamStatus.RELEASED);
			}
			
			ExamServiceImp a = new ExamServiceImp();
			String published = a.publishExam(exam);

			response.getWriter().write(published);
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

			if (!user.getRole().equals(Role.INSTRUCTOR)) {
				response.getWriter().write("You can't edit this exam."); // invalid session.
			} else {

				Exam exam = new Exam();
				JsonToObject jo = new JsonToObject();
				JSONObject examJsonObject = jo.ReqJsonToObject(request);
				exam = JSON.toJavaObject(examJsonObject, Exam.class);

				ExamServiceImp markExam = new ExamServiceImp();
				markExam.updateExam(exam);
				ExamMapper em = new ExamMapper();
				Exam updatedExam = em.findById(exam.getId());
				String result = JSONObject.toJSONString(updatedExam);
				response.getWriter().write(result);
			}

		}

		header.setResponseHeader(response);
	}

}
