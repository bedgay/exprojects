/**
 * 
 */
package jp.co.mti.mixjuke.web.external.request;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * @author Xuan Nguyen
 * 
 */
public class ReleaseSubscribe extends AbstractRequest {

    private String iaiSiteID;
    private String iaiCpTransactionId;
    private String iaiToken;
    private String iiaiMuid;
    private String iaiRid;
    @JsonSerialize(include = Inclusion.NON_NULL)
    private String iaiArg;
    @JsonSerialize(include = Inclusion.NON_NULL)
    private String iaiUagt;

    /**
     * @return the iaiSiteID
     */
    @JsonProperty("iai_siteid")
    public String getIaiSiteID() {
        return iaiSiteID;
    }

    /**
     * @param iaiSiteID
     *            the iaiSiteID to set
     */
    public void setIaiSiteID(String iaiSiteID) {
        this.iaiSiteID = iaiSiteID;
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
     * @return the iaiToken
     */
    @JsonProperty("iai_token")
    public String getIaiToken() {
        return iaiToken;
    }

    /**
     * @param iaiToken
     *            the iaiToken to set
     */
    public void setIaiToken(String iaiToken) {
        this.iaiToken = iaiToken;
    }

    /**
     * @return the iiaiMuid
     */
    @JsonProperty("iai_muid")
    public String getIiaiMuid() {
        return iiaiMuid;
    }

    /**
     * @param iiaiMuid
     *            the iiaiMuid to set
     */
    public void setIiaiMuid(String iiaiMuid) {
        this.iiaiMuid = iiaiMuid;
    }

    /**
     * @return the iaiRid
     */
    @JsonProperty("iai_rid")
    public String getIaiRid() {
        return iaiRid;
    }

    /**
     * @param iaiRid
     *            the iaiRid to set
     */
    public void setIaiRid(String iaiRid) {
        this.iaiRid = iaiRid;
    }

    /**
     * @return the iaiArg
     */
    @JsonProperty("iai_arg")
    public String getIaiArg() {
        return iaiArg;
    }

    /**
     * @param iaiArg
     *            the iaiArg to set
     */
    public void setIaiArg(String iaiArg) {
        this.iaiArg = iaiArg;
    }

    /**
     * @return the iaiUagt
     */
    @JsonProperty("iai_uagt")
    public String getIaiUagt() {
        return iaiUagt;
    }

    /**
     * @param iaiUagt
     *            the iaiUagt to set
     */
    public void setIaiUagt(String iaiUagt) {
        this.iaiUagt = iaiUagt;
    }

}
