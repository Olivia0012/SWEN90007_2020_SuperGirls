package servlet;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import domain.Exam;
import mapper.ExamMapper;
import service.ViewExamImp;

import java.io.BufferedReader;

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
		String data =new String(request.getParameter("examId").getBytes("ISO-8859-1"),"UTF-8");
	    int examId = Integer. valueOf(data);
		
		ViewExamImp a = new ViewExamImp();
		String result = a.findExamById(examId);
		response.setHeader("Access-Control-Allow-Origin", "*");  
        response.setContentType("text/json;charset=UTF-8");
		response.getWriter().write(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request+"---");
		String param= null; 
		try {
			BufferedReader streamReader = new BufferedReader( new InputStreamReader(request.getInputStream(), "UTF-8"));
			StringBuilder responseStrBuilder = new StringBuilder();
			String inputStr;
			while ((inputStr = streamReader.readLine()) != null)
				responseStrBuilder.append(inputStr);
			
			JSONObject jsonObject = JSONObject.parseObject(responseStrBuilder.toString());
			param= jsonObject.toJSONString();
			System.out.println(param+"=====");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	//	String data =new String(request.getParameter("examId").getBytes("ISO-8859-1"),"UTF-8");
	  //  int examId = Integer. valueOf(data);
		
		ViewExamImp a = new ViewExamImp();
		String result = a.findExamById(1);
		response.setHeader("Access-Control-Allow-Origin", "*");  
        response.setContentType("text/json;charset=UTF-8");
		response.getWriter().write(result);
		
	}
	
	public String readJSONString(HttpServletRequest request){
		String param= null; 
		try {
			BufferedReader streamReader = new BufferedReader( new InputStreamReader(request.getInputStream(), "UTF-8"));
			StringBuilder responseStrBuilder = new StringBuilder();
			String inputStr;
			while ((inputStr = streamReader.readLine()) != null)
				responseStrBuilder.append(inputStr);
			
			JSONObject jsonObject = JSONObject.parseObject(responseStrBuilder.toString());
			param= jsonObject.toJSONString();
			System.out.println(param);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return param;

		}
	
	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
