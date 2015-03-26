package performance.lazyload;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "lazyload_invoice_line")
public class LazyloadInvoiceLine {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private Integer productId;
	private Float price;
	private Integer items;
	private Integer discount;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "invoiceID", nullable = false, referencedColumnName = "id")
	private LazyloadInvoice invoice;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
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

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public LazyloadInvoice getInvoice() {
		return invoice;
	}

	public void setInvoice(LazyloadInvoice invoice) {
		this.invoice = invoice;
	}

}
