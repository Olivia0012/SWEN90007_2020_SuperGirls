/**
 * 
 */
package mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import database.DatabaseConnection;
import domain.DomainObject;
import domain.Subject;
import domain.User;
import enumeration.ExamStatus;
import enumeration.Role;
import shared.IdentityMap;

/**
 * @author manlo
 *
 */
public class UserMapper extends DataMapper{
	private String queryAllUser = "select * from users"; // query all users
	private String addNewSubjectStm = "INSERT INTO USERS (USERNAME,PASSWORD,ROLE) "
			+ "VALUES (?,?,?);";// insert new subject 1 into subject table
	private String findUserbyIdStm = "select * from users where userid = ?";

	/**
	 * 
	 */
	public UserMapper() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Boolean insert(DomainObject obj) {
		User newUser = (User) obj;
		PreparedStatement stmt = DatabaseConnection.prepare(addNewSubjectStm);
		
		try {
			stmt.setString(1, newUser.getUserName());
			stmt.setString(2, newUser.getPassWord());
			stmt.setInt(3, 1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

	@Override
	protected Boolean update(DomainObject obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Boolean delete(DomainObject obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected User findById(int userId) {

		// find the subject in the identity map.
		User user = new User();
		IdentityMap<User> userMap = IdentityMap.getInstance(user);
		user = userMap.get(userId);

		// find from the DB when it is not in the identity map.
		if (user == null) {
			List<User> result = new ArrayList<User>();

			try {
				PreparedStatement stmt = DatabaseConnection.prepare(findUserbyIdStm);
				stmt.setInt(1, userId);
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					Integer id = rs.getInt(1);
					String userName = rs.getString(2);
					String passWord = rs.getString(3);
					String role = rs.getString(4);

					user = new User(id, userName, passWord,Role.valueOf(role));
					result.add(user);
				}
				rs.close();
				stmt.close();

			} catch (Exception e) {
				e.printStackTrace();
				return null;
			} finally {
				DatabaseConnection.closeConnection();
			}
			if (result.size() > 0) {
				System.out.println(result.get(0).getUserName());
				return result.get(0);
				
			}
		}
		
		return user;
	}


	protected void FindAllInstructor() {
		PreparedStatement stmt = DatabaseConnection.prepare(queryAllUser);

		List<User> result = new ArrayList<User>();
		ResultSet rs;
		try {
			rs = stmt.executeQuery();
			User instructor = new User();

			while (rs.next()) {
				
				Integer id = rs.getInt("userid");
				String userName = rs.getString("userName");
				String password = rs.getString("password");
				String role = rs.getString("role");
				
				
				instructor = new User(id, userName, password,Role.INSTRUCTOR);
				result.add(instructor);
			}

			for (int i = 0; i < result.size(); i++) {
				System.out.println(
						result.get(i).getId() + "," + result.get(i).getUserName() + "," + result.get(i).getPassWord()+ "," + result.get(i).getRole());
			}

			rs.close();

			stmt.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DatabaseConnection.closeConnection();
		}
	}
	
	public static void main(String args[]) {
		UserMapper sm = new UserMapper();
		User newUser = new User();
		newUser.setUserName("Olivia");
		newUser.setPassWord("123");
	//	newUser.setRole(role);
	//	sm.insert(newUser);
		sm.FindAllInstructor();
		sm.findById(1);

	}


}
