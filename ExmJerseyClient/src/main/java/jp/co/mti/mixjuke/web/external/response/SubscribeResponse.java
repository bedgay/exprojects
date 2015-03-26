/**
 * 
 */
package jp.co.mti.mixjuke.web.external.response;

/**
 * @author Xuan Nguyen
 * 
 */
public class SubscribeResponse {

    private String buyid;
    private Result result;

    /**
     * @return the buyid
     */
    public String getBuyid() {
        return buyid;
    }

    /**
     * @param buyid
     *            the buyid to set
     */
    public void setBuyid(String buyid) {
        this.buyid = buyid;
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
