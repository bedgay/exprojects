package jp.co.mti.mixjuke.dom;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author natu
 * @date 2014-01-16
 *
 */
@Entity
@Table(name = "mj_purchase_histories")
public class PurchaseHistory extends AbstractDomain implements Serializable {

	private static final long serialVersionUID = -128600208976941564L;

	private String notificationId;
	private String orderId;

	private String packageName;
	private String productId;
	private Timestamp purchaseTime;

	// Possible values are 0 (purchased), 1 (canceled), 2 (refunded), or 3
	// (expired, for subscription purchases only).
	private Integer purchaseState;
	private String purchaseToken;
	private String developerPayload;

	private User user;

	@Override
	@Id
	@Column(name = "nonce")
	public String getId() {
		return id;
	}

	@Column(name = "notification_id")
	public String getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(String notificationId) {
		this.notificationId = notificationId;
	}

	@Column(name = "order_id")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Column(name = "package_name")
	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	@Column(name = "product_id")
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	@Column(name = "purchase_time")
	public Timestamp getPurchaseTime() {
		return purchaseTime;
	}

	public void setPurchaseTime(Timestamp purchaseTime) {
		this.purchaseTime = purchaseTime;
	}

	@Column(name = "purchase_state")
	public Integer getPurchaseState() {
		return purchaseState;
	}

	public void setPurchaseState(Integer purchaseState) {
		this.purchaseState = purchaseState;
	}

	@Column(name = "purchase_token")
	public String getPurchaseToken() {
		return purchaseToken;
	}

	public void setPurchaseToken(String purchaseToken) {
		this.purchaseToken = purchaseToken;
	}

	@Column(name = "developer_payload")
	public String getDeveloperPayload() {
		return developerPayload;
	}

	public void setDeveloperPayload(String developerPayload) {
		this.developerPayload = developerPayload;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mjuid", nullable = false)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
