package performance.lazyload;

import hibernate.HibernateUtil;

import org.hibernate.Session;

public class LazyloadDB {
	
	public void initData() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		for (int i = 0; i < 100; i++) {
			LazyloadInvoice invoice = new LazyloadInvoice();
			invoice.setPublicNumber("public_" + i);
			
			for (int j = i + 1000; j < i + 1100; j++) {
				LazyloadInvoiceLine line = new LazyloadInvoiceLine();
				line.setProductId(i + j);
				line.setPrice(10f + i);
				line.setItems(i + 5);
				line.setDiscount(i + 1);
				line.setInvoice(invoice);
				invoice.getLines().add(line);
			}
			
			session.save(invoice);
		}
		session.getTransaction().commit();
	}

}
