package jp.co.mti.mixjuke.ws.response;

public class BillingEventRThird {

	private String notificationid;
	private String orderid;
	private String purchaseToken;
	private String receptiontype;
	private BillingEventRFourth result;

	public String getNotificationid() {
		return notificationid;
	}

	public void setNotificationid(String notificationid) {
		this.notificationid = notificationid;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getPurchaseToken() {
		return purchaseToken;
	}

	public void setPurchaseToken(String purchaseToken) {
		this.purchaseToken = purchaseToken;
	}

	public String getReceptiontype() {
		return receptiontype;
	}

	public void setReceptiontype(String receptiontype) {
		this.receptiontype = receptiontype;
	}

	public BillingEventRFourth getResult() {
		return result;
	}

	public void setResult(BillingEventRFourth result) {
		this.result = result;
	}

}
