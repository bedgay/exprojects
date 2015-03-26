package hibernate.complete.tablegenerator;

import hibernate.complete.HibernateUtil;

import org.hibernate.Session;

public class AppTableGeneratorTest {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		Invoice inv = new Invoice();
		
		session.save(inv);
		session.getTransaction().commit();
	}
	
}
