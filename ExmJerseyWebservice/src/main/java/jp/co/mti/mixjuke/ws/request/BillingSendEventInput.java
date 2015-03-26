package jp.co.mti.mixjuke.ws.request;

import javax.ws.rs.core.MultivaluedMap;

import org.apache.commons.lang.StringUtils;

import jp.co.mti.mixjuke.dom.Purchase;

public class BillingSendEventInput {

	private Purchase data;
	private String sig;
	private String dataJson;
    private String uid;

	public Purchase getData() {
		return data;
	}

	public void setData(Purchase data) {
		this.data = data;
	}

	public String getSig() {
		return sig;
	}

	public void setSig(String sig) {
		this.sig = sig;
	}

	public String getDataJson() {
		return dataJson;
	}

	public void setDataJson(String dataJson) {
		this.dataJson = dataJson;
	}

    public boolean isValidData(){
    	return (!StringUtils.isEmpty(this.getSig())) && (this.getData()!=null);
    }
    
    public static BillingSendEventInput fromParams(MultivaluedMap<String, String> params) throws Exception {
		BillingSendEventInput input = new BillingSendEventInput();
		
		if (params.containsKey("sig")) {
			input.setSig(params.getFirst("sig"));
		}
		
		if (params.containsKey("data")) {
			input.setDataJson(params.getFirst("data"));
		}
        if (params.containsKey("uid")) {
            input.setUid(params.getFirst("uid"));
        }
		return input;
	}

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
