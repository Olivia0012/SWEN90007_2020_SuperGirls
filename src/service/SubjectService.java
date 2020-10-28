/**
 * 
 */
package service;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import domain.Subject;
import domain.User;
import enumeration.Role;

/**
 * @author manlo
 *
 */
public interface SubjectService {
	public String findAllSubjectsByUserId(int userId, Role role);
	public String findAllSubjects();
	public String findAllUsersBySubjectId(int subjectId);
	
	public boolean usersInSubject(int subjectId, List<User> users,String role);
	
	boolean addSubject(HttpServletRequest request);

}
