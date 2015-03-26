package performance.query;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQueries(@NamedNativeQuery(name = "customerDetail", query = "select c.name as cusName, p.name as proName, i.id as invoiceId, l.id as lineId from qr_customer c, qr_invoices i, qr_invoice_lines l, qr_product p where l.productID = p.id and l.invoiceID = i.id and i.customerID = c.id and i.customerID = :cus", resultClass=QrDetail.class))
public class QrDetail {
	
	public static String NAME = "customerDetail";

	@EmbeddedId
	private QrDetailPK id;

	private String cusName;
	private String proName;

	public QrDetailPK getId() {
		return id;
	}

	public void setId(QrDetailPK id) {
		this.id = id;
	}

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

}
