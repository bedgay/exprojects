/**
 * 
 */
package jp.co.mti.mixjuke.ws.response;

import java.util.List;

/**
 * @author ntnxuan
 * 
 */
public class AlbumResponse extends AbstractRespone {
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
}
