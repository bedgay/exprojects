/**
 * 
 */
package jp.co.mti.mixjuke.ws.response;

import java.util.List;

import jp.co.mti.mixjuke.ws.base.ResouceBundleHelper;

/**
 * @author ntnxuan
 * 
 */
public class UserListResponse extends AbstractRespone {
    private List<UserInfo> users;
    private String error = null;
    private Integer total;
    private Integer offset;

    /**
     * @return the users
     */
    public List<UserInfo> getUsers() {
        return users;
    }

    /**
     * @param users
     *            the users to set
     */
    public void setUsers(List<UserInfo> users) {
        this.users = users;
    }

    /**
     * @return the error
     */
    public String getError() {
        return error;
    }

    /**
     * @param error
     *            the error to set
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * @return the total
     */
    public Integer getTotal() {
        return total;
    }

    /**
     * @param total
     *            the total to set
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     * @return the offset
     */
    public Integer getOffset() {
        return offset;
    }

    /**
     * @param offset
     *            the offset to set
     */
    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public UserListResponse() {
        super();
    }

    public UserListResponse(Integer offset, Integer total, List<UserInfo> users) {
        super(ResultCode.NORMAL);
        this.offset = offset;
        this.total = total;
        this.users = users;
        this.error = null;
    }

    public UserListResponse(ResultCode result) {
        super(result);
        this.error = ResouceBundleHelper.getMessage(result.getDescription());
    }

    public UserListResponse(ResultCode result, String msg) {
        super(result);
        this.error = msg;
    }
}
