package service;

import javax.servlet.http.HttpServletRequest;

import domain.User;

public interface UserService {
	public User login(String userName, String passWord);
	
	public String checkLogin(HttpServletRequest request);
	
}
