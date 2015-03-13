/**
 * 
 */
package jp.co.mti.mixjuke.web.external.request;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author Xuan Nguyen
 * 
 */
public class InquiryMember extends AbstractRequest {

    private String iaiMuid;

    /**
     * @return the iaiMuid
     */
    @JsonProperty("iai_muid")
    public String getIaiMuid() {
        return iaiMuid;
    }

    /**
     * @param iaiMuid
     *            the iaiMuid to set
     */
    public void setIaiMuid(String iaiMuid) {
        this.iaiMuid = iaiMuid;
    }

    /**
     * @param version
     * @param accessKey
     * @param timeStamp
     */
    public InquiryMember(String version, String accessKey, String timeStamp,
            String uid) {
        super(version, accessKey, timeStamp);
        this.iaiMuid = uid;
    }

}
