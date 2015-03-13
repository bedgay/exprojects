package jp.co.mti.mixjuke.ws.impl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import jp.co.mti.mixjuke.dom.User;
import jp.co.mti.mixjuke.service.UserService;
import jp.co.mti.mixjuke.ws.AbstractWebService;
import jp.co.mti.mixjuke.ws.PlaybackToken;
import jp.co.mti.mixjuke.ws.response.PlaybackTokenRespone;
import jp.co.mti.mixjuke.ws.response.ResultCode;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * User: nhphuoc 
 * Date: 10/22/13 
 * Time: 8:29 AM
 */
@Component("playbackControlWebService")
@Path("/playbacktoken")
public class PlaybackTokenImpl extends AbstractWebService implements
		PlaybackToken {
	private static final Logger LOGGER = LogManager
			.getLogger(PlaybackTokenImpl.class.getName());

	@Autowired
	private UserService userService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public PlaybackTokenRespone controlPlaybackToken(
			@QueryParam("uid") String uid,
			@QueryParam("dev_unique_id") String devid,
			@QueryParam("take") int take) {
		LOGGER.info("controlPlaybackToken");
		PlaybackTokenRespone respone = null;
		// Validate the uid parameter.
		if (!this.checkIdValid(uid) || (devid == null)
				|| (take < 0 || take > 1)) {
			LOGGER.error("ErrorInParamException");
			respone = new PlaybackTokenRespone(ResultCode.ERROR_IN_PARAMETER);
		} else {
			if (this.checkUserInvalid(uid)) {
				LOGGER.error("NotLoginException");
				respone = new PlaybackTokenRespone(ResultCode.NOT_LOGIN);
			} else {
				User user = userService.findByUid(uid);
				if (isNotAMember(user)) {
					// Cannot find the user with specific uid in the db
					respone = new PlaybackTokenRespone(ResultCode.NOT_A_MEMBER);
				} else {
					// The user is found in the db
					switch (take) {
						case 0:
							// Notes user.getDevid() can be null
							String oldDevid = user.getDevid();
							if (oldDevid != null) {
								if (oldDevid.equalsIgnoreCase(devid)) {
									//CHG-S nhphuoc 140204 Fix bug 5712
									//respone = new PlaybackTokenRespone(0,
									//		user.getStatus());
									respone = new PlaybackTokenRespone(0,
											user.getMemberStatus().getStatus());
								    //CHG-E nhphuoc
								} else {
									respone = new PlaybackTokenRespone(
											ResultCode.ALREADY_DEVICE);
								}
								break;
							} else {
								// treat as take = 1
							}
						case 1:
							// Overwrite the devid
							user.setDevid(devid);
							userService.saveOrUpdate(user);
							// TODO Recheck in next release the member status
							//TODO getStatus
							respone = new PlaybackTokenRespone(0, user.getStatus());
							break;
					}
				}
			}
		}
		return respone;
	}
}
