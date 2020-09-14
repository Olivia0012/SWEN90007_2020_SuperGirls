package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Exam;
import mapper.ExamMapper;
import service.ViewExamImp;

/**
 * Servlet implementation class ViewExamController
 */
@WebServlet("/ExamController")
public class ExamController extends HttpServlet {
	private static final long serialVersionUID = 102831973239L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExamController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ViewExamImp a = new ViewExamImp();
		String result = a.findAllExams();
		System.out.print(request + "   "+ response);
		response.setHeader("Access-Control-Allow-Origin", "*");  
        response.setContentType("text/json;charset=UTF-8");
		response.getWriter().write(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		System.out.println("post");
		response.setHeader("Access-Control-Allow-Origin", "*");  
        response.setContentType("text/json;charset=UTF-8");
		response.getWriter().write("testing");
		System.out.print(request + "   "+ response);
		
	}
}
