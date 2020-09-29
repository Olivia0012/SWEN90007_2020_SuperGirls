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
import domain.Submission;
import domain.User;
import enumeration.Role;
import mapper.ExamMapper;
import mapper.SubmissionMapper;
import service.UserService;
import serviceImp.ExamServiceImp;
import serviceImp.SubjectServiceImp;
import serviceImp.UserServiceImp;
import util.JsonToObject;
import util.ResponseHeader;

/**
 * Servlet implementation class MarkExamController
 */
@WebServlet("/MarkExamController")
public class MarkExamController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MarkExamController() {
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
			int submissionId = Integer.valueOf(data);

			// String r = JSONObject.toJSONString(subject);
			System.out.println("submissionId: " + data);

			SubmissionMapper submissionMapper = new SubmissionMapper();
			ExamMapper em = new ExamMapper();
			Submission submission = submissionMapper.findById(submissionId);
			Submission resultSubmission = new Submission();
			resultSubmission.setAnswers(submission.getAnswers());
			Exam exam = new Exam();
			exam.setSubject(submission.getExam().getSubject());
			exam.setTitle(submission.getExam().getTitle());
			submission.setExam(exam);
			String result = JSONObject.toJSONString(submission);
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
				response.getWriter().write("You can't mark this exam."); // invalid session.
			} else {

				Submission submission = new Submission();
				JsonToObject jo = new JsonToObject();
				JSONObject SubmissionJsonObject = jo.ReqJsonToObject(request);
				submission = JSON.toJavaObject(SubmissionJsonObject, Submission.class);
				submission.setMarker(user);
				if (submission.getTotalMark() == 0) {
					float totalMark = 0;
					for (Answer an : submission.getAnswers()) {
						totalMark += an.getMark();
					}
					submission.setTotalMark(totalMark);
				}
				ExamServiceImp markExam = new ExamServiceImp();
				if (markExam.markSubmission(submission)) {
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
