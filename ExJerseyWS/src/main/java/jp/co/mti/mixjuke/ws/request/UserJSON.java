/**
 * 
 */
package jp.co.mti.mixjuke.ws.request;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * @author Xuan Nguyen
 * 
 */
public class UserJSON {

    private String name;
    private Integer gender;
    private String birthday;
    private String avatorUrl;
    private Boolean shareProfile;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the gender
     */
    public Integer getGender() {
        return gender;
    }

    /**
     * @param gender
     *            the gender to set
     */
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    /**
     * @return the birthday
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * @param birthday
     *            the birthday to set
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * @return the avatorUrl
     */
    @JsonProperty("avator_url")
    public String getAvatorUrl() {
        return avatorUrl;
    }

    /**
     * @param avatorUrl
     *            the avatorUrl to set
     */
    @JsonProperty("avator_url")
    public void setAvatorUrl(String avatorUrl) {
        this.avatorUrl = avatorUrl;
    }

    /**
     * @return the shareProfile
     */
    @JsonSerialize(include = Inclusion.NON_NULL)
    @JsonProperty("share_profile")
    public Boolean isShareProfile() {
        return shareProfile;
    }

    /**
     * @param shareProfile
     *            the shareProfile to set
     */
    @JsonSerialize(include = Inclusion.NON_NULL)
    @JsonProperty("share_profile")
    public void setShareProfile(Boolean shareProfile) {
        this.shareProfile = shareProfile;
    }
}
