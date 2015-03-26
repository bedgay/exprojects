package performance.filter;

import hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

@SuppressWarnings({"unused", "unchecked"})
public class FtDBTest {
	public static void main(String[] args) {
		//init();
//		normalTest(); // 5277
		performanceTest(); // 2968

	}

	private static void normalTest() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		long start = new Date().getTime();
		
		FtCategory cat = (FtCategory) session.load(FtCategory.class, 1);
		List<FtProduct> list = session.createFilter(cat.getProducts(), "").list();
		for (FtProduct pro : list){
			pro.getName();
		}
		
		long end = new Date().getTime();
		System.out.println("Normal : " + (end - start));
		session.close();
	}
	
	private static void performanceTest() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		long start = new Date().getTime();
		
		FtCategory cat = (FtCategory) session.load(FtCategory.class, 1);
		List<FtProduct> list = session.createFilter(cat.getProducts(), "where name LIKE '%9%'").list();
		for (FtProduct pro : list){
			pro.getName();
		}
		
		long end = new Date().getTime();
		System.out.println("Normal : " + (end - start));
		session.close();
	}

	private static void init() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		for (int i = 1; i < 10; i++) {
			FtCategory cat = new FtCategory("Category_" + i);
			List<FtProduct> list = new ArrayList<>();
			for (int j = 0; j < 50000; j++) {
				FtProduct pro = new FtProduct("Product_" + j);
				pro.setCategory(cat);
				list.add(pro);
			}
			cat.setProducts(list);
			session.save(cat);
		}
		session.getTransaction().commit();
		session.close();
	}
}
