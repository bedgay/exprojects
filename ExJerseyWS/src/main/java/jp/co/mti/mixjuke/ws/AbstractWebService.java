/**
 * 
 */
package jp.co.mti.mixjuke.ws;

import java.util.Random;

import jp.co.mti.mixjuke.dom.User;
import jp.co.mti.mixjuke.ws.request.MemberStatus;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author Xuan Nguyen
 * 
 */
public class AbstractWebService {

    @Value("${artist_image_url}")
    private String artistImageUrl;

    @Value("${artist_detail_url}")
    private String artistDetailUrl;

    @Value("${song_album_art_url}")
    private String songAlbumArtUrl;

    @Value("${song_detail_url}")
    private String songDetailUrl;

    @Value("${un_logined}")
    private String unLogin;

    @Value("${base_url}")
    private String baseUrl;

    @Value("${stream_url}")
    private String streamUrl;

    public int maxGenreMIXCount = 30;
    /**
     * @return the artistImageUrl
     */
    public String getArtistImageUrl() {
        return artistImageUrl;
    }

    /**
     * @param artistImageUrl
     *            the artistImageUrl to set
     */
    public void setArtistImageUrl(String artistImageUrl) {
        this.artistImageUrl = artistImageUrl;
    }

    /**
     * @return the artistDetailUrl
     */
    public String getArtistDetailUrl() {
        return artistDetailUrl;
    }

    /**
     * @param artistDetailUrl
     *            the artistDetailUrl to set
     */
    public void setArtistDetailUrl(String artistDetailUrl) {
        this.artistDetailUrl = artistDetailUrl;
    }

    /**
     * @return the songAlbumArtUrl
     */
    public String getSongAlbumArtUrl() {
        return songAlbumArtUrl;
    }

    /**
     * @param songAlbumArtUrl
     *            the songAlbumArtUrl to set
     */
    public void setSongAlbumArtUrl(String songAlbumArtUrl) {
        this.songAlbumArtUrl = songAlbumArtUrl;
    }

    /**
     * @return the songDetailUrl
     */
    public String getSongDetailUrl() {
        return songDetailUrl;
    }

    /**
     * @param songDetailUrl
     *            the songDetailUrl to set
     */
    public void setSongDetailUrl(String songDetailUrl) {
        this.songDetailUrl = songDetailUrl;
    }

    /**
     * @return the unLogin
     */
    public String getUnLogin() {
        return unLogin;
    }

    /**
     * @param unLogin
     *            the unLogin to set
     */
    public void setUnLogin(String unLogin) {
        this.unLogin = unLogin;
    }

    /**
     * @return the baseUrl
     */
    public String getBaseUrl() {
        return baseUrl;
    }

    /**
     * @param baseUrl
     *            the baseUrl to set
     */
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    /**
     * @return the streamUrl
     */
    public String getStreamUrl() {
        return streamUrl;
    }

    /**
     * @param streamUrl
     *            the streamUrl to set
     */
    public void setStreamUrl(String streamUrl) {
        this.streamUrl = streamUrl;
    }

    /**
     * Check id valid (null, blank, empty)
     * 
     * @param id
     * @return boolean value
     */
    protected boolean checkIdValid(String id) {
        return StringUtils.isNotBlank(id);
    }

    /**
     * Check if currently User is valid.
     * 
     * @param uid
     *            User ID.
     * @return Yes: User invalid
     *         <p>
     *         No: User valid.
     */
    public boolean checkUserInvalid(String uid) {
        return this.unLogin.equals(uid.trim());
    }
    
    public boolean isNotAMember(User user) {
        return (user == null) || (user.getStatus() == (short)MemberStatus.CANCELED.getStatus());
    }

    public int calculateActualCount(int listSize, int offset, int reqCount) {
        int actualCount = listSize > (offset + reqCount) ? reqCount
                : (listSize - offset);
        actualCount += offset;
        return actualCount;
    }

    public int randomInteger(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
}
