package util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import domain.User;
import mapper.ExclusiveWriteLockManager;
import mapper.LockManager;

public class SSOLogin {

	private static Map<String, User> loginList = new HashMap<String, User>();
	LockManager lock = ExclusiveWriteLockManager.getInstance();

	/**
	 * Authenticate the user
	 * 
	 * @return the login user
	 */
	public User checkLogin(HttpServletRequest request) {
		String token = request.getHeader("token");
		System.out.println("token: " + token);
		User user = SSOLogin.loginList.get(token);
		if (user == null) {
			//release all locks held by this user
			lock.releaseAllLocks(token);
			
			SSOLogin.loginList.remove(token, user);
		}
			

		return user;

	}

	/**
	 * @param add user to the login list
	 */
	public void login(String token, User user) {
		SSOLogin.loginList.put(token, user);
	}

	/**
	 * @param loginList the loginList to set
	 */
	public void logout(String token, User user) {
		//release all locks held by this user
		lock.releaseAllLocks(token);
		
		SSOLogin.loginList.remove(token, user);
	}

}
