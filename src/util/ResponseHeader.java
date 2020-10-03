package util;

import javax.servlet.http.HttpServletResponse;

public class ResponseHeader {
	
	public HttpServletResponse setResponseHeader (HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Credentials","true");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=UTF-8");
		return response;
	}

}
