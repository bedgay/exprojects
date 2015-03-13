package jp.co.mti.mixjuke.ws.impl;

import java.sql.Timestamp;
import java.util.Date;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import jp.co.mti.mixjuke.dom.PlaybackLog;
import jp.co.mti.mixjuke.dom.Song;
import jp.co.mti.mixjuke.dom.User;
import jp.co.mti.mixjuke.service.PlaybackLogService;
import jp.co.mti.mixjuke.service.SongService;
import jp.co.mti.mixjuke.service.StreamAuthService;
import jp.co.mti.mixjuke.service.UserService;
import jp.co.mti.mixjuke.util.DateUtil;
import jp.co.mti.mixjuke.ws.AbstractWebService;
import jp.co.mti.mixjuke.ws.ServiceAuthentication;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * User: nhphuoc
 * Date: 10/22/13
 * Time: 1:51 PM
 */
@Component("akamaiAuthCallbackWebService")
@Path("/service/auth")
public class ServiceAuthenticationImpl extends AbstractWebService implements ServiceAuthentication {

	private static final int FULL_PLAY_TYPE = 0;

	private static final Logger LOGGER = LogManager.getLogger(ServiceAuthenticationImpl.class
            .getName());
	
    private static final int PLAY_SEC_DEFAULT = 0;
	private static final String NAME_OF_UPDATE_DEFAULT = "uid";
	private static final String PROGRAM_OF_UPDATE_DEFAULT = "MJ_STRAUTH";
	private static final String DEVICE_CD_ANDROID = "android";
    private static final String STREAMING_DATE_FORMAT = "yyyyMMDDHHmmss";
    private static final String TIME_ZONE = "GMT+9";
    
    @Autowired
    UserService userService;
    @Autowired
    SongService songService;
    @Autowired
    PlaybackLogService playbackLogService;
    @Autowired
    StreamAuthService streamAuthService;
    @Context
    UriInfo uriInfo;
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response serviceAuthenticate(@QueryParam("uid") String uid,
                                        @QueryParam("dev_unique_id") String devid,
                                        @QueryParam("sv") String server,
                                        @QueryParam("prod_id") String pid,
                                        @QueryParam("device_nm") String deviceName,
                                        @QueryParam("streaming_dt") String streamingDt,
                                        @QueryParam("play_session_id") String playSessionId,
                                        @QueryParam("play_type") String playType,
                                        @QueryParam("seed_artist_id") @DefaultValue("-1") Integer seedArtistId,
                                        @QueryParam("seed_genre_id") @DefaultValue("-1") Integer seedGenreId) {
        Response error = Response.status(Response.Status.NOT_MODIFIED).build();
        Response result;
        if (uid == null || devid == null || server == null || this.checkUserInvalid(uid) || 
        		playSessionId == null || pid == null || playType == null || 
        		!StringUtils.isNumeric(playType) /*|| !StringUtils.isNumeric(coursId)*/) {
            LOGGER.info("Wrong params uid=" + uid + " devid=" + devid  +" server=" + server + 
            		" play_session_id=" + playSessionId + " prod_id=" + pid/* +" cours_id=" + coursId*/);
            result = error;
        } else {
            User user = userService.findByUid(uid);
            if (isNotAMember(user)) {
                LOGGER.info("Cannot find user or user is canceled.");
                result = error;
            } else if (!streamAuthService.checkRuleOneByProduct(pid)) {
                LOGGER.info("Product doesn't pass rule one with ID=" + pid);
                result = error;
            } else if (!streamAuthService.checkRuleTwoByProductAndFlag(pid, user.getMemberStatus())) {
                LOGGER.info("Product doesn't pass rule two with ID=" + pid + " memberstatus=" + user.getMemberStatus());
                result = error;
            } else if (!streamAuthService.checkRuleThree(Integer.valueOf(playType), user.getMemberStatus())) {
                LOGGER.info("User doesn't pass rule three with play_type=" + playType + " memberstatus=" + user.getMemberStatus());
                result = error;
            } else if(Integer.valueOf(playType) == FULL_PLAY_TYPE && (user.getDevid() == null || !user.getDevid().equalsIgnoreCase(devid))) {
            	//If play_type=0(full) Check if dev_unique_id is the same as MJ_USER.devid
                LOGGER.info("devid isn't match.");
                result = error;
            } else {
                Song song = songService.findSongByProductId(pid);
                if (song == null) {
                    LOGGER.info("cannot find the song with pid=" + pid);
                    result = error;
                } else {
                	if (!savePlaybackLog(uid, devid, pid, deviceName,
							user.getMemberStatus().getStatus(), streamingDt, playSessionId, playType, seedArtistId, seedGenreId)) {
                		result = error;
                	} else {
                		result = Response.status(Response.Status.OK).build();
                	}
                }
            }
        }
        return result;
    }
    
	private Boolean savePlaybackLog(String uid, String devid, String pid,
			String deviceName, int coursId, String streamingDt,
			String playSessionId, String playType, Integer seedArtistId, Integer seedGenreId) {
		try {
			PlaybackLog log = new PlaybackLog();
			log.setUid(uid);
			log.setDeviceCD(DEVICE_CD_ANDROID);
			log.setDeviceId(devid);
			log.setDeviceModel(deviceName);
			log.setProductId(pid);
			log.setPlaySec(PLAY_SEC_DEFAULT);
			log.setPlayEndTime(new Timestamp(DateUtil.stringToDate(streamingDt, STREAMING_DATE_FORMAT, TIME_ZONE).getTime()));
			log.setCourseId((short)coursId);
			log.setSessionId(playSessionId);
			log.setAccountType(Boolean.TRUE);
			log.setNameOfUpdate(NAME_OF_UPDATE_DEFAULT);
			log.setProgramOfUpdate(PROGRAM_OF_UPDATE_DEFAULT);
			log.setDateOfUpdate(new Timestamp(new Date().getTime()));
			log.setServerDate(new Timestamp(new Date().getTime()));
			log.setPreviewFlag(Integer.valueOf(playType));
			if (seedArtistId != -1) {
				log.setSeedArtistId(seedArtistId);
			}
			if (seedGenreId != -1) {
				log.setSeedGenreId(seedGenreId);
			}
			playbackLogService.save(log);
			return Boolean.TRUE;
		} catch (Exception e) {
		    LOGGER.info("can not save PlaybackLog with uid=" + uid + " dev_unique_id=" + devid + " product_id=" + pid);
			return Boolean.FALSE;
		}
	}

}
