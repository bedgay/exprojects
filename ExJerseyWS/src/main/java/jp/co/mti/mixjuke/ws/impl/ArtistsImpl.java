/**
 *
 */
package jp.co.mti.mixjuke.ws.impl;

import java.math.BigDecimal;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
import jp.co.mti.mixjuke.dom.Album;
import jp.co.mti.mixjuke.dom.Artist;
import jp.co.mti.mixjuke.dom.Favorite;
import jp.co.mti.mixjuke.dom.ModelList;
import jp.co.mti.mixjuke.dom.Song;
import jp.co.mti.mixjuke.dom.User;
import jp.co.mti.mixjuke.service.AlbumService;
import jp.co.mti.mixjuke.service.ArtistService;
import jp.co.mti.mixjuke.service.FavoriteService;
import jp.co.mti.mixjuke.service.PerformanceService;
import jp.co.mti.mixjuke.service.SongService;
import jp.co.mti.mixjuke.service.UserService;
import jp.co.mti.mixjuke.util.MixjukeUtils;
import jp.co.mti.mixjuke.ws.AbstractWebService;
import jp.co.mti.mixjuke.ws.Artists;
import jp.co.mti.mixjuke.ws.request.LocalArtistInput;
import jp.co.mti.mixjuke.ws.response.AlbumInfo;
import jp.co.mti.mixjuke.ws.response.AlbumListResponse;
import jp.co.mti.mixjuke.ws.response.ArtistInfo;
import jp.co.mti.mixjuke.ws.response.ArtistListResponse;
import jp.co.mti.mixjuke.ws.response.ArtistResponse;
import jp.co.mti.mixjuke.ws.response.ResultCode;
import jp.co.mti.mixjuke.ws.response.SongInfo;
import jp.co.mti.mixjuke.ws.response.SongListChargeResponse;
import jp.co.mti.mixjuke.ws.response.SongListResponse;
import jp.co.mti.mixjuke.ws.response.SuccessResponse;
import jp.co.mti.mixjuke.ws.util.JapaneseUtils;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author Xuan Nguyen
 *
 */
@Component("artistWebService")
@Path("/artists")
public class ArtistsImpl extends AbstractWebService implements Artists {
    private static final Logger LOGGER = LogManager.getLogger(ArtistsImpl.class.getName());
    @Autowired
    private REService reService;
    @Autowired
    private ArtistService artistService;
    @Autowired
    private SongService songService;
    @Autowired
    private UserService userService;
    @Autowired
    private FavoriteService favoriteService;
    @Autowired
    private PerformanceService performanceService;
    @Autowired
    private AlbumService albumService;

