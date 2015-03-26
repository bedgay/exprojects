package ehcache.querycache;

import hibernate.HibernateUtil;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;

public class EhLocationDBTest {
	
	public static void main(String[] args) {
//		new EhLocationDBTest().init();
		new EhLocationDBTest().criteriaTest(); // => 731 / 157
//		new EhLocationDBTest().queryTest(); // => 792 / 128
	}
	
	@SuppressWarnings("unused")
	private void init() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		for (int i = 0; i < 10000; i++) {
			session.save(new EhLocation("N_" + i, "P_" + i, "D_" + i));
		}
		
		session.getTransaction().commit();
	}
	
	@SuppressWarnings("unchecked")
	private void criteriaTest() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		long start = new Date().getTime();
		
		Criteria cri = session.createCriteria(EhLocation.class).setCacheRegion("CRITERIA_CACHE").setCacheable(Boolean.TRUE);
		List<EhLocation> list = cri.list();
		for (EhLocation loc : list) {
			loc.getDetail();
		}

		long end = new Date().getTime();
		System.out.println("Before caching : " + (end - start));
		
		session.getTransaction().commit();
		session.close();
		
		Session session2nd = HibernateUtil.getSessionFactory().openSession();
		start = new Date().getTime();
		
		cri = session2nd.createCriteria(EhLocation.class);
		list = cri.list();
		for (EhLocation loc : list) {
			loc.getDetail();
		}

		end = new Date().getTime();
		System.out.println("After caching : " + (end - start));
		session2nd.close();
	}

	@SuppressWarnings({ "unchecked", "unused" })
	private void queryTest() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		long start = new Date().getTime();

		Query query = session.createQuery("from EhLocation").setCacheRegion("QUERY_CACHE").setCacheable(Boolean.TRUE);
		List<EhLocation> list = query.list();
		for (EhLocation loc : list) {
			loc.getDetail();
		}

		long end = new Date().getTime();
		System.out.println("Before caching : " + (end - start));
		session.close();

		Session session2nd = HibernateUtil.getSessionFactory().openSession();
		start = new Date().getTime();
		
		query = session2nd.createQuery("from EhLocation");
		list = query.list();
		for (EhLocation loc : list) {
			loc.getDetail();
		}

		end = new Date().getTime();
		System.out.println("After caching : " + (end - start));
		session2nd.close();
	}

}
