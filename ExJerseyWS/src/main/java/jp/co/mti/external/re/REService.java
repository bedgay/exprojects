package jp.co.mti.external.re;

import java.util.List;
import java.util.Map;

import jp.co.mti.external.re.response.REMIXResponse;
import jp.co.mti.mixjuke.dom.Artist;
import jp.co.mti.mixjuke.dom.User;

public interface REService {

    /**
     * @param uid
     * @param genType
     * @param count
     * @return array of product id
     */
    REMIXResponse getGenreMIX(User user, String genType, int count);

    /**
     * @param uid
     * @param artistId
     * @param count
     * @return array of product id
     */
    REMIXResponse getArtistMIX(User user, String artistId, int count);

    /**
     * @param aids
     *            Seed artist list.
     * @return
     */
    Map<String, List<Artist>> getArtistList(List<String> aids);
}