    @Override
    @GET
    @Path("/{aid}/songs")
    @Produces(MediaType.APPLICATION_JSON)
    public SongListResponse getArtistMIX(@QueryParam("uid")
    String uid, @PathParam("aid")
    String aid, @QueryParam("count")
    @DefaultValue("-1")
    int count) {
        LOGGER.info("Triger getArtistMIX");

        SongListResponse response = new SongListChargeResponse();

        // count: Max count of expected result of songs. Max acceptable value is
        // 30, if this param is not present, return 30 songs.
        if (!this.checkIdValid(uid) || !this.checkIdValid(aid) ||
                (count > maxGenreMIXCount)) {
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
        REMIXResponse reMixRes = reService.getArtistMIX(user, aid, count);

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

        if (CollectionUtils.isEmpty(songList)) {
            return new SongListChargeResponse(new ArrayList<SongInfo>());
        }

        /* Convert Song array to SongInfo array */
        songList = songService.addReadID(songList);

        List<SongInfo> songInfolist = new ArrayList<SongInfo>();

        for (Song song : songList) {
            songInfolist.add(song.toSongInfo(user, response.isGetStreamUrl(),
                    aid));
        }

        // Songs from the same artist should not in continuous order in the song
        // list.
        MixjukeUtils.mixSongs(songInfolist);

        // First song in song list MUST belongs to SEED artist
        // CHG-S nhphuoc 140206 improve code
        // int indexOfSeedArtist = 0;
        // for (int i = 0; i < songInfolist.size(); i++) {
        // SongInfo song = songInfolist.get(i);
        // if (aid.equals(song.getArtistId())) {
        // indexOfSeedArtist = i;
        // break;
        // }
        // }
        // if (indexOfSeedArtist != 0) {
        // Collections.swap(songInfolist, 0, indexOfSeedArtist);
        // }
        for (int i = 1; i < songInfolist.size(); i++) {
            SongInfo song = songInfolist.get(i);

            if (aid.equals(song.getArtistId())) {
                Collections.swap(songInfolist, 0, i);
                break;
            }
        }

        // CHG-E nhphuoc
        response.setSongs(songInfolist);

        return response;
    }

    @Override
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArtistListResponse getArtistList(@QueryParam("uid")
    String uid, @QueryParam("count")
    @DefaultValue("-1")
    int count, @QueryParam("name")
    String name, @QueryParam("offset")
    @DefaultValue("-1")
    int offset) {
        LOGGER.info("Triger getArtistList");
        if(this.checkIdValid(uid)){
            if(name != null || (offset != -1) || (count != -1)){
                return this.getSearchArtistList(uid, name, offset, count);
            }else{
                return this.getArtistList(uid);
            }
        }

        LOGGER.error("ErrorInParamException");
        return new ArtistListResponse(ResultCode.ERROR_IN_PARAMETER);
    }

    @GET
    @Path("/hot")
    @Produces(MediaType.APPLICATION_JSON)
    public ArtistListResponse getHotArtistList(@QueryParam("uid")
    String uid, @QueryParam("count")
    @DefaultValue("-1")
    int count, @QueryParam("offset")
    @DefaultValue("-1")
    int offset) {
        LOGGER.info("Triger getHotArtistList");

        ArtistListResponse response = null;

        LOGGER.info("Triger getSearchArtist");

        if (this.checkUserInvalid(uid)) {
            LOGGER.error("NotLoginException");
            response = new ArtistListResponse(ResultCode.NOT_LOGIN);

            return response;
        }

        User user = userService.findByUid(uid);

        if (isNotAMember(user)) {
            LOGGER.error("NotAMemberException");
            response = new ArtistListResponse(ResultCode.NOT_A_MEMBER);

            return response;
        }

        if ((count <= 0) || (offset < 0)) {
            LOGGER.error("ErrorInParams");

            return new ArtistListResponse(ResultCode.ERROR_IN_PARAMETER);
        }

        ModelList<Artist> modelList = artistService.getHotArtistListBySearch(uid,
                offset, count);
        List<ArtistInfo> infos = new ArrayList<ArtistInfo>();

        for (Artist artist : modelList.getList()) {
            infos.add(artist.toArtistInfo(uid));
        }

        response = new ArtistListResponse(offset,
                new BigDecimal(modelList.getTotal()).intValueExact(), infos);
        response.setResult(ResultCode.NORMAL.getResultCode());

        return response;
    }

    /**
     * This API provide a mixed list of favorated artist and recommended artist.
     * <p>
     *
     * @param uid
     * @param recount
     * @param count
     * @return
     */
	private ArtistListResponse getArtistList(String uid) {
        LOGGER.info("Triger getArtistList");

        if (this.checkUserInvalid(uid)) {
            LOGGER.error("NotLoginException");

            return new ArtistListResponse(ResultCode.NOT_LOGIN);
        }

        User user = userService.findByUid(uid);

        if (isNotAMember(user)) {
            LOGGER.error("NotAMemberException");

            return new ArtistListResponse(ResultCode.NOT_A_MEMBER);
        }

        // Max favorite artist in response is 10, max total is 30.
        // "ft" is total favorite,"f" is favorite count, "r" is recommendation
        int ft = 0;

        // Max favorite artist in response is 10, max total is 30.
        // "ft" is total favorite,"f" is favorite count, "r" is recommendation
        int f = 0;

        // Max favorite artist in response is 10, max total is 30.
        // "ft" is total favorite,"f" is favorite count, "r" is recommendation
        int r = 0;
        ft = favoriteService.getTotalFavoriteListByUid(user.getId()).intValue();

        if (ft == 0) {
            return new ArtistListResponse(null, 0, new ArrayList<ArtistInfo>());
        }

        if (ft <= 5) {
            f = ft;
            r = f * 5;
        } else if (ft <= 10) {
            f = ft;
            r = 30 - f;
        } else {
            r = 20;
            f = 10;
        }

        List<Favorite> favoriteList = new ArrayList<Favorite>();
        // If favorite artists are no more than 10, return all of them. If there
        // are more than 10 favorite artist, randomly pickup 10 from them.
        favoriteList = favoriteService.getListByUid(user.getId(), 0, f);

        List<String> idArtistList = new ArrayList<String>();

        for (Favorite favorite : favoriteList) {
            idArtistList.add(favorite.getArtist().getId());
        }

        // Chose some of the favorite artist as SEED artist(should random),
        // generate recommended artists by randomly select artists from artist
        // group witch SEED artists belongs to.
        Random rand = new Random();
        int randomSeedArtist = rand.nextInt(idArtistList.size());

        Map<String, String> reMapArtist = new TreeMap<String, String>();
        Map<String, List<Artist>> groupArtists = reService.getArtistList(idArtistList.subList(
                    0, randomSeedArtist + 1));

        int count = 0;

        for (String key : groupArtists.keySet()) {
            Artist favoriteArtist = null;

            for (Artist artist : groupArtists.get(key)) {
                if (isFavoriteArtist(favoriteList, artist)) {
                    favoriteArtist = artist;

                    break;
                }
            }

            for (Artist artist : groupArtists.get(key)) {
                if (!idArtistList.contains(artist.getId())) {
                    reMapArtist.put(artist.getId(), favoriteArtist.getName());

                    if (++count == r) {
                        break;
                    }
                }
            }

            if (count == r) {
                break;
            }
        }

        for (Entry<String, String> entry : reMapArtist.entrySet()) {
            idArtistList.add(entry.getKey());
        }

        List<Artist> reArtist = new ArrayList<Artist>();

        if (!CollectionUtils.isEmpty(idArtistList)) {
            // need to fetch favorite to convert to ArtistInfo
            reArtist = artistService.getArtistListByPropertyWFetchFavorite("id",
                    idArtistList);
        }
        
        // update seed artist name;
        sortAtistsByNameKana(reArtist);
        ArtistListResponse response = getArtistListResponse(user, reArtist);

        for (ArtistInfo ainfo : response.getArtists()) {
            ainfo.setSeed_name(reMapArtist.get(ainfo.getAid()));
        }

        return response;
    }
    
	private void sortAtistsByNameKana(List<Artist> artists) {
        final Collator collator = Collator.getInstance(Locale.JAPANESE);
        collator.setStrength(Collator.SECONDARY);
        Collections.sort(artists, new Comparator<Artist>() {
			@Override
			public int compare(Artist arg0, Artist arg1) {
				return collator.compare(arg0.getNameKana(), arg1.getNameKana());
			}
        });
    }

    private Boolean isFavoriteArtist(List<Favorite> favoriteList, Artist artist) {
        for (Favorite favorite : favoriteList) {
            if (favorite.getArtist().getId().equals(artist.getId())) {
                return Boolean.TRUE;
            }
        }

        return Boolean.FALSE;
    }

    private ArtistListResponse getArtistListResponse(User user,
        List<Artist> reArtist) {
        ArtistListResponse response = new ArtistListResponse();
        response.setTotal(reArtist.size());

        List<ArtistInfo> artistInfos = artistService.getArtistInfoList(user.getId(),
                reArtist);
        response.setArtists(artistInfos);

        return response;
    }

    private ArtistListResponse getArtistListResponse(User user,
        List<Artist> reArtist, Integer offset, Integer total) {
        ArtistListResponse response = getArtistListResponse(user, reArtist);
        response.setOffset(offset);
        response.setTotal(total);

        return response;
    }

    private ArtistListResponse getSearchArtistList(String uid, String name,
        int offset, int count) {
        LOGGER.info("Triger getSearchArtist");
        if(StringUtils.isBlank(name) || offset < 0 || count <= 0){
            LOGGER.error("ErrorInParamException");
            return new ArtistListResponse(ResultCode.ERROR_IN_PARAMETER);
        }
        if (this.checkUserInvalid(uid)) {
            LOGGER.error("NotLoginException");

            return new ArtistListResponse(ResultCode.NOT_LOGIN);
        }

        User user = userService.findByUid(uid);

        if (isNotAMember(user)) {
            LOGGER.error("NotAMemberException");

            return new ArtistListResponse(ResultCode.NOT_A_MEMBER);
        }
        String[] nameConverted = convertSearchString(name);
        ModelList<Artist> artistList = artistService.getArtistListBySearchName(user.getId(),
                nameConverted, offset, count);

        sortAtistsByNameKana(artistList.getList());
        return getArtistListResponse(user, artistList.getList(), offset,
            artistList.getTotal().intValue());
    }
    
    private String[] convertSearchString(String input){
        String[] toSearch = null;
        if (JapaneseUtils.hasJapanese(input))
        {
            String[] ret = new String[4];
            ret[0] = input;
            ret[1] = JapaneseUtils.toHiragana(JapaneseUtils.toFullWidth(input)); // hiragana
            ret[2] = JapaneseUtils.toKatakana(ret[1]); // full-width katakana
            ret[3] = JapaneseUtils.toHalfWidth(ret[2]); // half-width katakana
            toSearch = ret;
        }
        else
        {
            toSearch = new String[] { input };
        }
        return toSearch;
    }
    
    @Override
    @PUT
    @Path("/favorites")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public SuccessResponse uploadLocalArtist(@QueryParam("uid")
    String uid, String artistsString) {
        LOGGER.info("Triger uploadLocalArtist");

        ObjectMapper om = new ObjectMapper();
        LocalArtistInput artists;

        try {
            artists = om.readValue(artistsString, LocalArtistInput.class);
        } catch (Exception e1) {
            LOGGER.error("ErrorInParamException");

            return new SuccessResponse(ResultCode.ERROR_IN_PARAMETER);
        }

        if (artists.getArtists().size() > 50) {
            LOGGER.error("ErrorInParamException");

            return new SuccessResponse(ResultCode.ERROR_IN_PARAMETER);
        }

        if (!this.checkIdValid(uid) ||
                CollectionUtils.isEmpty(artists.getArtists())) {
            LOGGER.error("ErrorInParamException");

            return new SuccessResponse(ResultCode.ERROR_IN_PARAMETER);
        }

        if (this.checkUserInvalid(uid)) {
            LOGGER.error("NotLoginException");

            return new SuccessResponse(ResultCode.NOT_LOGIN);
        }

        User user = userService.findByUid(uid);

        if (isNotAMember(user)) {
            LOGGER.error("NotAMemberException");

            return new SuccessResponse(ResultCode.NOT_A_MEMBER);
        }

        // Get artist object list base on artist name list
        List<Artist> artistList = artistService.getArtistListByProperty("name",
                artists.getArtists(), user.getId());

        List<Favorite> favorites = new ArrayList<Favorite>();

        for (Artist artist : artistList) {
            if (CollectionUtils.isEmpty(artist.getFavorites())) {
                Favorite f = new Favorite();
                f.setArtist(artist);
                f.setUser(user);
                favorites.add(f);
            }
        }

        favoriteService.addList(favorites);

        return new SuccessResponse();
    }

    @Override
    @PUT
    @Path("/favorites/{aid}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public SuccessResponse addFavoriteArtist(@QueryParam("uid")
    String uid, @PathParam("aid")
    String aid) {
        LOGGER.info("Triger addFavoriteArtist");

        if (!this.checkIdValid(uid) || StringUtils.isBlank(aid)) {
            LOGGER.error("ErrorInParamException");

            return new SuccessResponse(ResultCode.ERROR_IN_PARAMETER);
        }

        if (this.checkUserInvalid(uid)) {
            LOGGER.error("NotLoginException");

            return new SuccessResponse(ResultCode.NOT_LOGIN);
        }

        Artist artist = artistService.findById(aid);
        User user = userService.findByUid(uid);

        if (isNotAMember(user)) {
            LOGGER.error("NotAMemberException");

            return new SuccessResponse(ResultCode.NOT_A_MEMBER);
        }

        if (artist != null) {
            Favorite f = favoriteService.findByUidAnfAid(user.getId(), aid);

            if (f == null) {
                f = new Favorite();
                f.setArtist(artist);
                f.setUser(user);
                favoriteService.saveOrUpdate(f);
            }
        }

        return new SuccessResponse();
    }

    @Override
    @DELETE
    @Path("/favorites/{aid}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public SuccessResponse deleteFavoriteArtist(@QueryParam("uid")
    String uid, @PathParam("aid")
    String aid) {
        LOGGER.info("Triger deleteFavoriteArtist");

        if (!this.checkIdValid(uid) || StringUtils.isBlank(aid)) {
            LOGGER.error("ErrorInParamException");

            return new SuccessResponse(ResultCode.ERROR_IN_PARAMETER);
        }

        if (this.checkUserInvalid(uid)) {
            LOGGER.error("NotLoginException");

            return new SuccessResponse(ResultCode.NOT_LOGIN);
        }

        User user = userService.findByUid(uid);

        if (isNotAMember(user)) {
            LOGGER.error("NotAMemberException");

            return new SuccessResponse(ResultCode.NOT_A_MEMBER);
        }

        Favorite f = favoriteService.findByUidAnfAid(user.getId(), aid);

        if (f != null) {
            favoriteService.delete(f);
        }

        return new SuccessResponse();
    }

    @Override
    @GET
    @Path("/favorites")
    @Produces(MediaType.APPLICATION_JSON)
    public ArtistListResponse getFavoriteArtistList(
        @QueryParam("uid")
    String uid, @QueryParam("offset")
    @DefaultValue("-1")
    int offset, @QueryParam("count")
    @DefaultValue("-1")
    int count) {
        LOGGER.info("Triger getFavoriteArtistList");

        if (!this.checkIdValid(uid) || (offset < 0) || (count < 0)) {
            LOGGER.error("ErrorInParamException");

            return new ArtistListResponse(ResultCode.ERROR_IN_PARAMETER);
        }

        if (this.checkUserInvalid(uid)) {
            LOGGER.error("NotLoginException");

            return new ArtistListResponse(ResultCode.NOT_LOGIN);
        }

        /* Look up in DB */
        User user = userService.findByUid(uid);

        if (isNotAMember(user)) {
            return new ArtistListResponse(ResultCode.NOT_A_MEMBER);
        }

        int total = favoriteService.getTotalFavoriteListByUid(user.getId())
                                   .intValue();
        List<Artist> artistList = artistService.getFavoritedArtistListByUid(user.getId(),
                offset, count);

        return getArtistListResponse(user, artistList, offset, total);
    }

    @Override
    @GET
    @Path("/{aid}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public ArtistResponse getArtistInfo(@QueryParam("uid")
    String uid, @PathParam("aid")
    String aid) {
        LOGGER.info("Triger getArtistInfo");

        if (!this.checkIdValid(uid) || !this.checkIdValid(aid)) {
            LOGGER.error("ErrorInParamException");

            return new ArtistResponse(ResultCode.ERROR_IN_PARAMETER);
        }

        if (this.checkUserInvalid(uid)) {
            LOGGER.error("NotLoginException");

            return new ArtistResponse(ResultCode.NOT_LOGIN);
        }

        /* Look up in DB */
        User user = userService.findByUid(uid);

        // if we cannot find user in the database
        if (isNotAMember(user)) {
            return new ArtistResponse(ResultCode.NOT_A_MEMBER);
        }

        Artist artist = artistService.getArtistByArtistId(user.getId(), aid);

        if (artist == null) {
            return new ArtistResponse(ResultCode.CAN_FOUND_SPEC_RESOURCE);
        }

        return new ArtistResponse(artist.toArtistInfo(uid));
    }

    @Override
    @GET
    @Path("/{aid}/albums")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public AlbumListResponse getArtistAlbum(@QueryParam("uid")
    String uid, @PathParam("aid")
    String aid, @QueryParam("offset")
    @DefaultValue("-1")
    int offset, @QueryParam("count")
    @DefaultValue("-1")
    int count) {
        LOGGER.info("Triger getArtistAlbum");

        if (!this.checkIdValid(uid) || !this.checkIdValid(aid) || (offset < 0) ||
                (count < 0)) {
            LOGGER.error("ErrorInParamException");

            return new AlbumListResponse(ResultCode.ERROR_IN_PARAMETER);
        }

        if (this.checkUserInvalid(uid)) {
            LOGGER.error("NotLoginException");

            return new AlbumListResponse(ResultCode.NOT_LOGIN);
        }

        // check if user exist or not
        User userExist = userService.findByUid(uid);

        if (isNotAMember(userExist)) {
            return new AlbumListResponse(ResultCode.NOT_A_MEMBER);
        }

        AlbumListResponse response = new AlbumListResponse();
        int total = albumService.getTotalAlbumByArtistId(aid).intValue();
        List<Album> albums = albumService.getAlbumListByArtistId(aid, offset,
                count);
        response.setTotal(total);
        response.setOffset(offset);

        List<AlbumInfo> albumList = new ArrayList<AlbumInfo>();

        for (Album al : albums) {
            albumList.add(al.toAlbumInfo());
        }

        response.setAlbums(albumList);

        return response;
    }
}
