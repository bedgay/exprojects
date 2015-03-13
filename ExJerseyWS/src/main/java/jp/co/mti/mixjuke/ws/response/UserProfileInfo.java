/**
 * 
 */
package jp.co.mti.mixjuke.ws.response;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author Xuan Nguyen
 * 
 */
public class UserProfileInfo {

    private String mjuid;
    private String name;
    private int gender;
    private String birthday;
    private String avatorUrl;
    private int memberStatus;
    private boolean shareProfile;
    private boolean initial;

    /**
     * @return the mjuid
     */
    public String getMjuid() {
        return mjuid;
    }

    /**
     * @param mjuid
     *            the mjuid to set
     */
    public void setMjuid(String mjuid) {
        this.mjuid = mjuid;
    }

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
    public int getGender() {
        return gender;
    }

    /**
     * @param gender
     *            the gender to set
     */
    public void setGender(int gender) {
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
     * @return the memberStatus
     */
    @JsonProperty("member_status")
    public int getMemberStatus() {
        return memberStatus;
    }

    /**
     * @param memberStatus
     *            the memberStatus to set
     */
    @JsonProperty("member_status")
    public void setMemberStatus(int memberStatus) {
        this.memberStatus = memberStatus;
    }

    /**
     * @return the shareProfile
     */
    @JsonProperty("share_profile")
    public boolean isShareProfile() {
        return shareProfile;
    }

    /**
     * @param shareProfile
     *            the shareProfile to set
     */
    @JsonProperty("share_profile")
    public void setShareProfile(boolean shareProfile) {
        this.shareProfile = shareProfile;
    }

    /**
     * @return the initial
     */
    public boolean isInitial() {
        return initial;
    }

    /**
     * @param initial
     *            the initial to set
     */
    public void setInitial(boolean initial) {
        this.initial = initial;
    }

}
