package jp.co.mti.mixjuke.ws.response;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * @author Xuan Nguyen
 * 
 */
public class SongInfo {

    private String sid;
    private String prodId;
    private String title;
    private String albumArtUrl;
    private String streamUrl;
    private String detailUrl;
    private int sflag;
    private int trialLength;
    private String trialUrl;
    private ArtistInfo artist;
    @JsonSerialize(include = Inclusion.NON_NULL)
    private UserInfo user;

    @JsonProperty("sid")
    public String getSid() {
        return sid;
    }

    @JsonProperty("sid")
    public void setSid(String sid) {
        this.sid = sid;
    }

    /**
     * @return the prodId
     */
    @JsonProperty("prod_id")
    public String getProdId() {
        return prodId;
    }

    /**
     * @param prodId
     *            the prodId to set
     */
    @JsonProperty("prod_id")
    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("album_art_url")
    public String getAlbumArtUrl() {
        return albumArtUrl;
    }

    @JsonProperty("album_art_url")
    public void setAlbumArtUrl(String albumArtUrl) {
        this.albumArtUrl = albumArtUrl;
    }

    @JsonProperty("stream_url")
    public String getStreamUrl() {
        return streamUrl;
    }

    @JsonProperty("stream_url")
    public void setStreamUrl(String streamUrl) {
        this.streamUrl = streamUrl;
    }

    @JsonProperty("detail_url")
    public String getDetailUrl() {
        return detailUrl;
    }

    @JsonProperty("detail_url")
    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public int getSflag() {
        return sflag;
    }

    public void setSflag(int sflag) {
        this.sflag = sflag;
    }

    @JsonProperty("trial_length")
    public int getTrialLength() {
        return trialLength;
    }

    @JsonProperty("trial_length")
    public void setTrialLength(int trialLength) {
        this.trialLength = trialLength;
    }

    /**
     * @return the trialUrl
     */
    @JsonProperty("trial_url")
    public String getTrialUrl() {
        return trialUrl;
    }

    /**
     * @param trialUrl
     *            the trialUrl to set
     */
    @JsonProperty("trial_url")
    public void setTrialUrl(String trialUrl) {
        this.trialUrl = trialUrl;
    }

    public ArtistInfo getArtist() {
        return artist;
    }

    public void setArtist(ArtistInfo artist) {
        this.artist = artist;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

    @JsonIgnore
    public String getArtistId() {
        return this.getArtist().getAid();
    }
}
