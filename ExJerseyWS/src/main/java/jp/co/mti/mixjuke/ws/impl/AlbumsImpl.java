/**
 * 
 */
package jp.co.mti.mixjuke.ws.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import jp.co.mti.mixjuke.dom.Song;
import jp.co.mti.mixjuke.dom.User;
import jp.co.mti.mixjuke.service.AlbumService;
import jp.co.mti.mixjuke.service.SongService;
import jp.co.mti.mixjuke.service.UserService;
import jp.co.mti.mixjuke.ws.AbstractWebService;
import jp.co.mti.mixjuke.ws.Albums;
import jp.co.mti.mixjuke.ws.response.ResultCode;
import jp.co.mti.mixjuke.ws.response.SongInfo;
import jp.co.mti.mixjuke.ws.response.SongListFreeResponse;
import jp.co.mti.mixjuke.ws.response.SongListResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ntnxuan
 * 
 */
@Component("albumsWebService")
@Path("/albums")
public class AlbumsImpl extends AbstractWebService implements Albums {

    private static final Logger LOGGER = LogManager.getLogger(AlbumsImpl.class
            .getName());

    @Autowired
    private AlbumService albumService;

    @Autowired
    private SongService songService;

    @Autowired
    private UserService userService;

    @Override
    @GET
    @Path("/{album_id}/songs")
    @Produces(MediaType.APPLICATION_JSON)
    public SongListResponse getAlbumSongs(@QueryParam("uid") String uid,
            @PathParam("album_id") String albumId,
            @QueryParam("count") @DefaultValue("-1") int count,
            @QueryParam("offset") @DefaultValue("-1") int offset) {
        LOGGER.info("Triger getAlbumSongs");
        if (!this.checkIdValid(uid) || StringUtils.isBlank(albumId)
                || count < 0 || offset < 0) {
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
        int total = songService
                .getTotalSongListByAlbumId(user.getId(), albumId).intValue();
        List<Song> songList = songService.getSongListByAlbumId(user.getId(),
                albumId, offset, count);
        SongListResponse response = new SongListFreeResponse();
        response.setTotal(total);
        response.setOffset(offset);

        /* Convert Song array to SongInfo array */
        songList = songService.addReadID(songList);
        List<SongInfo> songInfolist = new ArrayList<SongInfo>();
        for (Song s : songList) {
            songInfolist
                    .add(s.toSongInfo(user, response.isGetStreamUrl(), null));
        }
        response.setSongs(songInfolist);
        return response;
    }

}
