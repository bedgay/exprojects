/**
 * 
 */
package jp.co.mti.mixjuke.web.external.request;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author Xuan Nguyen
 * 
 */
public class Login extends AbstractRequest {

    private String iaiAcctid;
    private String iaiPwd;
//    @JsonSerialize(include = Inclusion.NON_NULL)
//    private String iaiUagt;
//    @JsonSerialize(include = Inclusion.NON_NULL)
//    private String iaiExpireiaiDomname;

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

//    /**
//     * @return the iaiUagt
//     */
//    @JsonProperty("iai_uagt")
//    public String getIaiUagt() {
//        return iaiUagt;
//    }
//
//    /**
//     * @param iaiUagt
//     *            the iaiUagt to set
//     */
//    public void setIaiUagt(String iaiUagt) {
//        this.iaiUagt = iaiUagt;
//    }
//
//    /**
//     * @return the iaiExpireiaiDomname
//     */
//    @JsonProperty("iai_expireiai_domname")
//    public String getIaiExpireiaiDomname() {
//        return iaiExpireiaiDomname;
//    }
//
//    /**
//     * @param iaiExpireiaiDomname
//     *            the iaiExpireiaiDomname to set
//     */
//    public void setIaiExpireiaiDomname(String iaiExpireiaiDomname) {
//        this.iaiExpireiaiDomname = iaiExpireiaiDomname;
//    }

    public Login(String version, String accessKey, String timeStamp,
            String user, String pwd, String uagt, String exp) {
        super(version, accessKey, timeStamp);
        this.iaiAcctid = user;
        this.iaiPwd = pwd;
//        this.iaiUagt = uagt;
//        this.iaiExpireiaiDomname = exp;
    }
}
