/**
 * 
 */
package jp.co.mti.mixjuke.ws.response;

import java.util.List;

import jp.co.mti.mixjuke.ws.base.ResouceBundleHelper;

/**
 * @author ntnxuan
 * 
 */
public class AlbumListResponse extends AbstractRespone {
    private String error = null;
    private int total;
    private int offset;
    List<AlbumInfo> albums;

    /**
     * @return the total
     */
    public int getTotal() {
        return total;
    }

    /**
     * @param total
     *            the total to set
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * @return the offset
     */
    public int getOffset() {
        return offset;
    }

    /**
     * @param offset
     *            the offset to set
     */
    public void setOffset(int offset) {
        this.offset = offset;
    }

    /**
     * @return the albums
     */
    public List<AlbumInfo> getAlbums() {
        return albums;
    }

    /**
     * @param albums
     *            the albums to set
     */
    public void setAlbums(List<AlbumInfo> albums) {
        this.albums = albums;
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

    public AlbumListResponse() {
        super();
    }

    public AlbumListResponse(List<AlbumInfo> album) {
        super(ResultCode.NORMAL);
        this.albums = album;
        this.error = null;
    }

    public AlbumListResponse(ResultCode result) {
        super(result);
        this.error = ResouceBundleHelper.getMessage(result.getDescription());
    }

    public AlbumListResponse(ResultCode result, String msg) {
        super(result);
        this.error = msg;
    }
}
