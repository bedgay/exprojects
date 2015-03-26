/**
 * 
 */
package jp.co.mti.mixjuke.ws.response;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * @author ntnxuan
 * 
 */
public class SongListChargeResponse extends SongListResponse {
    public SongListChargeResponse(ArrayList<SongInfo> arrayList) {
        super(arrayList);
    }

    public SongListChargeResponse(ResultCode error) {
        super(error);
    }

    public SongListChargeResponse(ResultCode errorFromRe, String errorDetail) {
        super(errorFromRe, errorDetail);
    }

    public SongListChargeResponse() {
        super();
    }

    @JsonIgnore
    @Override
    public boolean isGetStreamUrl() {
        return true;
    }
}
