package util;

import javax.servlet.http.HttpServletResponse;

public class ResponseHeader {
	
	public HttpServletResponse setResponseHeader (HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Credentials","true");
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:8001");
		response.setContentType("text/json;charset=UTF-8");
		return response;
	}

}
