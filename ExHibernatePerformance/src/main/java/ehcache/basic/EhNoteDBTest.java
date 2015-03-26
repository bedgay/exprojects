package ehcache.basic;

import hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

public class EhNoteDBTest {
	
	public static void main(String[] args) {
//		new EhNoteDBTest().init();
		new EhNoteDBTest().test(); 
		// Result:
		//  - Do not use cach => 2980
		//  - Use cache: 
		//	  + NONSTRICT_READ_WRITE =>	485
		//	  + READ_WRITE =>	565
		//	  + READ_ONLY => 500
		//	  + TRANSACTIONAL => 487
		//	  + NONE => 2909
		//	  + Configuration cache detail => 328 !!!
	}

	public void init() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		for (int i = 0; i < 10000; i++) {
			EhNote note = new EhNote("Title_" + i, "Body_" + i);
			session.save(note);
		}

		session.getTransaction().commit();
	}

	@SuppressWarnings({ "unchecked" })
	public void test() {

		Session session = HibernateUtil.getSessionFactory().openSession();

		Query query = session.createQuery("from EhNote");
		List<EhNote> list = (List<EhNote>) query.list();
		for (EhNote note : list) {
			note.getBody();
		}
		session.close();
		
		session = HibernateUtil.getSessionFactory().openSession();
		long start = new Date().getTime();
		
		list = new ArrayList<>();
		for (int i = 1; i < 9000; i++) {
			EhNote note = (EhNote) session.get(EhNote.class, i);
			note.getBody();
			list.add(note);
		}
		
		long end = new Date().getTime();
		System.out.println("EhCache testing : " + (end - start));
	}

}
