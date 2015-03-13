/**
 * 
 */
package jp.co.mti.mixjuke.ws.exception;

import jp.co.mti.mixjuke.ws.response.ResultCode;

/**
 * @author Xuan Nguyen
 * 
 */
public class ResourceNotFoundException extends AbstractWebApplicationException {

    private static final long serialVersionUID = -6509501828104307847L;

    public ResourceNotFoundException() {
        super(ResultCode.CAN_FOUND_SPEC_RESOURCE);
    }

}
