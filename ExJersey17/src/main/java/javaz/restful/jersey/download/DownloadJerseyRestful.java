package javaz.restful.jersey.download;

import java.io.File;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.sun.jersey.api.core.HttpContext;

@Path("/download")
public class DownloadJerseyRestful {
	
	ServletContext context;

	@GET
	@Path("/txt")
	@Produces(MediaType.TEXT_PLAIN)
	public Response txt(@Context HttpContext httpContext) {
		String filePath = "/resource/export/text_plain.txt";
		File file = new File(context.getRealPath(filePath));
		 
		ResponseBuilder response = Response.ok((Object) file);
		response.header("Content-Disposition",
			"attachment; filename=\"file_from_server.log\"");
		return response.build();
	}

	/**
	 * http://www.mkyong.com/webservices/jax-rs/download-image-file-from-jax-rs/
	 * @return
	 */
	@GET
	@Path("/png")
	@Produces("image/png")
	public Response png() {
		String filePath = "/resource/export/vn.png";
		File file = new File(context.getRealPath(filePath));
		 
		ResponseBuilder response = Response.ok((Object) file);
		response.header("Content-Disposition",
			"attachment; filename=\"file_from_server.png\"");
		return response.build();
	}

	@GET
	@Path("/pdf")
	@Produces("application/pdf")
	public Response pdf() {
		String filePath = "/resource/export/server.pdf";
		File file = new File(context.getRealPath(filePath));
		 
		ResponseBuilder response = Response.ok((Object) file);
		response.header("Content-Disposition",
			"attachment; filename=\"file_from_server.pdf\"");
		return response.build();
	}

	@GET
	@Path("/xlsx")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response xlsx() {
		String filePath = "/resource/export/server.xlsx";
		File file = new File(context.getRealPath(filePath));
		 
		ResponseBuilder response = Response.ok((Object) file);
		response.header("Content-Disposition",
			"attachment; filename=\"file_from_server.xlsx\"");
		return response.build();
	}
	
	/**
	 * Get ServletContext on tomcat from jax-rs / jersey
	 * http://stackoverflow.com/questions/1814517/get-servletcontext-on-tomcat-from-jax-rs-jersey
	 * @param context
	 */
	@Context
    public void setServletContext(ServletContext context) {
        System.out.println("servlet context set here");
        this.context = context;
    }

}
