/**
 * 
 */
package jp.co.mti.mixjuke.ws.exception;

import jp.co.mti.mixjuke.ws.response.ResultCode;

/**
 * @author Xuan Nguyen
 *
 */
public class ErrorInParamException extends AbstractWebApplicationException {

    private static final long serialVersionUID = 5132560602173369915L;

    /**
     * @param resultCode
     */
    public ErrorInParamException() {
        super(ResultCode.ERROR_IN_PARAMETER);
    }

}
