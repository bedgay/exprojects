package jp.co.mti.mixjuke.ws.response;

/**
 * @author natu
 * @date 2014-01-22
 */
public enum MopitaResult {
	OK(""), NG(""), I000(""), E025("");
	
	private String description;
	
	private MopitaResult(String description) {
		this.description = description;
	}
	
	public String getCode() {
		return name();
	}
	
	public String getDescription() {
		return description;
	}
	
}
