/**
 * 
 */
package jp.co.mti.mixjuke.web.external.response;


/**
 * @author Xuan Nguyen
 * 
 */
public class BeginTokenResponse {

    private String token;
    private Result result;

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token
     *            the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return the result
     */
    public Result getResult() {
        return result;
    }

    /**
     * @param result
     *            the result to set
     */
    public void setResult(Result result) {
        this.result = result;
    }

}
