package performance.lazyload;

import hibernate.HibernateUtil;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.annotations.BatchSize;
import org.hibernate.criterion.DetachedCriteria;

@SuppressWarnings("unused")
public class LazyloadDBTest {
	
	public static void main(String[] args) {
//		new LazyloadDB().initData();

		Session session = HibernateUtil.getSessionFactory().openSession();
		long start = new Date().getTime();
		
		Criteria criteria = session.createCriteria(LazyloadInvoice.class);
//		criteria.setFetchMode("lines", FetchMode.EAGER);
//		criteria.setResultTransformer(DetachedCriteria.DISTINCT_ROOT_ENTITY);
				
		@SuppressWarnings("unchecked")
		List<LazyloadInvoice> invoices = criteria.list();
		
		long invoiceCount = 0l;
		long invLineCount = 0l;
		for (LazyloadInvoice invoice : invoices) {
			invoiceCount++; 
			for (LazyloadInvoiceLine line : invoice.getLines()) {
				invLineCount++;
			}
		}
		long end = new Date().getTime();
		
//		System.out.println("Count : " + invoiceCount);
//		System.out.println("List.size : " + invLineCount);
		System.out.println("Lazy load - normal test : " + (end - start));
		
		/**
		 * Test normal : 2412
		 * Test with [LazyloadInvoice.@Fetch(FetchMode.SUBSELECT)] : 1936
		 * Test with [LazyloadInvoice.@BatchSize(size = 50)] : 2150
		 */
	}
	
}
