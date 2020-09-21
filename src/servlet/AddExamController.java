package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import domain.Exam;
import mapper.ExamMapper;
import service.ExamServiceImp;

/**
 * Servlet implementation class AddExamController
 */
@WebServlet("/AddExamController")
public class AddExamController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddExamController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// read params from post request
				BufferedReader reader = request.getReader();
			    StringBuilder sb = new StringBuilder();
			    String line = reader.readLine();
			    while (line != null) {
			      sb.append(line + "\n");
			      line = reader.readLine();
			    }
			    reader.close();
			    String params = sb.toString();
			    String[] _params = params.split("&");
			    Exam exam = new Exam();
			    for (String param : _params) {
			      System.out.println("add exam : params(POST)-->" + param);
			      JSONObject jsonObject = JSONObject.parseObject(param);
			      exam = JSON.toJavaObject(jsonObject,Exam.class);
			    }
			    ExamServiceImp addExam = new ExamServiceImp();
			    addExam.addNewExam(exam);
			   
			    
			    String result = JSONObject.toJSONString(exam);
				response.setHeader("Access-Control-Allow-Origin", "*");  
		        response.setContentType("text/json;charset=UTF-8");
				response.getWriter().write(result);
	}

}
