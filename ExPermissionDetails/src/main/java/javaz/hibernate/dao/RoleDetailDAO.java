package javaz.hibernate.dao;

import java.util.List;

import javaz.hibernate.entity.RoleDetail;

/**
 * @author SUCCESS\tungo
 * @date Sep 20, 2014 10:39:49 AM
 */
public interface RoleDetailDAO extends DAO<RoleDetail, Integer> {
	
	/**
	 * @param userId
	 * @param permissions
	 * @param subject
	 * @param subjectId
	 * @return
	 */
	boolean checkSubjectIDByUserAndPermissions(Integer userId, String[] permissions, String subject, Integer subjectId);
	
	/**
	 * @param userId
	 * @param subject
	 * @param permissions
	 * @return
	 */
	List<Integer> findSubjectIDsByUserAndPermissions(Integer userId, String subject,
			String[] permissions);
	
	/**
	 * @param subject
	 * @param subjectId
	 * @param roleId
	 * @return
	 */
	RoleDetail findBySubjectAndRole(String subject, Integer subjectId, Integer roleId);
	
	/**
	 * @param subject
	 * @param subjectId
	 * @return
	 */
	boolean delBySubject(String subject, Integer subjectId);
			
}
