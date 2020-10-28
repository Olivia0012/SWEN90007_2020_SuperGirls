/**
 *  @author Lu Wang
 *
 * */
package mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import database.DatabaseConnection;
import database.QueryExecutor;
import domain.DomainObject;
import domain.Exam;
import domain.Student;
import domain.Subject;
import domain.Submission;
import domain.User;
import enumeration.ExamStatus;
import enumeration.Role;

/**
 * This class is for managing user table.
 *
 */
public class UserMapper extends DataMapper {

	/**
	 * Insert a new user record into users table
	 * 
	 * @param obj = user to be inserted
	 * @return indication whether the insert is successful or not
	 */
	@Override
	public int insert(DomainObject obj) {
		User newUser = (User) obj;
		String addNewUserStm = "INSERT INTO USERS (USERNAME,PASSWORD,ROLE) " + "VALUES (?,?,?)";// insert new subject 1
																								// into subject table

		try {
			PreparedStatement stmt = DatabaseConnection.prepareInsert(addNewUserStm);
			stmt.setString(1, newUser.getUserName());
			stmt.setString(2, newUser.getPassWord());
			stmt.setString(3, newUser.getRole() + "");

			stmt.executeUpdate();
			ResultSet keys = stmt.getGeneratedKeys();
			keys.next();
			int id = keys.getInt(1);

			newUser.setId(id);

	//		IdentityMap1<User> userMap = IdentityMap1.getInstance(newUser);
	//		userMap.put(newUser.getId(), newUser);
			keys.close();
			stmt.close();
			return id;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		} finally {
	//		DatabaseConnection.closeConnection();
		}
	}

	/**
	 * Update a user record in the user table
	 * 
	 * @param obj = user to be updated
	 * @return indication whether the update is successful or not
	 */
	@Override
	public Boolean update(DomainObject obj) {
		User user = (User) obj;

		String updateUserStm = "UPDATE users SET userName = ?, password = ? WHERE subjectid = ?";

		try {
			PreparedStatement stmt = DatabaseConnection.prepare(updateUserStm);
			stmt.setString(1, user.getUserName());
			stmt.setString(2, user.getPassWord());
			stmt.executeUpdate();

		//	IdentityMap1<User> userMap = IdentityMap1.getInstance(user);

			// add the updated subject into subject identity map if it is not there.
	//		userMap.put(user.getId(), user);

			stmt.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
		//	DatabaseConnection.closeConnection();
		}
	}

	/**
	 * Delete a user record in the user table
	 * 
	 * @param obj = user to be deleted
	 * @return indication whether the delete is successful or not
	 */
	@Override
	public Boolean delete(DomainObject obj) {
		User user = (User) obj;

		String deleteUserStm = "DELETE FROM users WHERE userid = ?";

		try {
			PreparedStatement stmt = DatabaseConnection.prepare(deleteUserStm);
			stmt.setInt(1, user.getId());
			stmt.executeUpdate();

		/*	IdentityMap1<User> userMap = IdentityMap1.getInstance(user);
			User userInMap = userMap.get(user.getId());
			if (userInMap != null) {
				userMap.put(user.getId(), null);
			}*/

			stmt.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
		//	DatabaseConnection.closeConnection();
		}
	}

