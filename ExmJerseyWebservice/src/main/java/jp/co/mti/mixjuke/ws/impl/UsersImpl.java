package jp.co.mti.mixjuke.ws.impl;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import jp.co.mti.external.re.REService;
import jp.co.mti.mixjuke.dom.User;
import jp.co.mti.mixjuke.service.UserService;
import jp.co.mti.mixjuke.ws.AbstractWebService;
import jp.co.mti.mixjuke.ws.Users;
import jp.co.mti.mixjuke.ws.request.UserInput;
import jp.co.mti.mixjuke.ws.response.ResultCode;
import jp.co.mti.mixjuke.ws.response.SuccessResponse;
import jp.co.mti.mixjuke.ws.response.UserProfileInfo;
import jp.co.mti.mixjuke.ws.response.UserResponse;

import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("userWebService")
@Path("/users")
public class UsersImpl extends AbstractWebService implements Users {

    private static final Logger LOGGER = LogManager.getLogger(UsersImpl.class
            .getName());

    @Autowired
    private UserService userService;

    @Autowired
    private REService reService;

    @Override
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public UserResponse getUserProfile(@QueryParam("uid") String uid) {
        LOGGER.info("Triger getUserProfile with uid: " + uid);
        if (!this.checkIdValid(uid)) {
            LOGGER.error("ErrorInParamException");
            return new UserResponse(ResultCode.ERROR_IN_PARAMETER);

        }
        if (this.checkUserInvalid(uid)) {
            LOGGER.error("NotLoginException");
            return new UserResponse(ResultCode.NOT_LOGIN);
        }
        User user = userService.findByUid(uid);
        if (isNotAMember(user)) {
            LOGGER.error("NotAMemberException");
            return new UserResponse(ResultCode.NOT_A_MEMBER);
        }
        UserProfileInfo userProfile = user.toUserProfileInfo();
        return new UserResponse(userProfile);
    }

    @Override
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public SuccessResponse userProfileUpdate(@QueryParam("uid") String uid,
            String userString) {
        LOGGER.info("Triger userProfileUpdate");
        ObjectMapper om = new ObjectMapper();
        UserInput user;
        try {
            user = om.readValue(userString, UserInput.class);
        } catch (Exception e1) {
            return new SuccessResponse(ResultCode.ERROR_IN_PARAMETER);
        }

        if (!this.checkIdValid(uid)) {
            LOGGER.error("ErrorInParamException");
            return new SuccessResponse(ResultCode.ERROR_IN_PARAMETER);
        }

        if (this.checkUserInvalid(uid)) {
            LOGGER.error("NotLoginException");
            return new SuccessResponse(ResultCode.NOT_LOGIN);
        }

        User existUser = userService.findByUid(uid);
        if (isNotAMember(existUser)) {
            LOGGER.error("NotAMemberException");
            return new SuccessResponse(ResultCode.NOT_A_MEMBER);
        }

        Date birthday = null;
        String data = null;
        data = user.getUser().getBirthday();
        if (data != null) {
            try {
                birthday = DateUtils.parseDateStrictly(data,
                        new String[] { "yyyyMMdd" });
            } catch (Exception e) {
                LOGGER.error("ErrorInParamException");
                return new SuccessResponse(ResultCode.ERROR_IN_PARAMETER);
            }
        }
        data = user.getUser().getName();
        if (data != null) {
            existUser.setName(data);
        }
        data = user.getUser().getAvatorUrl();
        if (data != null) {
            existUser.setAvatarUrl(data);
        }
        if (birthday != null) {
            existUser.setBirthday(birthday);
        }
        if (user.getUser().getGender() != null) {
            existUser.setGender(user.getUser().getGender());
        }
        if (user.getUser().isShareProfile() != null) {
            existUser.setShareProfile(user.getUser().isShareProfile() ? 1 : 0);
        }
        existUser.setInitial(false);
        userService.saveOrUpdate(existUser);
        return new SuccessResponse();
    }
}
