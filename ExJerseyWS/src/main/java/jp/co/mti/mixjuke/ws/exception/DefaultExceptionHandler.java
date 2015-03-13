/**
 * 
 */
package jp.co.mti.mixjuke.ws.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import jp.co.mti.mixjuke.ws.response.ResultCode;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * @author Xuan Nguyen
 * 
 */
@Provider
public class DefaultExceptionHandler implements ExceptionMapper<Exception> {

    private static final Logger LOGGER = LogManager.getLogger(DefaultExceptionHandler.class
            .getName());
    
    @Override
    public Response toResponse(Exception exception) {
        LOGGER.error("Unexpected exception ", exception);
        return buildResponse(ResultCode.SYSTEM_ERROR);
    }

    private Response buildResponse(ResultCode code) {
        return Response.status(Response.Status.OK).entity(code)
                .type(MediaType.APPLICATION_JSON).build();
    }
}
