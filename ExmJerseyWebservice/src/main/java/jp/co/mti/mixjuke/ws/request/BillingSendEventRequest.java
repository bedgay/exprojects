package jp.co.mti.mixjuke.ws.request;

import org.codehaus.jackson.annotate.JsonProperty;

public class BillingSendEventRequest extends AbstractRequest {

	private String data;
	
	private String sig;

	public BillingSendEventRequest() {
		super();
	}

	@JsonProperty("iai_inapp_sig_d")
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@JsonProperty("iai_inapp_sig")
	public String getSig() {
		return sig;
	}

	public void setSig(String sig) {
		this.sig = sig;
	}

}
