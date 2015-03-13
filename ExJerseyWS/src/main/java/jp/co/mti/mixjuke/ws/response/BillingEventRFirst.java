package jp.co.mti.mixjuke.ws.response;

import java.util.List;

public class BillingEventRFirst {
	private List<BillingEventRSecond> purchaselist;
	private BillingEventRFourth result;

	public List<BillingEventRSecond> getPurchaselist() {
		return purchaselist;
	}

	public void setPurchaselist(List<BillingEventRSecond> purchaselist) {
		this.purchaselist = purchaselist;
	}

	public BillingEventRFourth getResult() {
		return result;
	}

	public void setResult(BillingEventRFourth result) {
		this.result = result;
	}

}
