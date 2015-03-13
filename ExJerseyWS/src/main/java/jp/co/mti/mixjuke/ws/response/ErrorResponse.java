/**
 * 
 */
package jp.co.mti.mixjuke.ws.response;

/**
 * @author Xuan Nguyen
 * 
 */
public class ErrorResponse extends AbstractRespone {

    private String error;

    /**
     * @return the error
     */
    public String getError() {
        return error;
    }

    /**
     * @param error
     *            the error to set
     */
    public void setError(String error) {
        this.error = error;
    }

    public ErrorResponse(int code, String description) {
        super(code);
        this.error = description;
    }

}
