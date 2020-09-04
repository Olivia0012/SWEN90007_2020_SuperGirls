/**
 * 
 */
package domain;

import enumeration.Role;

/**
 * @author manlo
 *
 */
public class Admin extends User{

	public Admin(int uId, String userName, String passWord, Role role) {
		super(uId, userName, passWord, Role.ADMIN);
		// TODO Auto-generated constructor stub
	}

	

}
