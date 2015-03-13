package jp.co.mti.external.re.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import jp.co.mti.external.re.REService;
import jp.co.mti.external.re.response.REMIXResponse;
import jp.co.mti.external.re.response.RESongInfo;
import jp.co.mti.mixjuke.dom.Artist;
import jp.co.mti.mixjuke.dom.GroupArtist;
import jp.co.mti.mixjuke.dom.Song;
import jp.co.mti.mixjuke.dom.User;
import jp.co.mti.mixjuke.service.GroupArtistService;
import jp.co.mti.mixjuke.service.SongService;
import jp.co.mti.mixjuke.service.UserService;
import jp.co.mti.mixjuke.util.MixjukeUtils;
import jp.co.mti.mixjuke.util.PropertyUtil;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class REServiceImpl implements REService {

    private static final Logger LOGGER = LogManager
            .getLogger(REServiceImpl.class.getName());

    private static final int SONG_ARRANGE = 1000;

    @Autowired
    private SongService songService;
    @Autowired
    private GroupArtistService groupArtistService;
    @Autowired
    private UserService userService;

    private boolean enableRule1;

    public REServiceImpl() {
        String s = PropertyUtil.getProperty("enableRule1");
        if (s.equals("true")) {
            enableRule1 = true;
        }
    }

    /**
     * Generate recommended song base on SEED genre
     * <p/>
     * Step1: Select(randomly) artist to be played from artist group belong to
     * SEED genre Step2: Select(randomly) song from selected artist
     * <p/>
     * 2.1: Select songs belong to artist
     * <p/>
     * Restriction:
     * <p/>
     * Max 3 songs can be chosed from the same artist(in step 2)
     * <p/>
     * Songs from the same artist should not in continuous order in the song
     * list.
     * <p/>
     * Do NOT provide non-available song. Songs returned in the response MUST
     * follow Appendix 2 Rule 1~2
     * <p/>
     * Rule 1. Content available duration
     * <p/>
     * Start date time = MAX(CONTENT_RELEASE_DATE + CONTENT_RELEASE_DATE,
     * CONTENT_RELEASE_DATE)
     * <p/>
     * End time = android_release_end_datetime
     * <p/>
     * Rule 2. Content availability per Member ship
     * <p/>
     * Availability = ANDROID_{MEMBER_STATUS}_ENABLE_FLG |
     * ANDROID_DELIVERY_ENABLE_FLG
     */
    @Override
    public REMIXResponse getGenreMIX(User user, String gid, int count) {
        LOGGER.info("RE getGenreMIX begin:" + new Date());
        List<String> songIds = songService.getListIdByGenreId(gid);
        List<Song> currentSong = getSongListByGenreMixRule(songIds, count,
                user, SONG_ARRANGE, null);
        LOGGER.info("RE getGenreMIX end:" + new Date());
        REMIXResponse response = new REMIXResponse();
        response.setResult(0);
        response.setTotal(currentSong.size());
        List<RESongInfo> songInfos = new ArrayList<RESongInfo>();
        for (Song song : currentSong) {
            songInfos.add(new RESongInfo(song.getId()));
        }
        response.setSongInfos(songInfos);
        return response;
    }

    /**
     * Include below rules:
     * <p/>
     * 1. Start time = MAX ( CONTENT_RELEASE_DATE + WINDOW_DAYS,
     * ANDROID_RELEASE_START_DATETIME )
     * <p/>
     * 2. End time = ANDROID_RELEASE_END_DATETIME
     * <p/>
     * 3. Availability = ANDROID_{MEMBER_STATUS}_ENABLE_FLG |
     * ANDROID_DELIVERY_ENABLE_FLG
     * <p/>
     * 4. Max 3 songs can be chosed from the same artist
     * <p/>
     * Exclude below rules:
     * <p/>
     * 1. Songs from the same artist should not in continuous order in the song
     * list.
     * 
     * @param songIds
     * @param count
     * @param user
     * @param seedArtist
     *           the id of seed artist.
     * @return
     */
    private List<Song> getSongListByGenreMixRule(List<String> songIds,
            int count, User user, int arrange, String seedArtist) {
        List<Song> currentSongs = new ArrayList<Song>();
        if (songIds.size() > 0) {
            int beginIndex = 0;
            int endIndex = arrange;
            while (currentSongs.size() < count && beginIndex < songIds.size()) {
                if (endIndex > songIds.size()) {
                    endIndex = songIds.size();
                }
//                LOGGER.info("In range: " + beginIndex +", " + endIndex);
                List<String> remainActualSongIds = songIds.subList(beginIndex,
                        endIndex);
                List<Song> songs = songService
                        .getListByIdsWFetchArtistAuth(remainActualSongIds);
                // check rule 1&2 on new songs
                songs = MixjukeUtils.checkRule1And2(songs, user,
                        this.enableRule1);
//                LOGGER.info("num of song satify rule1&2: " + songs.size());
                // check GenreMix rule on all current songs
                currentSongs.addAll(songs);
                currentSongs = MixjukeUtils.checkMax3SongPerArtistRule(
                        currentSongs, count, seedArtist);
//                LOGGER.info("num of song satify rule max 3 song: " + currentSongs.size());
                beginIndex += arrange;
                endIndex += arrange;
            }
        }
        return currentSongs;
    }

    @Override
    public REMIXResponse getArtistMIX(User user, String artistId, int count) {
        LOGGER.info("RE getArtistMIX begin:" + new Date());
        List<String> aids = new ArrayList<String>();
        aids.add(artistId);
        // get all group which artist belong to
        List<String> groupIds = groupArtistService.getGroupIdsByArtistIds(aids);
        //LOGGER.info("num of groups: " + Arrays.asList(groupIds));
        // get all artist belong to group
        List<String> songIds = songService.getListIdByGroupIds(groupIds);
//        LOGGER.info("num of song: " + songIds.size());
        List<Song> currentSong = getSongListByGenreMixRule(songIds, count,
                user, SONG_ARRANGE, artistId);
        LOGGER.info("RE getArtistMIX end:" + new Date());
        REMIXResponse response = new REMIXResponse();
        response.setResult(0);
        response.setTotal(currentSong.size());
        List<RESongInfo> songInfos = new ArrayList<RESongInfo>();
        for (Song song : currentSong) {
            songInfos.add(new RESongInfo(song.getId()));
        }
        response.setSongInfos(songInfos);
        return response;
    }

    @Override
    public Map<String, List<Artist>> getArtistList(List<String> aids) {
        // get all group which artist belong to
        List<String> groupIds = groupArtistService.getGroupIdsByArtistIds(aids);
        List<GroupArtist> groupArtists = groupArtistService
                .getArtistIdsByGroupIds(groupIds);

        Map<String, List<Artist>> artistMap = new TreeMap<String, List<Artist>>();
        List<Artist> artist = null;
        String groupId = "";
        int count = 0;
        for (GroupArtist groupArtist : groupArtists) {
            if (!groupId.equals(groupArtist.getGroup().getId())) {
                if (!groupId.equals("")) {
                    artistMap.put(groupId, artist);
                }
                groupId = groupArtist.getGroup().getId();
                artist = new ArrayList<Artist>();
            }
            artist.add(groupArtist.getArtist());

            if (++count == groupArtists.size()) {
                artistMap.put(groupId, artist);
            }
        }
        return artistMap;
    }

}
