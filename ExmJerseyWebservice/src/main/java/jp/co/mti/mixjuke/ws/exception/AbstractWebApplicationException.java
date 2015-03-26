/**
 * 
 */
package jp.co.mti.mixjuke.ws.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import jp.co.mti.mixjuke.ws.base.ResouceBundleHelper;
import jp.co.mti.mixjuke.ws.response.ErrorResponse;
import jp.co.mti.mixjuke.ws.response.ResultCode;

/**
 * @author Xuan Nguyen
 * 
 */
public class AbstractWebApplicationException extends WebApplicationException {

    private static final long serialVersionUID = 8585614423635470115L;

    public AbstractWebApplicationException(ResultCode resultCode) {
        super(buildResponse(resultCode));
    }

    public AbstractWebApplicationException(int code, String description) {
        super(buildResponseFromDynamicDescription(code, description));
    }

    private static Response buildResponse(ResultCode code) {
        return Response
                .status(Response.Status.OK)
                .entity(new ErrorResponse(code.getResultCode(),
                        ResouceBundleHelper.getMessage(code.getDescription())))
                .type(MediaType.APPLICATION_JSON).build();
    }

    private static Response buildResponseFromDynamicDescription(int code,
            String description) {
        return Response.status(Response.Status.OK)
                .entity(new ErrorResponse(code, description))
                .type(MediaType.APPLICATION_JSON).build();
    }
}
