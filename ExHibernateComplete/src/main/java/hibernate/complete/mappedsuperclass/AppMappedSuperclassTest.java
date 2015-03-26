package hibernate.complete.mappedsuperclass;

import hibernate.complete.HibernateUtil;

import org.hibernate.Session;

public class AppMappedSuperclassTest {
	
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		Customer cus = new Customer();
		cus.setAddress("4 Phan Thuc Duyen");
		cus.setName("Mapped Super Class");
		
		session.save(cus);
		session.getTransaction().commit();
	}

}
