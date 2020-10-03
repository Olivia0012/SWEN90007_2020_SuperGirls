package util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

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
		System.out.println("token: "+token);
		User user = SSOLogin.loginList.get(token);
		if(user !=null)
		 System.out.println("check key:"+token +" username:"+user.getUserName());
		return user;
		
	}

	/**
	 * @param add user to the login list
	 */
	public void login(String token, User user) {
		
		Iterator<Entry<String, User>> users = SSOLogin.loginList.entrySet().iterator(); 
		while (users.hasNext()) { 
		  Map.Entry<String, User> entry = (Map.Entry<String, User>) users.next(); 
		 
		  String key = (String)entry.getKey(); 
		  User userInList = (User)entry.getValue(); 
		  // delete the previous login user
		  if(userInList.getId() == user.getId()) {
			  System.out.println("login key:"+key);
			//  SSOLogin.loginList.remove(key);
			 
			  break;
		  }	  
		}
		
		SSOLogin.loginList.put(token, user);
		 System.out.println("login key:"+SSOLogin.loginList.get(token).getUserName() +" token:"+token);
		
	}

	/**
	 * @param loginList the loginList to set
	 */
	public void logout(String token, User user) {
		 System.out.println("logout key:"+token +" username:"+user.getUserName());
		SSOLogin.loginList.remove(token, user);
	}
	
}
