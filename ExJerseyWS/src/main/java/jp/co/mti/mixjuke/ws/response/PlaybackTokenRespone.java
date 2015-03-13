package jp.co.mti.mixjuke.ws.response;

import jp.co.mti.mixjuke.ws.base.ResouceBundleHelper;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * User: nhphuoc Date: 10/21/13 Time: 6:06 PM
 */
public class PlaybackTokenRespone extends AbstractRespone {

    private String error = null;
    private int member_status = -1;

    public PlaybackTokenRespone() {
        super(0);
        this.error = null;
        this.member_status = -1;
    }

    public PlaybackTokenRespone(int result, int status) {
        super(result);
        this.member_status = status;
    }

    public PlaybackTokenRespone(ResultCode code) {
        super(code.getResultCode());
        this.error = ResouceBundleHelper.getMessage(code.getDescription());
    }

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public int getMember_status() {
        return member_status;
    }

    public void setMember_status(int member_status) {
        this.member_status = member_status;
    }
}
