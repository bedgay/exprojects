/**
 * 
 */
package jp.co.mti.mixjuke.web.request;

/**
 * @author Xuan Nguyen
 * 
 */
public class RegisterRequestBody {

	private String username;
	private String password;
	private String repassword;
	private String answer;
	private Boolean checkInputValid;
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
	 * @return the repassword
	 */
	public String getRepassword() {
		return repassword;
	}

	/**
	 * @param repassword
	 *            the repassword to set
	 */
	public void setRepassword(String repassword) {
		this.repassword = repassword;
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
	 * @return the checkInputValid
	 */
	public Boolean getCheckInputValid() {
		return checkInputValid;
	}

	/**
	 * @param checkInputValid the checkInputValid to set
	 */
	public void setCheckInputValid(Boolean checkInputValid) {
		this.checkInputValid = checkInputValid;
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

	public RegisterRequestBody() {
		this.username = null;
		this.password = null;
		this.repassword = null;
		this.answer = null;
	}

	public RegisterRequestBody(String username, String pwd, String repwd, String answer, Boolean checkInput) {
		this.username = username;
		this.password = pwd;
		this.repassword = repwd;
		this.answer = answer;
		this.checkInputValid = checkInput;
	}
}
