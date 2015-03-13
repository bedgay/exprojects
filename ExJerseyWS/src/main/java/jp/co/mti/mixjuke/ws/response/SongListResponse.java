/**
 * 
 */
package jp.co.mti.mixjuke.ws.response;

import java.util.List;

import jp.co.mti.mixjuke.ws.base.ResouceBundleHelper;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @author Xuan Nguyen
 * 
 */
public abstract class SongListResponse extends AbstractRespone implements
        SongList {

    protected List<SongInfo> songs;
    protected String error = null;
    private Integer total;
    private Integer offset;

    /**
     * Default constructor.
     */
    public SongListResponse() {
        super();
    }

    /**
     * This constructor for successful respone
     * 
     * @param result
     *            Include code and description.
     * @param songs
     *            The list of songs.
     */
    public SongListResponse(Integer offset, Integer total, List<SongInfo> songs) {
        super(ResultCode.NORMAL);
        this.songs = songs;
        this.error = null;
        this.total = total;
        this.offset = offset;
    }

    public SongListResponse(List<SongInfo> songs) {
        super(ResultCode.NORMAL);
        this.songs = songs;
        this.error = null;
    }

    /**
     * This constructor for failure respone
     * 
     * @param result
     *            Code & descriptor for error.
     * @param msg
     *            The source to get messages
     */
    public SongListResponse(ResultCode result) {
        super(result);
        this.error = ResouceBundleHelper.getMessage(result.getDescription());
    }

    public SongListResponse(ResultCode result, String msg) {
        super(result);
        this.error = msg;
    }

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public List<SongInfo> getSongs() {
        return songs;
    }

    public void setSongs(List<SongInfo> songs) {
        this.songs = songs;
    }

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    /**
     * @return the total
     */
    public Integer getTotal() {
        return total;
    }

    /**
     * @param total
     *            the total to set
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     * @return the offset
     */
    public Integer getOffset() {
        return offset;
    }

    /**
     * @param offset
     *            the offset to set
     */
    public void setOffset(Integer offset) {
        this.offset = offset;
    }

}
