package hibernate.complete.tablegenerator;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@TableGenerator(name = "invoice_GEN", 
	table = "db_seqs", 
	pkColumnName = "name", 
	valueColumnName = "next_id", 
	pkColumnValue = "invoice", 
	initialValue = 1000, 
	allocationSize = 100)
// No cache, mutable and critical
@Table(name = "invoice")
public class Invoice {

	@Id @GeneratedValue(strategy = GenerationType.TABLE, generator = "invoice_GEN")
	@Column(name = "ID")
	private Integer id;

	@Column(name = "Balance")
	private Double balance = 0.0d;

	@Column(name = "CreateDate")
	private Date createDate = new Date();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
