package servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import domain.Answer;
import domain.Exam;
import domain.Question;
import domain.Submission;
import domain.User;
import enumeration.ExamStatus;
import mapper.ExamMapper;
import mapper.SubmissionMapper;
import service.UserService;
import serviceImp.ExamServiceImp;
import serviceImp.SubjectServiceImp;
import serviceImp.UserServiceImp;
import util.JsonToObject;
import util.ResponseHeader;

import java.io.BufferedReader;

/**
 * Servlet implementation class ViewExamController
 */
@WebServlet("/ExamController")
public class ExamController extends HttpServlet {
	private static final long serialVersionUID = 102831973239L;
	private UserService us = new UserServiceImp();
	private ResponseHeader header = new ResponseHeader();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExamController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String val = us.checkLogin(request);

		if (val.equals("0") || val.equals("1")) {
			response.getWriter().write(val); // invalid session.
		} else {
			// finding exam by examId.
			String data = new String(request.getParameter("examId").getBytes("ISO-8859-1"), "UTF-8");
			int examId = Integer.valueOf(data);

		//	String r = JSONObject.toJSONString(subject);
			System.out.println("examId: "+data);
			
			ExamServiceImp a = new ExamServiceImp();
			String exam = a.findExamById(examId);

			String result = JSONObject.toJSONString(exam);
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
			
			Exam exam = new Exam();
			JsonToObject jo = new JsonToObject();
			JSONObject examJsonObject = jo.ReqJsonToObject(request);
			exam = JSON.toJavaObject(examJsonObject, Exam.class);
			
			Submission checkSub = sm.FindSubmissionsByUserId_ExamId(user.getId(), exam.getId());
			String result1 = JSONObject.toJSONString(checkSub);
			
			List<Question> questions = em.findById(exam.getId()).getQuestionList();
			
			
			if(checkSub.getId() == 0) {
				Submission newSub = new Submission();
				newSub.setExam(exam);
				newSub.setLock(false);
				newSub.setStudent(user);
				
				List<Answer> answers = new ArrayList<Answer>();
				
				for(Question q: questions) {
					Answer an = new Answer();
					an.setQuestion(q);
					answers.add(an);
				}
				
				newSub.setAnswers(answers);
				
				ExamServiceImp esi = new ExamServiceImp();
				int newSubmissionId = esi.addSubmission(newSub);
				if( newSubmissionId != 0) {
					
					Submission newSubmission = sm.findById(newSubmissionId);
					String result = JSONObject.toJSONString(newSubmission);
					response.getWriter().write(result);
				} else response.getWriter().write("0");
			}else response.getWriter().write("1");
			
			
			
			

		/*	if (examMapper.findById(submission.getExam().getId()).getStatus() != ExamStatus.PUBLISHED) {
				response.getWriter().write("This exam is not awailable. "); // invalid session.
			} else {

				submission.setStudent(user);
				ExamServiceImp addSubmission = new ExamServiceImp();
				addSubmission.addSubmission(submission);
				SubmissionMapper sm = new SubmissionMapper();
				Submission newSub = sm.findByStudentId(user.getId());
				String result = JSONObject.toJSONString(newSub);
				response.getWriter().write(result);
			}*/

		}
		
		header.setResponseHeader(response);
	}

	public String readJSONString(HttpServletRequest request) {
		String param = null;
		try {
			BufferedReader streamReader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
			StringBuilder responseStrBuilder = new StringBuilder();
			String inputStr;
			while ((inputStr = streamReader.readLine()) != null)
				responseStrBuilder.append(inputStr);

			JSONObject jsonObject = JSONObject.parseObject(responseStrBuilder.toString());
			param = jsonObject.toJSONString();
			System.out.println(param);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return param;

	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
