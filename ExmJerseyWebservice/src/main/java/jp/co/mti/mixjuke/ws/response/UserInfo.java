package jp.co.mti.mixjuke.ws.response;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * @author Xuan Nguyen
 * 
 */

public class UserInfo {

    private String mjuid;
    private String name;
    private String avatarUrl;
    private String mixUrl;
    private boolean initial;
    // use in case SocialList API compare uid which returned from RE, if not we
    // must query mjuid to DB from uid.
    @JsonIgnore
    private String uid;

    @JsonSerialize(include = Inclusion.NON_NULL)
    private SongInfo song;

    public String getMjuid() {
        return mjuid;
    }

    public void setMjuid(String mjuid) {
        this.mjuid = mjuid;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("avatar_url")
    public String getAvatarUrl() {
        return avatarUrl;
    }

    @JsonProperty("avatar_url")
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @JsonProperty("mix_url")
    public String getMixUrl() {
        return mixUrl;
    }

    @JsonProperty("mix_url")
    public void setMixUrl(String mixUrl) {
        this.mixUrl = mixUrl;
    }

    /**
     * @return the song
     */
    public SongInfo getSong() {
        return song;
    }

    /**
     * @param song
     *            the song to set
     */
    public void setSong(SongInfo song) {
        this.song = song;
    }

    /**
     * @return the uid
     */
    @JsonIgnore
    public String getUid() {
        return uid;
    }

    /**
     * @param uid
     *            the uid to set
     */
    @JsonIgnore
    public void setUid(String uid) {
        this.uid = uid;
    }

}
