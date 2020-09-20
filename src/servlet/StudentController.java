package servlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import service.StudentServiceImp;
import service.SubjectServiceImp;
import service.ViewExamImp;

/**
 * Servlet implementation class SubjectController
 */
@WebServlet("/StudentController")
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String data =new String(request.getParameter("subjectId").getBytes("ISO-8859-1"),"UTF-8");
	//	String data = request.getParameter("data");
	//	System.out.println(data);
        int subjectId = Integer. valueOf(data);
	//	response.getWriter().append("Served at: ").append(request.getContextPath());
		StudentServiceImp a = new StudentServiceImp();
		String result = a.findAllStudentsBySubjectId(subjectId);
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
