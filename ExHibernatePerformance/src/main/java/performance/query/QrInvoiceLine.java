package performance.query;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "qr_invoice_lines")
public class QrInvoiceLine {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private Float price;
	private Integer items;
	private Float discount;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "productID", nullable = false, referencedColumnName = "id")
	private QrProduct product;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "invoiceID", nullable = false, referencedColumnName = "id")
	private QrInvoice invoice;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Integer getItems() {
		return items;
	}

	public void setItems(Integer items) {
		this.items = items;
	}

	public Float getDiscount() {
		return discount;
	}

	public void setDiscount(Float discount) {
		this.discount = discount;
	}

	public QrProduct getProduct() {
		return product;
	}

	public void setProduct(QrProduct product) {
		this.product = product;
	}

	public QrInvoice getInvoice() {
		return invoice;
	}

	public void setInvoice(QrInvoice invoice) {
		this.invoice = invoice;
	}

}
