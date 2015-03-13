package jp.co.mti.mixjuke.ws.response;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @author Xuan Nguyen
 * 
 */
public class ArtistInfo {

    private String aid;
    private String name;
    private String imageUrl;
    private String detailUrl;
    private String mixUrl;
    private boolean favorited;
    private String seed_name;

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("image_url")
    public String getImageUrl() {
        return imageUrl;
    }

    @JsonProperty("image_url")
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @JsonProperty("detail_url")
    public String getDetailUrl() {
        return detailUrl;
    }

    @JsonProperty("detail_url")
    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    @JsonProperty("mix_url")
    public String getMixUrl() {
        return mixUrl;
    }

    @JsonProperty("mix_url")
    public void setMixUrl(String mixUrl) {
        this.mixUrl = mixUrl;
    }

    public boolean isFavorited() {
        return favorited;
    }

    public void setFavorited(boolean favorited) {
        this.favorited = favorited;
    }

    /**
     * @return the seed_name
     */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public String getSeed_name() {
        return seed_name;
    }

    /**
     * @param seed_name
     *            the seed_name to set
     */
    public void setSeed_name(String seed_name) {
        this.seed_name = seed_name;
    }
}
