/**
 * 
 */
package jp.co.mti.mixjuke.ws.response;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * @author ntnxuan
 * 
 */
public class SongListFreeResponse extends SongListResponse {
    public SongListFreeResponse(ResultCode error) {
        super(error);
    }

    public SongListFreeResponse() {
        super();
    }

    @JsonIgnore
    @Override
    public boolean isGetStreamUrl() {
        return false;
    }
}
