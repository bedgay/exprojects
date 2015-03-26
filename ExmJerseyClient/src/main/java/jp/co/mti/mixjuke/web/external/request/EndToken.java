/**
 * 
 */
package jp.co.mti.mixjuke.web.external.request;


/**
 * @author Xuan Nguyen
 * 
 */
public class EndToken extends AbstractRequest {

    private String iai_token;

    /**
     * @return the iai_token
     */
    public String getIai_token() {
        return iai_token;
    }

    /**
     * @param iai_token
     *            the iai_token to set
     */
    public void setIai_token(String iai_token) {
        this.iai_token = iai_token;
    }

    /**
     * @param version
     * @param accessKey
     * @param timeStamp
     */
    public EndToken(String version, String accessKey, String timeStamp,
            String token) {
        super(version, accessKey, timeStamp);
        this.iai_token = token;
    }
}
