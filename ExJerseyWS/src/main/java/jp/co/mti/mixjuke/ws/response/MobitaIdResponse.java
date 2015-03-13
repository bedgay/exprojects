package jp.co.mti.mixjuke.ws.response;


/**
 * @author natu
 * @date 2014-01-15
 */
public class MobitaIdResponse {

	private String acctid; // mopita ID

	private MopitaIdResult result; // {code:'', args:[], target:'IF0016'}

	public String getAcctid() {
		return acctid;
	}

	public void setAcctid(String acctid) {
		this.acctid = acctid;
	}

	public MopitaIdResult getResult() {
		return result;
	}

	public void setResult(MopitaIdResult result) {
		this.result = result;
	}

}
