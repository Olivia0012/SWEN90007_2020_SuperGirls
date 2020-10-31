package domain;

import enumeration.Role;

public class User extends DomainObject{
	private String userName;
	private String passWord;
	private Role role;
	
	
	public User(int id, String userName, String passWord, Role role) {
		super(id);
		this.setUserName(userName);
		this.setPassWord(passWord);
		this.setRole(role);
	}
	
	
	public User() {
		super();
	}


	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the passWord
	 */
	public String getPassWord() {
		return passWord;
	}
	/**
	 * @param passWord the passWord to set
	 */
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	/**
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}



}
