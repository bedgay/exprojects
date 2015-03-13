/**
 * 
 */
package jp.co.mti.mixjuke.ws.response;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author Xuan Nguyen
 * 
 */
public class GenreInfo {

    private String gid;

    private String name;

    private String iconUrl;

    private String mixUrl;

    /**
     * @return the id
     */
    public String getGId() {
        return gid;
    }

    /**
     * @param gid
     *            the id to set
     */
    public void setGid(String gid) {
        this.gid = gid;
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
     * @return the iconUrl
     */
    @JsonProperty("icon_url")
    public String getIconUrl() {
        return iconUrl;
    }

    /**
     * @param iconUrl
     *            the iconUrl to set
     */
    @JsonProperty("icon_url")
    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    /**
     * @return the mixUrl
     */
    @JsonProperty("mix_url")
    public String getMixUrl() {
        return mixUrl;
    }

    /**
     * @param mixUrl
     *            the mixUrl to set
     */
    @JsonProperty("mix_url")
    public void setMixUrl(String mixUrl) {
        this.mixUrl = mixUrl;
    }

}
