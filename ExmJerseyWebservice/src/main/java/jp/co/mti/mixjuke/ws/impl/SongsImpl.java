/**
 *
 */
package jp.co.mti.mixjuke.ws.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import jp.co.mti.external.re.REService;
import jp.co.mti.external.re.response.REMIXResponse;
import jp.co.mti.external.re.response.RESongInfo;
import jp.co.mti.mixjuke.dom.Artist;
import jp.co.mti.mixjuke.dom.Favorite;
import jp.co.mti.mixjuke.dom.Performance;
import jp.co.mti.mixjuke.dom.Rating;
import jp.co.mti.mixjuke.dom.Song;
import jp.co.mti.mixjuke.dom.User;
import jp.co.mti.mixjuke.service.FavoriteService;
import jp.co.mti.mixjuke.service.PerformanceService;
import jp.co.mti.mixjuke.service.RatingService;
import jp.co.mti.mixjuke.service.SongService;
import jp.co.mti.mixjuke.service.UserService;
import jp.co.mti.mixjuke.util.MixjukeUtils;
import jp.co.mti.mixjuke.ws.AbstractWebService;
import jp.co.mti.mixjuke.ws.Songs;
import jp.co.mti.mixjuke.ws.request.SongStatus;
import jp.co.mti.mixjuke.ws.response.ResultCode;
import jp.co.mti.mixjuke.ws.response.SongInfo;
import jp.co.mti.mixjuke.ws.response.SongListChargeResponse;
import jp.co.mti.mixjuke.ws.response.SongListFreeResponse;
import jp.co.mti.mixjuke.ws.response.SongListResponse;
import jp.co.mti.mixjuke.ws.response.SongResponse;
import jp.co.mti.mixjuke.ws.response.SuccessResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Xuan Nguyen
 */
@Component("songsWebService")
@Path("/songs")
public class SongsImpl extends AbstractWebService implements Songs {

    private static final Logger LOGGER = LogManager.getLogger(SongsImpl.class
            .getName());
    @Autowired
    private REService reService;

    @Autowired
    private SongService songService;

    @Autowired
    private UserService userService;

    @Autowired
    private RatingService ratingService;

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private PerformanceService performanceService;

    /*
     * (non-Javadoc)
     * 
     * @see jp.co.mti.mixjuke.webservice.Users#getGenreMIXList(java.lang.String,
     * java.lang.String, int)
     */
    @Override
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public SongListResponse getGenreMIX(@QueryParam("uid") String uid,
            @QueryParam("gid") String gid,
            @QueryParam("count") @DefaultValue("-1") int count,
            @QueryParam("offset") @DefaultValue("-1") int offset,
            @QueryParam("sflag") @DefaultValue("-1") int sflag) {
        if (StringUtils.isEmpty(gid) && (offset != -1 || sflag != -1)) {
            return this.getSongList(uid, sflag, offset, count);
        } else {
            return this.getGenreMIX(uid, gid, count);
        }
    }

    private SongListResponse getGenreMIX(String uid, String gid, int count) {
        LOGGER.info("Triger getGenreMIX");

        // count: Max count of expected result of songs. Max acceptable value is
        // 30, if this param is not present, return 30 songs.
        SongListResponse response = new SongListChargeResponse();
        if (!this.checkIdValid(uid) || !this.checkIdValid(gid)
                || count > maxGenreMIXCount) {
            LOGGER.error("ErrorInParamException");
            return new SongListChargeResponse(ResultCode.ERROR_IN_PARAMETER);
        }
        if (this.checkUserInvalid(uid)) {
            LOGGER.error("NotLoginException");
            return new SongListChargeResponse(ResultCode.NOT_LOGIN);
        }

        User user = userService.findByUid(uid);
        if (isNotAMember(user)) {
            return new SongListChargeResponse(ResultCode.NOT_A_MEMBER);
        }
        if (count < 0) {
            count = maxGenreMIXCount;
        }
        /* Query to RE */
        REMIXResponse reMixRes = reService.getGenreMIX(user, gid, count);
        if (CollectionUtils.isEmpty(reMixRes.getSongInfos())) {
            return new SongListChargeResponse(new ArrayList<SongInfo>());
        }
        List<RESongInfo> reSongs = reMixRes.getSongInfos();
        /* Collect pids from RE */
        List<String> proids = new ArrayList<String>();
        for (RESongInfo songInfo : reSongs) {
            proids.add(songInfo.getPid());
        }
        /* Look up in DB */
        List<Song> songList = songService.getSongListBySongIds(user.getId(),
                proids);
        /* Convert Song array to SongInfo array */
        songList = songService.addReadID(songList);
        List<SongInfo> songInfolist = new ArrayList<SongInfo>();
        for (Song song : songList) {
            songInfolist.add(song.toSongInfo(user, response.isGetStreamUrl(),
                    null));
        }
        // Songs from the same artist should not in continuous order in the song
        // list.
        MixjukeUtils.mixSongs(songInfolist);
        response.setSongs(songInfolist);
        return response;
    }

    private SongListResponse getSongList(String uid, int sflag, int offset,
            int count) {
        LOGGER.info("Triger getSongList");
        if (!this.checkIdValid(uid) || (count < 0) || (offset < 0)
                || !(SongStatus.isValidFlag(sflag))) {
            LOGGER.error("ErrorInParamException");
            return new SongListFreeResponse(ResultCode.ERROR_IN_PARAMETER);
        }
        if (this.checkUserInvalid(uid)) {
            LOGGER.error("NotLoginException");
            return new SongListFreeResponse(ResultCode.NOT_LOGIN);
        }
        /* Look up in DB */
        User user = userService.findByUid(uid);
        if (isNotAMember(user)) {
            return new SongListFreeResponse(ResultCode.NOT_A_MEMBER);
        }
        int total = songService.getTotalSongListBySflag(user.getId(), sflag)
                .intValue();
        List<Song> songList = songService.getSongListByFlag(user.getId(),
                sflag, offset, count);
        SongListResponse response = new SongListFreeResponse();
        response.setTotal(total);
        response.setOffset(offset);
        /* Convert Song array to SongInfo array */
        songList = songService.addReadID(songList);
        List<SongInfo> songInfolist = new ArrayList<SongInfo>();
        for (Song song : songList) {
            songInfolist.add(song.toSongInfo(user, response.isGetStreamUrl(),
                    null));
        }
        response.setSongs(songInfolist);
        return response;
    }

