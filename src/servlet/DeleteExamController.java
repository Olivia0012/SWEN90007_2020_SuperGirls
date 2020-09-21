package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ExamServiceImp;

/**
 * Servlet implementation class DeleteExamController
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String data =new String(request.getParameter("examId").getBytes("ISO-8859-1"),"UTF-8");
	    int examId = Integer. valueOf(data);
	    System.out.println("examId-->" + examId);
		ExamServiceImp a = new ExamServiceImp();
		String result = a.deleteExamById(examId);
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
