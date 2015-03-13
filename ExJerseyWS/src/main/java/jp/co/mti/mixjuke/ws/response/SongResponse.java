/**
 * 
 */
package jp.co.mti.mixjuke.ws.response;

import jp.co.mti.mixjuke.ws.base.ResouceBundleHelper;

/**
 * @author Xuan Nguyen
 * 
 */
public class SongResponse extends AbstractRespone {

    private SongInfo song;
    private String error = null;

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

    /**
     * 
     */
    public SongResponse() {
        super();
    }

    public SongResponse(ResultCode result) {
        super(result);
        this.error = ResouceBundleHelper.getMessage(result.getDescription());
    }

    public SongResponse(SongInfo song) {
        super(ResultCode.NORMAL);
        this.song = song;
        this.error = null;
    }

    public SongResponse(ResultCode result, String msg) {
        super(result);
        this.error = msg;
    }
}
