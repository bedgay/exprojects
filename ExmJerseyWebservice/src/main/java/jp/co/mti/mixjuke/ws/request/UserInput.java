/**
 * 
 */
package jp.co.mti.mixjuke.ws.request;

/**
 * @author Xuan Nguyen
 * 
 */
public class UserInput {
    private UserJSON user;

    /**
     * @return the user
     */
    public UserJSON getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(UserJSON user) {
        this.user = user;
    }
    public UserInput() {
        this.user = new UserJSON();
    }
}
