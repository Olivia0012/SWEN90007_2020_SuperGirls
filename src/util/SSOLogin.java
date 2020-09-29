package util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import domain.User;

public class SSOLogin {
	
	private static Map<String,User> loginList = new HashMap<String,User>();

	/**
	 * Authenticate the user
	 * 
	 * @return the login user
	 */
	public User checkLogin(HttpServletRequest request) {
		String token = request.getHeader("token");
	//	System.out.println("token: "+token);
		User user = SSOLogin.loginList.get(token);
		return user;
		
	}

	/**
	 * @param add user to the login list
	 */
	public void login(String token, User user) {
		SSOLogin.loginList.put(token, user);
		System.out.println(loginList.get(token).getUserName());
	}

	/**
	 * @param loginList the loginList to set
	 */
	public void logout(String token, User user) {
		SSOLogin.loginList.remove(token, user);
	}
	
}
