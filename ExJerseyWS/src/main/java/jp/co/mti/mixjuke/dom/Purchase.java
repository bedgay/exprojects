package jp.co.mti.mixjuke.dom;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * 
 * @author natu
 * @date 2014-01-13
 * 
 */
@Entity
@Table(name = "MJ_PURCHASES")
public class Purchase extends AbstractDomain implements Serializable {

	private static final long serialVersionUID = -128600208976941564L;

	@JsonProperty("nonce")
	private String nonce;

	@JsonIgnore
	private User user;

	@JsonProperty("orders")
	private List<PurchaseOrder> orders;

	@Override
	@Id
	@Column(name = "nonce")
	@JsonProperty("nonce")
	public String getId() {
		return id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mjuid", nullable = false)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

    @OneToMany(mappedBy = "purchase", fetch = FetchType.LAZY)
    @Cascade({ CascadeType.ALL })
	public List<PurchaseOrder> getOrders() {
		return orders;
	}

	public void setOrders(List<PurchaseOrder> orders) {
		this.orders = orders;
	}

	@Transient
	public String getNonce() {
		return id;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
		this.id = nonce;
	}

	@JsonIgnore
	public String toString() {
		try {
			ObjectMapper om = new ObjectMapper();
			return om.writeValueAsString(this);
		} catch (Exception e) {
			return null;
		}
	}

}