    @Override
    @GET
    @Path("/{sid}")
    @Produces(MediaType.APPLICATION_JSON)
    public SongResponse getSongInfo(@QueryParam("uid") String uid,
            @PathParam("sid") String sid) {
        LOGGER.info("Triger getSongInfo");
        if (!this.checkIdValid(uid) || !this.checkIdValid(sid)) {
            LOGGER.error("ErrorInParamException");
            return new SongResponse(ResultCode.ERROR_IN_PARAMETER);
        }
        if (this.checkUserInvalid(uid)) {
            LOGGER.error("NotLoginException");
            return new SongResponse(ResultCode.NOT_LOGIN);
        }
        /* Look up in DB */
        User user = userService.findByUid(uid);
        // if we cannot find user in the database
        if (isNotAMember(user)) {
            return new SongResponse(ResultCode.NOT_A_MEMBER);
        }
        List<String> ids = new ArrayList<String>();
        ids.add(sid);
        Song song = null;
        List<Song> songList = songService.getSongListBySongIds(user.getId(),
                ids);
        songList = songService.addReadID(songList);
        if (!CollectionUtils.isEmpty(songList)) {
            song = songList.get(0);
        }
        if (song == null) {
            return new SongResponse(ResultCode.CAN_FOUND_SPEC_RESOURCE);
        }
        return new SongResponse(song.toSongInfo(user, false, null));
    }

    @Override
    @PUT
    @Path("/{sid}")
    @Produces(MediaType.APPLICATION_JSON)
    public SuccessResponse updateSongStatus(@QueryParam("uid") String uid,
            @PathParam("sid") String sid,
            @QueryParam("sflag") @DefaultValue("-1") int sflag) {
        LOGGER.info("Triger updateSongStatus");
        if (!this.checkIdValid(uid) || !this.checkIdValid(sid)
                || !SongStatus.isValidFlag(sflag)) {
            LOGGER.error("ErrorInParamException");
            return new SuccessResponse(ResultCode.ERROR_IN_PARAMETER);
        }
        if (this.checkUserInvalid(uid)) {
            LOGGER.error("NotLoginException");
            return new SuccessResponse(ResultCode.NOT_LOGIN);
        }

        /* Look up in DB */
        User user = userService.findByUid(uid);
        if (isNotAMember(user)) {
            return new SuccessResponse(ResultCode.NOT_A_MEMBER);
        }

        List<String> ids = new ArrayList<String>();
        ids.add(sid);
        Song song = null;
        List<Song> songList = songService.getSongListBySongIds(user.getId(),
                ids);
        if (!CollectionUtils.isEmpty(songList)) {
            song = songList.get(0);
        }
        if (song == null) {
            return new SuccessResponse(ResultCode.CAN_FOUND_SPEC_RESOURCE);
        }
        boolean rate = false;
        Rating rating = null;

        for (Rating r : song.getRatings()) {
            if (user.getId().equals(r.getUser().getId())) {
                rate = true;
                // Please don't update timestamp of good song
                // if they are already been good-ed.
                // keep rating is null to indicator song has already been
                // good-ed.
                if (r.getSflag() != sflag) {
                    rating = r;
                    rating.setSflag(sflag);
                    break;
                }
            }
        }
        if (!rate) {
            rating = new Rating();
            rating.setSflag(sflag);
            rating.setUser(user);
            rating.setSong(song);
        }

        // When user update a song's slag to 2(good), add artist of this song to
        // the user's favorite artist list(if not yet).
        // If user set a 2(good) song to 0 or 1, keep the artist being
        // favorited.
        // If song status changed we should update the song's status always.
        // But only favorite the artist when the song is set to good.
        // Don't remove favorite the artist when the song change from good to
        // bad or neutral.

        List<Favorite> favoriteList = new ArrayList<Favorite>();
        // rating != null to indicator song hasn't mark goo-ed song or favorite
        // yet.
        if (rating != null && sflag == SongStatus.GOOD.getSflag()) {
            List<Performance> perList = new ArrayList<Performance>(
                    song.getPerformances());
            for (Performance performance : perList) {
                Artist artist = performance.getArtist();
                boolean favorited = false;
                for (Favorite f : artist.getFavorites()) {
                    if (user.getId().equals(f.getUser().getId())) {
                        favorited = true;// user had favorited this artist -> do
                                         // nothing
                        break;
                    }
                }
                // Please don't update timestamp of favorite artist if they are
                // already been favorited.
                // if had not favorite yet -> update
                if (!favorited && !favoriteList.contains(artist)) {
                    favorited = false;
                    // make sure favorite did not exist in list before.
                    for (Favorite favorite : favoriteList) {
                        if (favorite.getArtist().getId().equals(artist.getId())) {
                            favorited = true;
                            break;
                        }
                    }
                    if (!favorited) {
                        Favorite f = new Favorite();
                        f.setArtist(artist);
                        f.setUser(user);
                        favoriteList.add(f);
                    }
                }
            }
        }

        ratingService.updateRatingAndFavorite(rating, favoriteList);

        return new SuccessResponse();
    }

}
