/**
 * 
 */
package jp.co.mti.mixjuke.ws.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import jp.co.mti.mixjuke.dom.Genre;
import jp.co.mti.mixjuke.dom.User;
import jp.co.mti.mixjuke.service.GenreService;
import jp.co.mti.mixjuke.service.UserService;
import jp.co.mti.mixjuke.ws.AbstractWebService;
import jp.co.mti.mixjuke.ws.Genres;
import jp.co.mti.mixjuke.ws.response.GenreInfo;
import jp.co.mti.mixjuke.ws.response.GenreListResponse;
import jp.co.mti.mixjuke.ws.response.ResultCode;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Xuan Nguyen
 * 
 */
@Component("genreWebService")
@Path("/genres")
public class GenresImpl extends AbstractWebService implements Genres {

    private static final Logger LOGGER = LogManager.getLogger(GenresImpl.class
            .getName());

    @Autowired
    private GenreService genreService;

    @Autowired
    private UserService userService;

    /*
     * (non-Javadoc)
     * 
     * @see jp.co.mti.mixjuke.webservice.Users#getGenreList(java.lang.String)
     */
    @Override
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public GenreListResponse getGenreList(@QueryParam("uid") String uid) {
        LOGGER.info("Triger getGenreList");
        if (!this.checkIdValid(uid)) {
            LOGGER.error("ErrorInParamException");
            return new GenreListResponse(ResultCode.ERROR_IN_PARAMETER);
        }
        if (this.checkUserInvalid(uid)) {
            LOGGER.error("NotLoginException");
            return new GenreListResponse(ResultCode.NOT_LOGIN);
        }
        /* Look up in DB */
        User user = userService.findByUid(uid);
        if (isNotAMember(user)) {
            return new GenreListResponse(ResultCode.NOT_A_MEMBER);
        }
        /* Collect in DB */
        List<Genre> genList = genreService.getList();
        if (CollectionUtils.isEmpty(genList)) {
            return new GenreListResponse(new ArrayList<GenreInfo>());
        }
        /* Convert Genre array to GenreInfo array */
        List<GenreInfo> list = new ArrayList<GenreInfo>();
        for (int i = 0; i < genList.size(); i++) {
            list.add(genList.get(i).toGenreInfo());
        }
        return new GenreListResponse(list);
    }

}
