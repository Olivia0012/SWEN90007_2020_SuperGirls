package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LogoutController
 */
@WebServlet("/LogoutController")
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String val = "";
		try {
			response.setContentType("text/html;charset=gbk");
            PrintWriter pw = response.getWriter();
            //从客户端获取cookie信息
            Cookie[] allcookie = request.getCookies();
            int i = 0;
            //如果cookie不为空。。。
            if (allcookie != null) {
                //从中取出cookie
                for (i = 0; i < allcookie.length; i++) {
                    //依次取出
                    Cookie temp = allcookie[i];
                    temp.setMaxAge(0); //另有效时间为0则系统会自动删除过期的cookie
            	    response.addCookie(temp);
                    //判断一下
                    if (temp.getName().equals("token")) {
                    //    val = temp.getValue();
                        temp.setMaxAge(0); //另有效时间为0则系统会自动删除过期的cookie
                 	    response.addCookie(temp);
                        pw.println("true");
                        break;
                    }
                }
                if (allcookie.length == i) {
                    pw.println("Session has been expired.");
                }
            } else {
                pw.println("Session has been expired or not existed.");
            }

        } catch(IOException e) {

            e.printStackTrace();
        }
    
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
