package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Question;
import domain.User;
import mapper.ExclusiveWriteLockManager;
import mapper.LockManager;
import mapper.QuestionMapper;
import serviceImp.ExamServiceImp;
import util.ResponseHeader;
import util.SSOLogin;

/**
 * Delete Question Controller
 */
@WebServlet("/DeleteQuestionController")
public class DeleteQuestionController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteQuestionController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Delete a question by its id.
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ResponseHeader header = new ResponseHeader();

		String data = new String(request.getParameter("questionId").getBytes("ISO-8859-1"), "UTF-8");
		int questionId = Integer.valueOf(data);
		String token = request.getHeader("token");

		// Login check.
		SSOLogin ssoCheck = new SSOLogin();
		User user = ssoCheck.checkLogin(request);

		if (user == null) {
			response.getWriter().write("false"); // invalid token.
		} else {
			//delete the question
			ExamServiceImp deleteQuestion = new ExamServiceImp(SSOLogin.uowList.get(token));
			QuestionMapper qm = new QuestionMapper();
			Question q = qm.findById(questionId);
			
			boolean success = deleteQuestion.deleteQuestionById(questionId);
			
			//release the edit exam lock
			LockManager lock = ExclusiveWriteLockManager.getInstance();
			lock.releaseLock(q.getExamId(), "exam", request.getHeader("token"));

			response.getWriter().write(success + "");

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
