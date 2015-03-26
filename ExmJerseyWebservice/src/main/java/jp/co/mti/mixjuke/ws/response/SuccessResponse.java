/**
 * 
 */
package jp.co.mti.mixjuke.ws.response;

import jp.co.mti.mixjuke.ws.base.ResouceBundleHelper;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @author Xuan Nguyen
 * 
 */
public class SuccessResponse extends AbstractRespone {

    private String error = null;

    /**
     * @return the error
     */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
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

    public SuccessResponse() {
        super(ResultCode.NORMAL);
        this.error = null;
    }

    /**
     * @param result
     */
    public SuccessResponse(int result) {
        super(result);
        this.error = null;
    }

    /**
     * @param result
     */
    public SuccessResponse(ResultCode result) {
        super(result);
        this.error = ResouceBundleHelper.getMessage(result.getDescription());
    }

}
