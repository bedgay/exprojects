/**
 * 
 */
package jp.co.mti.external.re.response;

/**
 * @author Xuan Nguyen
 * 
 */
public class RESongInfo {

    String pid;

    public RESongInfo() {
        pid = null;
    }

    public RESongInfo(String pid) {
        this.pid = pid;
    }

    /**
     * @return the pid
     */
    public String getPid() {
        return pid;
    }

    /**
     * @param pid
     *            the pid to set
     */
    public void setPid(String pid) {
        this.pid = pid;
    }

}
