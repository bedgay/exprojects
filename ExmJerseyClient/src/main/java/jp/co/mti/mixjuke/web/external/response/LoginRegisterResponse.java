/**
 * 
 */
package jp.co.mti.mixjuke.web.external.response;


/**
 * @author Xuan Nguyen
 *
 */
public class LoginRegisterResponse{

    private String mtisess;
    private String muid;
    private Result result;
    
    /**
     * @return the mtisess
     */
    public String getMtisess() {
        return mtisess;
    }

    /**
     * @param mtisess the mtisess to set
     */
    public void setMtisess(String mtisess) {
        this.mtisess = mtisess;
    }

    /**
     * @return the muid
     */
    public String getMuid() {
        return muid;
    }

    /**
     * @param muid the muid to set
     */
    public void setMuid(String muid) {
        this.muid = muid;
    }

    /**
     * @return the result
     */
    public Result getResult() {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(Result result) {
        this.result = result;
    }

}
