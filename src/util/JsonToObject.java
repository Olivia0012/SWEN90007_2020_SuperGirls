package util;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;


public class JsonToObject {

	public JSONObject ReqJsonToObject(HttpServletRequest request) {
		JSONObject jsonObject = null;
		BufferedReader reader;
		try {
			reader = request.getReader();
			StringBuilder sb = new StringBuilder();
			String line = reader.readLine();
			while (line != null) {
				sb.append(line + "\n");
				line = reader.readLine();
			}

			reader.close();
			String params = sb.toString();
			String[] _params = params.split("&");
			for (String param : _params) {
				System.out.println("add exam : params(POST)-->" + param);
				jsonObject = JSONObject.parseObject(param);
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jsonObject;
	}
	
	public String ReqJsonToString(HttpServletRequest request) {
		String result = null;
		BufferedReader reader;
		try {
			reader = request.getReader();
			StringBuilder sb = new StringBuilder();
			String line = reader.readLine();
			while (line != null) {
				sb.append(line + "\n");
				line = reader.readLine();
			}

			reader.close();
			String params = sb.toString();
			String[] _params = params.split("&");
			for (String param : _params) {
				System.out.println("add exam : params(POST)-->" + param);
				result = param;
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
	
	

}
