/**
 * 
 */
package jp.co.mti.external.re.response;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author Xuan Nguyen
 * 
 */
public class REMIXResponse extends AbstractREResponse {
    @JsonProperty("songs")
    List<RESongInfo> songInfos;

    /**
     * @return the songInfos
     */
    public List<RESongInfo> getSongInfos() {
        return songInfos;
    }

    /**
     * @param songInfos the songInfos to set
     */
    public void setSongInfos(List<RESongInfo> songInfos) {
        this.songInfos = songInfos;
    }

    public boolean checkSuccess(){
        return this.result == 0;
    }
}
