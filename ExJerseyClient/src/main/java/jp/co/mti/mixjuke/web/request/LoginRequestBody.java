/**
 * 
 */
package jp.co.mti.mixjuke.web.request;

/**
 * @author Xuan Nguyen
 * 
 */
public class LoginRequestBody {

	private String username;
	private String password;
	private String answer;
	private String register;

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the answer
	 */
	public String getAnswer() {
		return answer;
	}

	/**
	 * @param answer
	 *            the answer to set
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}

	/**
	 * @return the register value to auto subscribe. Ex: 1 => auto subscribe
	 */
	public String getRegister() {
		return register;
	}

	/**
	 * @param register
	 *            the register value to auto subscribe. Ex: 1 => auto subscribe
	 */
	public void setRegister(String register) {
		this.register = register;
	}

}
