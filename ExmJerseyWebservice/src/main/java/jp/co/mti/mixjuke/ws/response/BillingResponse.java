package jp.co.mti.mixjuke.ws.response;

import jp.co.mti.mixjuke.ws.base.ResouceBundleHelper;

import org.codehaus.jackson.map.annotate.JsonSerialize;


public class BillingResponse extends AbstractRespone {

	private String error = null;
	private BillingEventRFirst mtiresp = null;

    public BillingResponse() {
		super();
	}

	public BillingResponse(ResultCode result) {
        super(result);
        this.error = ResouceBundleHelper.getMessage(result.getDescription());
    }

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public BillingEventRFirst getMtiresp() {
		return mtiresp;
	}

	public void setMtiresp(BillingEventRFirst mtiresp) {
		this.mtiresp = mtiresp;
	}

}
