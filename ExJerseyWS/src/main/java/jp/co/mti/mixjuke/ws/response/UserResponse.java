/**
 * 
 */
package jp.co.mti.mixjuke.ws.response;

import jp.co.mti.mixjuke.ws.base.ResouceBundleHelper;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @author Xuan Nguyen
 * 
 */
public class UserResponse extends AbstractRespone {

    private String error = null;
    private UserProfileInfo user;

    /**
     * Default constructor.
     */
    public UserResponse() {
        super(0);
        this.user = null;
    }

    /**
     * @param result
     */
    public UserResponse(UserProfileInfo userProfile) {
        super(ResultCode.NORMAL);
        this.user = userProfile;
    }

    public UserResponse(ResultCode code) {
        super(code.getResultCode());
        this.error = ResouceBundleHelper.getMessage(code.getDescription());
    }

    /**
     * @return the user
     */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public UserProfileInfo getUser() {
        return user;
    }

    /**
     * @param user
     *            the user to set
     */
    public void setUser(UserProfileInfo user) {
        this.user = user;
    }

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
