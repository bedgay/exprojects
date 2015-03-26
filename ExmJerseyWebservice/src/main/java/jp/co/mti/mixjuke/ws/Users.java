package jp.co.mti.mixjuke.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import jp.co.mti.mixjuke.ws.response.SuccessResponse;
import jp.co.mti.mixjuke.ws.response.UserResponse;

public interface Users {

    /**
     * Get user profile data
     * 
     * @param uid
     *            User ID
     * @return String in JSON format.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    UserResponse getUserProfile(@QueryParam("uid") String uid);

    /**
     * Update user profile Update user profile use given data.
     * 
     * @param user
     *            User info.
     * @param uid
     *            User ID
     * @return String in JSON format.
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public SuccessResponse userProfileUpdate(@QueryParam("uid") String uid,
            String userString);

}