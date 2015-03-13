/**
 * 
 */
package jp.co.mti.mixjuke.ws.response;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author ntnxuan
 * 
 */
public class AlbumInfo {
    private String albumId;
    private String albumTitle;
    private String artistId;
    private String artistName;
    private String albumArtUrl;
    private String songsUrl;

    /**
     * @return the albumId
     */
    @JsonProperty("album_id")
    public String getAlbumId() {
        return albumId;
    }

    /**
     * @param albumId
     *            the albumId to set
     */
    @JsonProperty("album_id")
    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    /**
     * @return the albumTitle
     */
    @JsonProperty("album_title")
    public String getAlbumTitle() {
        return albumTitle;
    }

    /**
     * @param albumTitle
     *            the albumTitle to set
     */
    @JsonProperty("album_title")
    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    /**
     * @return the artistId
     */
    @JsonProperty("artist_id")
    public String getArtistId() {
        return artistId;
    }

    /**
     * @param artistId
     *            the artistId to set
     */
    @JsonProperty("artist_id")
    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    /**
     * @return the artistName
     */
    @JsonProperty("artist_name")
    public String getArtistName() {
        return artistName;
    }

    /**
     * @param artistName
     *            the artistName to set
     */
    @JsonProperty("artist_name")
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    /**
     * @return the albumArtUrl
     */
    @JsonProperty("album_art_url")
    public String getAlbumArtUrl() {
        return albumArtUrl;
    }

    /**
     * @param albumArtUrl
     *            the albumArtUrl to set
     */
    @JsonProperty("album_art_url")
    public void setAlbumArtUrl(String albumArtUrl) {
        this.albumArtUrl = albumArtUrl;
    }

    /**
     * @return the songsUrl
     */
    @JsonProperty("songs_url")
    public String getSongsUrl() {
        return songsUrl;
    }

    /**
     * @param songsUrl
     *            the songsUrl to set
     */
    @JsonProperty("songs_url")
    public void setSongsUrl(String songsUrl) {
        this.songsUrl = songsUrl;
    }
}
