/**
 * 
 */
package jp.co.mti.mixjuke.ws.response;

import jp.co.mti.mixjuke.ws.base.ResouceBundleHelper;

/**
 * @author Xuan Nguyen
 * 
 */
public class ArtistResponse extends AbstractRespone {

    private ArtistInfo artist;
    private String error = null;

    /**
     * @return the artist
     */
    public ArtistInfo getArtist() {
        return artist;
    }

    /**
     * @param artist
     *            the artist to set
     */
    public void setArtist(ArtistInfo artist) {
        this.artist = artist;
    }

    /**
     * @return the error
     */
    public String getError() {
        return error;
    }

    /**
     * @param error
     *            the error to set
     */
    public void setError(String error) {
        this.error = error;
    }

    public ArtistResponse() {
        super();
    }

    public ArtistResponse(ResultCode result) {
        super(result);
        this.error = ResouceBundleHelper.getMessage(result.getDescription());
    }

    public ArtistResponse(ArtistInfo artist) {
        super(ResultCode.NORMAL);
        this.artist = artist;
        this.error = null;
    }

    public ArtistResponse(ResultCode result, String msg) {
        super(result);
        this.error = msg;
    }
}
