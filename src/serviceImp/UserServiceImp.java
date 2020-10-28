package serviceImp;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import domain.Exam;
import domain.User;
import enumeration.Role;
import mapper.UserMapper;
import service.UserService;
import shared.UnitOfWork;
import util.JsonToObject;

public class UserServiceImp implements UserService {
	UserMapper userMapper = new UserMapper();
	private JsonToObject jo = new JsonToObject();

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
		        String s = "";
		        User user = new User();
		        // checking the tokens of student and instructor
		        if (temp.getName().equals("tokenIns")) {
		        	s = temp.getValue();
		        	user = userMapper.findById(Integer.valueOf(s));
		        	result = JSONObject.toJSONString(user);
		        	System.out.println("checklogin: " + user.getRole()+user.getUserName());
		            break;
		        }
		        
		        if(temp.getName().equals("tokenStu")) {
		        	s = temp.getValue();
		        	user = userMapper.findById(Integer.valueOf(s));
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

	@Override
	public String findAllUsers(Role role, int id) {
		List<User> userList = new ArrayList<User>();
		List<User> enrolledusers = new ArrayList<User>();
		String result = "";
		if(!role.equals(Role.ADMIN)) {
			enrolledusers = userMapper.FindEnrolledUsers(role,id);
			userList = userMapper.FindAllUsers(role, id);
			String enUsers = JSONObject.toJSONString(enrolledusers);
			String unUsers = JSONObject.toJSONString(userList);
			result = "{\"enrolled\":"+enUsers+",\"unenrolled\":"+unUsers+"}";
		}else {
			userList = userMapper.FindAllUsers(role, id);
			result = JSONObject.toJSONString(userList);
		}
		
		
		return result;
	}

	@Override
	public boolean addNewUser(HttpServletRequest request) {
		UnitOfWork.newCurrent();

		// get the new exam info from the request.
		JSONObject userJsonObject = jo.ReqJsonToObject(request);

		User user = new User();
		user = JSON.toJavaObject(userJsonObject, User.class);
		
		System.out.println(user.getId()+user.getUserName()+user.getPassWord()+user.getRole());

		UnitOfWork.getCurrent().registerNew(user);

		return UnitOfWork.getCurrent().commit();
	}

}
