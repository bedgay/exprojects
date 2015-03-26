/**
 * 
 */
package jp.co.mti.mixjuke.ws.request;

import java.util.List;

/**
 * @author Xuan Nguyen
 * 
 */
public class LocalArtistInput {

    private List<String> artists;

    public LocalArtistInput() {
        super();
    }

    /**
     * @return the artists
     */
    public List<String> getArtists() {
        return artists;
    }

    /**
     * @param artists
     *            the artists to set
     */
    public void setArtists(List<String> artists) {
        this.artists = artists;
    }

}
