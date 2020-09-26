package serviceImp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;

import domain.User;
import mapper.UserMapper;
import service.UserService;

public class UserServiceImp implements UserService {
	UserMapper userMapper = new UserMapper();

	@Override
	public User login(String userName, String passWord) {
		User user = userMapper.login(userName, passWord);
		return user;
	}

	@Override
	public String checkLogin(HttpServletRequest request) {
		Cookie[] allcookie = request.getCookies();
		String result = "";
		int i = 0;
		if (allcookie != null) {
		    for (i = 0; i < allcookie.length; i++) {
		        Cookie temp = allcookie[i];
		        // checking the token
		        if (temp.getName().equals("token")) {
		        	String s = temp.getValue();
		        	User user = userMapper.findById(Integer.valueOf(s));
		        	result = JSONObject.toJSONString(user);
		        	System.out.println("checklogin: " + user.getRole()+user.getUserName());
		            break;
		        }
		    }
		    if (allcookie.length == i) {
		    	result =  "0";
		    }else {
		    	return result;
		    }
		} else {
			result = "1";
		}
		return result;
		
	}

}
