/**
 * 
 */
package jp.co.mti.mixjuke.web.external.response;

import java.util.List;

/**
 * @author Xuan Nguyen
 * 
 */
public class Result {

    private String code;
    private List<String> args;
    private String target;

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     *            the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the args
     */
    public List<String> getArgs() {
        return args;
    }

    /**
     * @param args
     *            the args to set
     */
    public void setArgs(List<String> args) {
        this.args = args;
    }

    /**
     * @return the target
     */
    public String getTarget() {
        return target;
    }

    /**
     * @param target
     *            the target to set
     */
    public void setTarget(String target) {
        this.target = target;
    }

}
