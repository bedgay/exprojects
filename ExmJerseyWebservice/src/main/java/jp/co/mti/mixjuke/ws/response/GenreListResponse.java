package jp.co.mti.mixjuke.ws.response;

import java.util.List;

import jp.co.mti.mixjuke.ws.base.ResouceBundleHelper;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @author Xuan Nguyen
 * 
 */
public class GenreListResponse extends AbstractRespone {

    private String error = null;
    private List<GenreInfo> genres;

    public GenreListResponse() {
        super();
    }

    public GenreListResponse(List<GenreInfo> arrayGenre) {
        super(ResultCode.NORMAL);
        this.genres = arrayGenre;
    }

    public GenreListResponse(ResultCode code) {
        super(code.getResultCode());
        this.error = ResouceBundleHelper.getMessage(code.getDescription());
    }

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public List<GenreInfo> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreInfo> genres) {
        this.genres = genres;
    }

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
