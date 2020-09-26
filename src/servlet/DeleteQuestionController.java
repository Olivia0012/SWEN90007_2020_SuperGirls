package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import serviceImp.ExamServiceImp;

/**
 * Servlet implementation class DeleteQuestionController
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String data =new String(request.getParameter("questionId").getBytes("ISO-8859-1"),"UTF-8");
	    int questionId = Integer. valueOf(data);
	    System.out.println("questionId-->" + questionId);
		ExamServiceImp a = new ExamServiceImp();
		String result = a.deleteQuestionById(questionId);
		response.setHeader("Access-Control-Allow-Origin", "*"); 

        response.setContentType("text/json;charset=UTF-8");
        
		response.getWriter().write(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
