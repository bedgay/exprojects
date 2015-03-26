package performance.query;

import hibernate.HibernateUtil;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

public class QrDBTest {

	public static void main(String[] args) {
//		new QrDBTest().init();
//		new QrDBTest().checkByNormal(); // 933
		new QrDBTest().checkByQuery(); // 723
	}

	public void init() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		for (int i = 0; i < 1000; i++) {
			QrProduct product = new QrProduct();
			product.setName("Pro_" + i);
			product.setPrice(1f + i);

			QrCustomer cus = new QrCustomer();
			cus.setName("Cus_" + i);
			cus.setAddress("Address_" + i);
			cus.setEmail("Email_" + i);
			cus.setPhone("Phone_" + i);

			QrInvoice invoice = new QrInvoice();
			invoice.setPublicNumber("publicNumber_" + i);
			invoice.setCustomer(cus);

			for (int j = 0; j < 10; j++) {
				QrInvoiceLine line = new QrInvoiceLine();
				line.setDiscount(1f + j);
				line.setPrice(5f + j);
				line.setProduct(product);
				line.setInvoice(invoice);
				line.setItems(1 + j);
				invoice.getLine().add(line);
			}
			session.save(cus);
			session.save(product);
			session.save(invoice);
		}

		session.getTransaction().commit();
	}
	
	@SuppressWarnings("unchecked")
	public void checkByNormal() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		long start = new Date().getTime();
		
		Query query = session.createQuery("from QrCustomer as c where c.id = 1");
		List<QrCustomer> cuss = query.list();
		
		for (QrCustomer cus : cuss) {
			for (QrInvoice inv : cus.getInvoices()) {
				for (QrInvoiceLine line : inv.getLine()) {
					System.out.println(line.getProduct().getName());
				}
			}
		}

		long end = new Date().getTime();
		System.out.println("Normal : " + (end - start));
		
	}
	
	@SuppressWarnings("unchecked")
	public void checkByQuery() {

		Session session = HibernateUtil.getSessionFactory().openSession();

		long start = new Date().getTime();
		
		Query query = session.getNamedQuery(QrDetail.NAME);
		query.setParameter("cus", 1);
		List<QrDetail> list = (List<QrDetail>) query.list();
		
		for (QrDetail detail : list) {
			System.out.println(detail.getProName());
		}

		long end = new Date().getTime();
		System.out.println("Name query : " + (end - start));
	}

}
