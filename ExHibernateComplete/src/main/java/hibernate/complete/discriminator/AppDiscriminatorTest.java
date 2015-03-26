package hibernate.complete.discriminator;

import hibernate.complete.HibernateUtil;

import org.hibernate.Session;

public class AppDiscriminatorTest {
	
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		NewsCategory news = new NewsCategory();
		news.setName("NewsCategory");
		ProductCategory pro = new ProductCategory();
		pro.setName("ProductCategory");

		session.save(news);
		session.save(pro);
		session.getTransaction().commit();
	}


}
