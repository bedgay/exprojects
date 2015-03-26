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
public class Subscribe extends ReleaseSubscribe {

    @JsonSerialize(include = Inclusion.NON_NULL)
    private String iaiNtasess;
    @JsonSerialize(include = Inclusion.NON_NULL)
    private String iaiLsid;
    @JsonSerialize(include = Inclusion.NON_NULL)
    private String iaiAfpgid;
    @JsonSerialize(include = Inclusion.NON_NULL)
    private String iaiAfseid;
    @JsonSerialize(include = Inclusion.NON_NULL)
    private String iaiAspinfo;

    /**
     * @return the iaiNtasess
     */
    @JsonProperty("iai_ntasess")
    public String getIaiNtasess() {
        return iaiNtasess;
    }

    /**
     * @param iaiNtasess
     *            the iaiNtasess to set
     */
    public void setIaiNtasess(String iaiNtasess) {
        this.iaiNtasess = iaiNtasess;
    }

    /**
     * @return the iaiLsid
     */
    @JsonProperty("iai_lsid")
    public String getIaiLsid() {
        return iaiLsid;
    }

    /**
     * @param iaiLsid
     *            the iaiLsid to set
     */
    public void setIaiLsid(String iaiLsid) {
        this.iaiLsid = iaiLsid;
    }

    /**
     * @return the iaiAfpgid
     */
    @JsonProperty("iai_afpgid")
    public String getIaiAfpgid() {
        return iaiAfpgid;
    }

    /**
     * @param iaiAfpgid
     *            the iaiAfpgid to set
     */
    public void setIaiAfpgid(String iaiAfpgid) {
        this.iaiAfpgid = iaiAfpgid;
    }

    /**
     * @return the iaiAfseid
     */
    @JsonProperty("iai_afseid")
    public String getIaiAfseid() {
        return iaiAfseid;
    }

    /**
     * @param iaiAfseid
     *            the iaiAfseid to set
     */
    public void setIaiAfseid(String iaiAfseid) {
        this.iaiAfseid = iaiAfseid;
    }

    /**
     * @return the iaiAspinfo
     */
    @JsonProperty("iai_aspinfo")
    public String getIaiAspinfo() {
        return iaiAspinfo;
    }

    /**
     * @param iaiAspinfo
     *            the iaiAspinfo to set
     */
    public void setIaiAspinfo(String iaiAspinfo) {
        this.iaiAspinfo = iaiAspinfo;
    }

}
