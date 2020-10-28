package service;

import javax.servlet.http.HttpServletRequest;

import domain.User;
import enumeration.Role;

public interface UserService {
	public User login(String userName, String passWord);
	
	public String checkLogin(HttpServletRequest request);
	
	public String findAllUsers(Role role, int id);


	boolean addNewUser(HttpServletRequest request);
	
}
