/**
 * 
 */
package service;


import enumeration.Role;

/**
 * @author manlo
 *
 */
public interface SubjectService {
	public String findAllSubjectsByUserId(int userId, Role role);

}
