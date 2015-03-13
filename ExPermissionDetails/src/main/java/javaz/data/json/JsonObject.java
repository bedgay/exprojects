package javaz.data.json;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author TuNgo
 * @date Sep 22, 2014 10:56:23 PM
 */
public class JsonObject implements Serializable {

	private static final long serialVersionUID = 1L;
	
	enum Status {
		OK, ERROR
	}
	
	public enum ErrorCode {
		OK(200),
		UNAUTHORIZED(401),
		SAVE_ERROR(403);
		
		private int code;
		
		ErrorCode(int code) {
			this.code = code;
		}
		
		public int value() {
			return code;
		}
	}

	private Map<String, Object> header = new HashMap<>();

	private Map<String, Object> body = new HashMap<>();

	private Map<String, Object> error = new HashMap<>();

	public Map<String, Object> getHeader() {
		return header;
	}

	public void setHeader(Map<String, Object> header) {
		this.header = header;
	}

	public Map<String, Object> getBody() {
		return body;
	}

	public void setBody(Map<String, Object> body) {
		this.body = body;
	}

	public Map<String, Object> getError() {
		return error;
	}

	public void setErrors(Map<String, Object> error) {
		this.error = error;
	}
	
	/**
	 * @param code
	 * @param msg
	 */
	public void addError(String msg) {
		this.error.put("msg", msg);
	}
	
	/**
	 * @param body
	 * @return
	 */
	public static JsonObject wrapData(Object data) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.getHeader().put("code", ErrorCode.OK.value());
		jsonObject.getHeader().put("status", Status.OK.name());
		jsonObject.getBody().put("data", data);		
		return jsonObject;
	}

	/**
	 * @param code
	 * @param msg
	 * @return
	 */
	public static JsonObject wrapError(String msg) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.getHeader().put("code", ErrorCode.UNAUTHORIZED.value());
		jsonObject.getHeader().put("status", Status.ERROR.name());
		jsonObject.addError(msg);
		return jsonObject;
	}

	/**
	 * @param code
	 * @param msg
	 * @return
	 */
	public static JsonObject wrapError(Integer code, String msg) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.getHeader().put("code", code);
		jsonObject.getHeader().put("status", Status.ERROR.name());
		jsonObject.addError(msg);
		return jsonObject;
	}
	
}
