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
public class ArtistListResponse extends AbstractRespone {

    private List<ArtistInfo> artists;
    private String error = null;
    private Integer total;
    private Integer offset;

    public ArtistListResponse() {
        super();
    }

    /**
     * @param result
     */
    public ArtistListResponse(Integer offset, Integer total,
            List<ArtistInfo> artistInfos) {
        super(ResultCode.NORMAL);
        this.artists = artistInfos;
        this.total = total;
        this.offset = offset;
    }

    public ArtistListResponse(ResultCode result) {
        super(result);
        this.error = ResouceBundleHelper.getMessage(result.getDescription());
    }

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    /**
     * @return the artists
     */
    public List<ArtistInfo> getArtists() {
        return artists;
    }

    /**
     * @param artists
     *            the artists to set
     */
    public void setArtists(List<ArtistInfo> artists) {
        this.artists = artists;
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
     * @return the count
     */
    public Integer getOffset() {
        return offset;
    }

    /**
     * @param offset
     *            the count to set
     */
    public void setOffset(Integer offset) {
        this.offset = offset;
    }

}
