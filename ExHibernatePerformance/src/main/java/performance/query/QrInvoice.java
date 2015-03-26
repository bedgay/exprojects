package performance.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "qr_invoices")
public class QrInvoice {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String publicNumber;
	private Date createDate = new Date();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customerID", nullable = false, referencedColumnName = "id")
	private QrCustomer customer;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "invoice")
	private List<QrInvoiceLine> line = new ArrayList<>();
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPublicNumber() {
		return publicNumber;
	}

	public void setPublicNumber(String publicNumber) {
		this.publicNumber = publicNumber;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public QrCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(QrCustomer customer) {
		this.customer = customer;
	}

	public List<QrInvoiceLine> getLine() {
		return line;
	}

	public void setLine(List<QrInvoiceLine> line) {
		this.line = line;
	}

}
