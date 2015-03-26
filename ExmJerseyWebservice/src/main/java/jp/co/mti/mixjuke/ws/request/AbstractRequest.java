package jp.co.mti.mixjuke.ws.request;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author natu
 * @date 2014-01-15
 */
public class AbstractRequest {

    private String iaiAver;
    private String iaiAkey;
    private String iaiAtms;

    public AbstractRequest() {
        this.iaiAkey = this.iaiAver = this.iaiAtms = "";
    }

    public AbstractRequest(String version, String accessKey, String timeStamp) {
        this.iaiAkey = accessKey;
        this.iaiAver = version;
        this.iaiAtms = timeStamp;
    }

    /**
     * @return the iaiAver
     */
    @JsonProperty("iai_aver")
    public String getIaiAver() {
        return iaiAver;
    }

    /**
     * @param iaiAver
     *            the iaiAver to set
     */
    public void setIaiAver(String iaiAver) {
        this.iaiAver = iaiAver;
    }

    /**
     * @return the iaiAkey
     */
    @JsonProperty("iai_akey")
    public String getIaiAkey() {
        return iaiAkey;
    }

    /**
     * @param iaiAkey
     *            the iaiAkey to set
     */
    public void setIaiAkey(String iaiAkey) {
        this.iaiAkey = iaiAkey;
    }

    /**
     * @return the iaiAtms
     */
    @JsonProperty("iai_atms")
    public String getIaiAtms() {
        return iaiAtms;
    }

    /**
     * @param iaiAtms
     *            the iaiAtms to set
     */
    public void setIaiAtms(String iaiAtms) {
        this.iaiAtms = iaiAtms;
    }

}
