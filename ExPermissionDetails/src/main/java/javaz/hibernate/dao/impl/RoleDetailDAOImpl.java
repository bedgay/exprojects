package javaz.hibernate.dao.impl;

import java.util.List;

import javaz.hibernate.dao.RoleDetailDAO;
import javaz.hibernate.entity.RoleDetail;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

/**
 * @author SUCCESS\tungo
 * @date Sep 20, 2014 10:41:25 AM
 */
@SuppressWarnings("unchecked")
@Repository
public class RoleDetailDAOImpl extends DAOImpl<RoleDetail, Integer> implements
		RoleDetailDAO {

	@Override
	public boolean checkSubjectIDByUserAndPermissions(Integer userId,
			String[] permissions, String subject, Integer subjectId) {
		return getSession()
				.createQuery(
						"SELECT rd.subjectId FROM RoleDetail rd JOIN rd.role.permissions p JOIN rd.role.users u WHERE u.id = :userId AND rd.subject = :subject AND rd.subjectId = :subjectId AND p.code IN(:permissions)")
				.setParameter("userId", userId)
				.setParameter("subject", subject)
				.setParameter("subjectId", subjectId)
				.setParameterList("permissions", permissions).list().size() > 0;
	}

	@Override
	public List<Integer> findSubjectIDsByUserAndPermissions(Integer userId, String subject,
			String[] permissions) {
		return getSession()
				.createQuery(
						"SELECT rd.subjectId FROM RoleDetail rd JOIN rd.role.permissions p JOIN rd.role.users u WHERE rd.subject = :subject AND u.id = :userId AND p.code IN(:permissions)")
				.setParameter("userId", userId)
				.setParameter("subject", subject)
				.setParameterList("permissions", permissions).list();
	}

	@Override
	public RoleDetail findBySubjectAndRole(String subject, Integer subjectId,
			Integer roleId) {
		List<RoleDetail> list = getSession()
				.createQuery(
						"FROM RoleDetail rd WHERE rd.subject = :subject AND rd.subjectId = :subjectId AND rd.role.id = :roleId")
				.setParameter("subject", subject)
				.setParameter("subjectId", subjectId)
				.setParameter("roleId", roleId).list();
		if (CollectionUtils.isEmpty(list)) {
			return null;
		} else {
			return list.get(0);
		}
	}

	@Override
	public boolean delBySubject(String subject, Integer subjectId) {
		return getSession().createQuery("DELETE FROM RoleDetail rd WHERE rd.subject = :subject AND rd.subjectId = :subjectId")
				.setParameter("subject", subject)
				.setParameter("subjectId", subjectId)
				.executeUpdate() > 0;
	}

}
