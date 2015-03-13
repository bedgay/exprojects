/**
 * 
 */
package jp.co.mti.mixjuke.web.response;

/**
 * @author Xuan
 *
 */
public class DataModel {

	private ResultCode code;
	private String message;
	
	public DataModel() {
		this.code = null;
		this.message = null;
	}

	public DataModel(ResultCode code, String msg){
		this.code  = code;
		this.message = msg;
	}
	/**
	 * @return the code
	 */
	public ResultCode getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(ResultCode code) {
		this.code = code;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