	/**
	 * find a user record in the user table
	 * 
	 * @param userId = target user id
	 * @return user with the id.
	 */
	@Override
	public User findById(int userId) {

		// find the subject in the identity map.
		User user = new User();

	//	IdentityMap1<User> userMap = IdentityMap1.getInstance(user);
	//	user = userMap.get(userId);

		// find from the DB when it is not in the identity map.
	//	if (user == null) {
			List<User> result = new ArrayList<User>();
			// query a subject by subjectId
			String findSubjectbyIdStm = "SELECT * FROM users WHERE userid = ?";

			try {
				PreparedStatement stmt = DatabaseConnection.prepare(findSubjectbyIdStm);
				stmt.setInt(1, userId);
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					Integer id = rs.getInt(1);
					String userName = rs.getString(2);
					String password = rs.getString(3);
					String role = rs.getString(4);

					user = new User(id, userName, password, Role.valueOf(role));
					result.add(user);
				}
				rs.close();
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
			//	DatabaseConnection.closeConnection();
			}
		/*	if (result.size() > 0) {
				userMap.put(result.get(0).getId(), result.get(0));
				return result.get(0);
			}*/
	//	}
		return user;
	}

	/**
	 * find all subjects record in the subject table
	 * 
	 *
	 * @return all the subject records.
	 */
	public List<User> FindAllUsers(Role type, int subjectId) {
		String queryAllUser = ""; 
		if(type != Role.ADMIN && subjectId != -1)
			queryAllUser = "SELECT * FROM users WHERE userid NOT IN (SELECT userid FROM userandsubject WHERE subjectId = "+subjectId+") AND role = '"+type+"'";
		else queryAllUser = "SELECT * FROM users";
			
		User user = new User();
		// query all subjects

//		IdentityMap1<User> userMap = IdentityMap1.getInstance(user);
		List<User> result = new ArrayList<User>();

		try {
			PreparedStatement stmt = DatabaseConnection.prepare(queryAllUser);

			ResultSet rs = stmt.executeQuery();
			// Subject subject = new Subject();

			while (rs.next()) {
				Integer id = rs.getInt(1);
				String userName = rs.getString(2);
				String password = rs.getString(3);
				String role = rs.getString(4);

				user = new User(id, userName, password, Role.valueOf(role));
				result.add(user);
			}

	/*		if (result.size() > 0) {
				for (int i = 0; i < result.size(); i++) {
					User s = userMap.get(result.get(i).getId());
					if (s == null) {
						userMap.put(result.get(i).getId(), result.get(i));
					}
					System.out.println(
							result.get(i).getId() + "," + result.get(i).getUserName() + "," + result.get(i).getRole());
				}

			}*/
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
		//	DatabaseConnection.closeConnection();
		}
		return result;
	}
	
	
	public List<User> FindEnrolledUsers(Role type, int subjectId) {
		String queryAllUser = ""; 
		queryAllUser = "SELECT * FROM users WHERE userid IN (SELECT userid FROM userandsubject WHERE subjectId = "+subjectId+") AND role = '"+type+"'";
			
		User user = new User();
		// query all subjects

//		IdentityMap1<User> userMap = IdentityMap1.getInstance(user);
		List<User> result = new ArrayList<User>();

		try {
			PreparedStatement stmt = DatabaseConnection.prepare(queryAllUser);

			ResultSet rs = stmt.executeQuery();
			// Subject subject = new Subject();

			while (rs.next()) {
				Integer id = rs.getInt(1);
				String userName = rs.getString(2);
				String password = rs.getString(3);
				String role = rs.getString(4);

				user = new User(id, userName, password, Role.valueOf(role));
				result.add(user);
			}

		/*	if (result.size() > 0) {
				for (int i = 0; i < result.size(); i++) {
					User s = userMap.get(result.get(i).getId());
					if (s == null) {
						userMap.put(result.get(i).getId(), result.get(i));
					}
					System.out.println(
							result.get(i).getId() + "," + result.get(i).getUserName() + "," + result.get(i).getRole());
				}

			}*/
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
		//	DatabaseConnection.closeConnection();
		}
		return result;
	}
	

	public static void main(String args[]) {
		UserMapper sm = new UserMapper();
		User newUser = new User();
		newUser.setUserName("Amanda");
		newUser.setPassWord("111");
		newUser.setRole(Role.STUDENT);
		// newUser.setRole(role);
		sm.insert(newUser);
	//	sm.FindAllUsers(Role.INSTRUCTOR,1);
	//	sm.findById(1);

	}

	public User login(String userName, String passWord) {
		User user = new User();
	//	IdentityMap1<User> userMap = IdentityMap1.getInstance(user);

		// find from the DB when it is not in the identity map.

		List<User> result = new ArrayList<User>();
		// query a subject by subjectId
		String login = "SELECT * FROM users WHERE username = ? and password = ? ";

		try {
			PreparedStatement stmt = DatabaseConnection.prepare(login);
			stmt.setString(1, userName);
			stmt.setString(2, passWord);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Integer id = rs.getInt(1);
				String uName = rs.getString(2);
				String password = rs.getString(3);
				String role = rs.getString(4);

				user = new User(id, userName, password, Role.valueOf(role));
				result.add(user);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		//	DatabaseConnection.closeConnection();
		}

		if (result.size() > 0) {
			return result.get(0);
		} else
			return null;

	}

	/**
	 * finding all students who enrolled in a subject.
	 * 
	 * @param student Id
	 *
	 * @return all the enrolled students records.
	 */
	public List<Student> FindAllStudentsBySubjectId(int subjectid) {
		User user = new User();
		Student student = new Student();
		UserMapper userMapper = new UserMapper();
		List<Student> result = new ArrayList<Student>();
		SubjectMapper subjectMapper = new SubjectMapper();
		SubmissionMapper submMapper = new SubmissionMapper();
		ExamMapper examMapper = new ExamMapper();
		List<Exam> examList = examMapper.FindAllExamsBySubjectId(subjectid, Role.STUDENT);

		// query all users' id by subject Id in the subject and user relation table.
		String queryAllUser = "SELECT * FROM userandsubject WHERE subjectid=?";

		try {
			PreparedStatement stmt = DatabaseConnection.prepare(queryAllUser);
			stmt.setInt(1, subjectid);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Integer id = rs.getInt("userid");
				// find user info.
				User userResult = userMapper.findById(id);
				// Only students are wanted.
				if (userResult.getRole() == Role.STUDENT) {
					user = new User(id, userResult.getUserName(), null, userResult.getRole());
					List<Submission> submissions = new ArrayList<Submission>();

					// finding all submissions
					for (Exam exam : examList) {
						submissions.add(submMapper.FindSubmissionsByUserId_ExamId(id, exam.getId()));
					}

					student = new Student(user, submissions);
					result.add(student);
				}
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
		//	DatabaseConnection.closeConnection();
		}
		return result;
	}

	public List<Student> findAllStudentsAndSubmissions(int subjectId, int examId) {
		User user = new User();
		Student student = new Student();
		UserMapper userMapper = new UserMapper();
		List<Student> result = new ArrayList<Student>();
		SubjectMapper subjectMapper = new SubjectMapper();
		SubmissionMapper submMapper = new SubmissionMapper();
		ExamMapper examMapper = new ExamMapper();
		Exam exam = examMapper.findById(examId);

		// query all users' id by subject Id in the subject and user relation table.
		String queryAllUser = "SELECT * FROM userandsubject WHERE subjectid=?";

		try {
			PreparedStatement stmt = DatabaseConnection.prepare(queryAllUser);
			stmt.setInt(1, subjectId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Integer id = rs.getInt("userid");
				// find user info.
				User userResult = userMapper.findById(id);
				// Only students are wanted.
				if (userResult.getRole() == Role.STUDENT) {
					user = new User(id, userResult.getUserName(), null, userResult.getRole());
					List<Submission> submissions = new ArrayList<Submission>();

					// finding all submissions
					submissions.add(submMapper.FindSubmissionsByUserId_ExamId(id, exam.getId()));

					student = new Student(user, submissions);
					result.add(student);
				}
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
		//	DatabaseConnection.closeConnection();
		}
		return result;
	}

}
