package jp.co.mti.mixjuke.ws.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import jp.co.mti.mixjuke.dom.PlaybackLog;
import jp.co.mti.mixjuke.dom.User;
import jp.co.mti.mixjuke.service.PlaybackLogService;
import jp.co.mti.mixjuke.service.UserService;
import jp.co.mti.mixjuke.ws.AbstractWebService;
import jp.co.mti.mixjuke.ws.PlaybackDoLog;
import jp.co.mti.mixjuke.ws.request.PlaybackLogInput;
import jp.co.mti.mixjuke.ws.request.PlaybackLogItem;
import jp.co.mti.mixjuke.ws.response.ResultCode;
import jp.co.mti.mixjuke.ws.response.SuccessResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @User:nhphuoc 
 * @Date: 11/28/13 
 * @Time: 2:15 PM
 */
@Component("playbackLogWebService")
@Path("/playbacklog")
public class PlaybackDoLogImpl extends AbstractWebService implements
		PlaybackDoLog {
	private static final Logger LOGGER = LogManager
			.getLogger(PlaybackDoLogImpl.class.getName());
	@Autowired
	private UserService userService;
	@Autowired
	private PlaybackLogService logService;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public SuccessResponse playbacklog(MultivaluedMap<String,String> params) {
		LOGGER.info("playbacklog");
		SuccessResponse respone = null;
		
		if (params == null || StringUtils.isEmpty(params.getFirst("uid")) 
				|| StringUtils.isEmpty(params.getFirst("data"))) {
			LOGGER.error("ErrorInParamException");
			return new SuccessResponse(ResultCode.ERROR_IN_PARAMETER);
		} else {
			String uid = params.getFirst("uid");
			String postData = params.getFirst("data");
		
			if (this.checkUserInvalid(uid)) {
				LOGGER.error("NotLoginException");
				respone = new SuccessResponse(ResultCode.NOT_LOGIN);
			} else {
				User user = userService.findByUid(uid);
				if (isNotAMember(user)) {
					respone = new SuccessResponse(ResultCode.NOT_A_MEMBER);
				} else {
					ObjectMapper om = new ObjectMapper();
					PlaybackLogInput log = null;
					
					try {
						log = om.readValue(postData, PlaybackLogInput.class);
					} catch (Exception ex) {
						LOGGER.warn("Post data is invalid json", ex);
						respone = new SuccessResponse(ResultCode.ERROR_IN_PARAMETER);
					}
					
					if (log != null) {
						List<PlaybackLogItem> items = log.getLog();
						boolean isOk = false;
						for (PlaybackLogItem item : items) {
							if (logService.isValidLogItem(
									item.getPlaySessionId(),
									item.getProdId())) {
								// store the new one
								PlaybackLog pLog = new PlaybackLog();
								// uid
								pLog.setUid(user.getUid());
								// play_session_id
								pLog.setSessionId(item.getPlaySessionId());
								// streaming_dt
								Timestamp currentDate = new Timestamp(
										new java.util.Date().getTime());
								pLog.setServerDate(currentDate);
								// prod_id
								pLog.setProductId(item.getProdId());
								// device_cd
								pLog.setDeviceCD(log.getDeviceCd());
								// device_nm
								pLog.setDeviceModel(log.getDeviceName());
								// unit_acc
								pLog.setAccountType(user.isAccountType());
								// play_sec
								pLog.setPlaySec(item.getPlaySec());
								// imei_no
								pLog.setDeviceId(log.getDevUniqueId());
								// course_id
								pLog.setCourseId((short) user.getMemberStatus()
										.getStatus());
								// log_type
								pLog.setLogType(item.getLogType());
								// app_streaming_dt
								try {
									pLog.setPlayEndTime(new Timestamp(item
											.getPlayEndInDate().getTime()));
								} catch (ParseException ex) {
									LOGGER.info("Cannot parse play_end_dt property", ex);
									continue;
								}
								// upd_nm
								pLog.setNameOfUpdate("uid");
								// upd_pg
								pLog.setProgramOfUpdate("MJ_STRLOG");
								// upd_dt
								pLog.setDateOfUpdate(currentDate);
								// play_type
								pLog.setPreviewFlag(item.getPlayType());
								//seed_artist_id
								pLog.setSeedArtistId(item.getSeedArtistId());
								//seed_genre_id
								pLog.setSeedGenreId(item.getSeedGenreId());
								// Store into DB
								try {
									logService.save(pLog);
								} catch (HibernateException ex) {
									LOGGER.info("Cannot store log to database", ex);
									continue;
								}
								isOk = true;
							}
						}
						if (isOk) {
							respone = new SuccessResponse();
						} else {
							respone = new SuccessResponse(ResultCode.ERROR_IN_PARAMETER);
						}
					}

				}
			}
		}
		return respone;
	}
}
