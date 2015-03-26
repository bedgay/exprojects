package jp.co.mti.mixjuke.ws.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import jp.co.mti.mixjuke.ws.response.ResultCode;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.sun.jersey.api.NotFoundException;

/**
 * Handle the URL not found
 * @author TuNgo
 * @date 2012-12-19 13:32:31
 *
 */
@Provider
public class NotFoundExceptionHandler implements ExceptionMapper<NotFoundException> {
	
    private static final Logger LOGGER = LogManager.getLogger(NotFoundExceptionHandler.class
            .getName());

	@Override
	public Response toResponse(NotFoundException exception) {
        LOGGER.error("The url is wrong ", exception);
        return Response.status(Response.Status.OK).entity(ResultCode.RESOURCE_NOT_FOUND)
                .type(MediaType.APPLICATION_JSON).build();
	}

}
