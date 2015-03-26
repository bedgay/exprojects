/**
 * 
 */
package jp.co.mti.mixjuke.ws.exception;

import jp.co.mti.mixjuke.ws.response.ResultCode;

/**
 * @author Xuan Nguyen
 * 
 */
public class NotLoginException extends AbstractWebApplicationException {

    private static final long serialVersionUID = -5371631146993594870L;

    /**
     * @param resultCode
     */
    public NotLoginException() {
        super(ResultCode.NOT_LOGIN);
    }

}
