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
public class Register extends AbstractRequest {

    private String iaiSiteID;
    private String iaiCpTransactionId;
    private String iaiToken;
    private String iaiAcctid;
    private String iaiPwd;
    @JsonSerialize(include = Inclusion.NON_NULL)
    private String iaiBirthday;
    @JsonSerialize(include = Inclusion.NON_NULL)
    private String iaiMailAddr;
    @JsonSerialize(include = Inclusion.NON_NULL)
    private String iaiTelno;
    @JsonSerialize(include = Inclusion.NON_NULL)
    private String iaiUagt;
    @JsonSerialize(include = Inclusion.NON_NULL)
    private String iaiRouteType;
    @JsonSerialize(include = Inclusion.NON_NULL)
    private String iaiDomname;

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
     * @return the iaiAcctid
     */
    @JsonProperty("iai_acctid")
    public String getIaiAcctid() {
        return iaiAcctid;
    }

    /**
     * @param iaiAcctid
     *            the iaiAcctid to set
     */
    public void setIaiAcctid(String iaiAcctid) {
        this.iaiAcctid = iaiAcctid;
    }

    /**
     * @return the iaiPwd
     */
    @JsonProperty("iai_pwd")
    public String getIaiPwd() {
        return iaiPwd;
    }

    /**
     * @param iaiPwd
     *            the iaiPwd to set
     */
    public void setIaiPwd(String iaiPwd) {
        this.iaiPwd = iaiPwd;
    }

    /**
     * @return the iaiBirthday
     */
    @JsonProperty("iai_birthday")
    public String getIaiBirthday() {
        return iaiBirthday;
    }

    /**
     * @param iaiBirthday
     *            the iaiBirthday to set
     */
    public void setIaiBirthday(String iaiBirthday) {
        this.iaiBirthday = iaiBirthday;
    }

    /**
     * @return the iaiMailAddr
     */
    @JsonProperty("iai_mailaddr")
    public String getIaiMailAddr() {
        return iaiMailAddr;
    }

    /**
     * @param iaiMailAddr
     *            the iaiMailAddr to set
     */
    public void setIaiMailAddr(String iaiMailAddr) {
        this.iaiMailAddr = iaiMailAddr;
    }

    /**
     * @return the iaiTelno
     */
    @JsonProperty("iai_telno")
    public String getIaiTelno() {
        return iaiTelno;
    }

    /**
     * @param iaiTelno
     *            the iaiTelno to set
     */
    public void setIaiTelno(String iaiTelno) {
        this.iaiTelno = iaiTelno;
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

    /**
     * @return the iaiRouteType
     */
    @JsonProperty("iai_routetype")
    public String getIaiRouteType() {
        return iaiRouteType;
    }

    /**
     * @param iaiRouteType
     *            the iaiRouteType to set
     */
    public void setIaiRouteType(String iaiRouteType) {
        this.iaiRouteType = iaiRouteType;
    }

    /**
     * @return the iaiDomname
     */
    @JsonProperty("iai_domname")
    public String getIaiDomname() {
        return iaiDomname;
    }

    /**
     * @param iaiDomname
     *            the iaiDomname to set
     */
    public void setIaiDomname(String iaiDomname) {
        this.iaiDomname = iaiDomname;
    }

    public Register(String version, String accessKey, String timeStamp) {
        super(version, accessKey, timeStamp);
    }

    public Register() {
        super();
    }
}
