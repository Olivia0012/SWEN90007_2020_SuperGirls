/**
 * 
 */
package database;

/**
 * @author manlo
 *	This class defines all SQL Statements.
 */

class SQLStatement {
	
	// User Login Verification
	private String loginVerificationStatement = "SELECT * FROM User WHERE userName = (?) and passWord = (?)";

}
