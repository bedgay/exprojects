package jp.co.mti.mixjuke.ws.request;

import org.codehaus.jackson.annotate.JsonProperty;

public class MopitaIDRequest extends AbstractRequest {

	private String muid;

	public MopitaIDRequest() {
		super();
	}

	public MopitaIDRequest(String version, String accessKey, String timeStamp,
			String muid) {
		super(version, accessKey, timeStamp);
		this.muid = muid;
	}

	@JsonProperty("iai_muid")
	public String getMuid() {
		return muid;
	}

	public void setMuid(String muid) {
		this.muid = muid;
	}

}
