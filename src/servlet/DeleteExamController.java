package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.User;
import serviceImp.ExamServiceImp;
import util.ResponseHeader;
import util.SSOLogin;

/** 
 * Delete Exam Controller
 */
@WebServlet("/DeleteExamController")
public class DeleteExamController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteExamController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * 
	 * delete an exam
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String data =new String(request.getParameter("examId").getBytes("ISO-8859-1"),"UTF-8");
	    int examId = Integer. valueOf(data);
	    
	    ResponseHeader header = new ResponseHeader();
	    String token = request.getHeader("token");

		// Login check.
		SSOLogin ssoCheck = new SSOLogin();
		User user = ssoCheck.checkLogin(request);

		if (user == null) {
			response.getWriter().write("false"); // invalid token.
		} else {
			//delete the question
			ExamServiceImp deleteExam = new ExamServiceImp(SSOLogin.uowList.get(token));
			boolean success = deleteExam.deleteExamById(examId);

			response.getWriter().write(success + "");
		}
		header.setResponseHeader(response);
	    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
