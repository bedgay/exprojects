package jp.co.mti.mixjuke.ws.request;

import java.util.Map;

import jp.co.mti.mixjuke.dom.Purchase;

/**
 * @author natu
 * @date 2014-01-08
 */
public class CancelationInput {

	private String ci;
	private String uid;
	private String act;

	private String iai_tms;
	private Integer iai_paytype;
	private String iai_ordid;

	private String iai_dl_market;

	private String iai_cp_itemid;
	private String iai_inapp_ps;
	private String iai_inapp_oid;

	private String iai_inapp_pid;
	private Purchase iai_inapp_sig_d;
	private String iai_inapp_sig;
	private String iai_inapp_sig_json;

	public String getCi() {
		return ci;
	}

	public void setCi(String ci) {
		this.ci = ci;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getAct() {
		return act;
	}

	public void setAct(String act) {
		this.act = act;
	}

	public String getIai_tms() {
		return iai_tms;
	}

	public void setIai_tms(String iai_tms) {
		this.iai_tms = iai_tms;
	}

	public Integer getIai_paytype() {
		return iai_paytype;
	}

	public void setIai_paytype(Integer iai_paytype) {
		this.iai_paytype = iai_paytype;
	}

	public String getIai_ordid() {
		return iai_ordid;
	}

	public void setIai_ordid(String iai_ordid) {
		this.iai_ordid = iai_ordid;
	}

	public String getIai_dl_market() {
		return iai_dl_market;
	}

	public void setIai_dl_market(String iai_dl_market) {
		this.iai_dl_market = iai_dl_market;
	}

	public String getIai_cp_itemid() {
		return iai_cp_itemid;
	}

	public void setIai_cp_itemid(String iai_cp_itemid) {
		this.iai_cp_itemid = iai_cp_itemid;
	}

	public String getIai_inapp_ps() {
		return iai_inapp_ps;
	}

	public void setIai_inapp_ps(String iai_inapp_ps) {
		this.iai_inapp_ps = iai_inapp_ps;
	}

	public String getIai_inapp_oid() {
		return iai_inapp_oid;
	}

	public void setIai_inapp_oid(String iai_inapp_oid) {
		this.iai_inapp_oid = iai_inapp_oid;
	}

	public String getIai_inapp_pid() {
		return iai_inapp_pid;
	}

	public void setIai_inapp_pid(String iai_inapp_pid) {
		this.iai_inapp_pid = iai_inapp_pid;
	}

	public Purchase getIai_inapp_sig_d() {
		return iai_inapp_sig_d;
	}

	public void setIai_inapp_sig_d(Purchase iai_inapp_sig_d) {
		this.iai_inapp_sig_d = iai_inapp_sig_d;
	}

	public String getIai_inapp_sig() {
		return iai_inapp_sig;
	}

	public void setIai_inapp_sig(String iai_inapp_sig) {
		this.iai_inapp_sig = iai_inapp_sig;
	}

	public String getIai_inapp_sig_json() {
		return iai_inapp_sig_json;
	}

	public void setIai_inapp_sig_json(String iai_inapp_sig_json) {
		this.iai_inapp_sig_json = iai_inapp_sig_json;
	}

	public static CancelationInput fromParams(Map<String, String> params) throws Exception {
		CancelationInput input = new CancelationInput();
		
		if (params.containsKey("ci")) {
			input.setCi(params.get("ci"));
		}
		if (params.containsKey("uid")) {
			input.setUid(params.get("uid"));
		}
		if (params.containsKey("act")) {
			input.setAct(params.get("act"));
		}
		if (params.containsKey("iai_tms")) {
			input.setIai_tms(params.get("iai_tms"));
		}
		if (params.containsKey("iai_paytype")) {
			input.setIai_paytype(Integer.valueOf(params.get("iai_paytype")));
		}
		if (params.containsKey("iai_ordid")) {
			input.setIai_ordid(params.get("iai_ordid"));
		}
		if (params.containsKey("iai_dl_market")) {
			input.setIai_dl_market(params.get("iai_dl_market"));
		}
		if (params.containsKey("iai_cp_itemid")) {
			input.setIai_cp_itemid(params.get("iai_cp_itemid"));
		}
		if (params.containsKey("iai_inapp_ps")) {
			input.setIai_inapp_ps(params.get("iai_inapp_ps"));
		}
		if (params.containsKey("iai_inapp_oid")) {
			input.setIai_inapp_oid(params.get("iai_inapp_oid"));
		}
		if (params.containsKey("iai_inapp_pid")) {
			input.setIai_inapp_pid(params.get("iai_inapp_pid"));
		}
		if (params.containsKey("iai_inapp_sig_d")) {
			input.setIai_inapp_sig_json(params.get("iai_inapp_sig_d"));
		}
		if (params.containsKey("iai_inapp_sig")) {
			input.setIai_inapp_sig(params.get("iai_inapp_sig"));
		}
		
		return input;
	}

}
