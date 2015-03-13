package jp.co.mti.mixjuke.web.external.request;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

public class BeginToken extends AbstractRequest {

    private String iaiSiteId;
    private String iaiCpTransactionId;
    private String iaiIfids;
    @JsonSerialize(include = Inclusion.NON_NULL)
    private String iaiExpire;

    /**
     * @return the iaiSiteId
     */
    @JsonProperty("iai_siteid")
    public String getIaiSiteId() {
        return iaiSiteId;
    }

    /**
     * @param iaiSiteId
     *            the iaiSiteId to set
     */
    public void setIaiSiteId(String iaiSiteId) {
        this.iaiSiteId = iaiSiteId;
    }

    /**
     * @return the iaiCpTransactionId
     */
    @JsonProperty("iai_cp_transactionid")
    public String getIaiCpTransactionId() {
        return iaiCpTransactionId;
    }

    /**
     * @param iaiCpTransactionId
     *            the iaiCpTransactionId to set
     */
    public void setIaiCpTransactionId(String iaiCpTransactionId) {
        this.iaiCpTransactionId = iaiCpTransactionId;
    }

    /**
     * @return the iaiIfids
     */
    @JsonProperty("iai_ifids")
    public String getIaiIfids() {
        return iaiIfids;
    }

    /**
     * @param iaiIfids
     *            the iaiIfids to set
     */
    public void setIaiIfids(String iaiIfids) {
        this.iaiIfids = iaiIfids;
    }

    /**
     * @return the iaiExpire
     */
    @JsonProperty("iai_expire")
    public String getIaiExpire() {
        return iaiExpire;
    }

    /**
     * @param iaiExpire
     *            the iaiExpire to set
     */
    public void setIaiExpire(String iaiExpire) {
        this.iaiExpire = iaiExpire;
    }

    public BeginToken() {
        super();
    }

    public BeginToken(String version, String accessKey, String timeStamp,
            String siteId, String transaction, String ifId, String expire) {
        super(version, accessKey, timeStamp);
        this.iaiSiteId = siteId;
        this.iaiCpTransactionId = transaction;
        this.iaiIfids = ifId;
        this.iaiExpire = expire;
    }

}
