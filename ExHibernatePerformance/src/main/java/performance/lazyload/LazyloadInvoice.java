package performance.lazyload;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;
import org.springframework.context.annotation.Lazy;

@Entity
@Table(name = "lazyload_invoice")
public class LazyloadInvoice {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String publicNumber;
	private Date createDate = new Date();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "invoice")
//	@Fetch(FetchMode.SUBSELECT)
//	@BatchSize(size = 50)
	private List<LazyloadInvoiceLine> lines = new ArrayList<>();

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

	public List<LazyloadInvoiceLine> getLines() {
		return lines;
	}

	public void setLines(List<LazyloadInvoiceLine> lines) {
		this.lines = lines;
	}

}
