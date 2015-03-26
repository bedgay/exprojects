package javaz.restful.jersey.matrix;

import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/matrix")
public class MatrixJerseyRestful {
	
	@GET
	@Path("/{year}")
	public Response get(@PathParam("year") int year,
			@MatrixParam("author") String author,
			@MatrixParam("country") String country) {
		return Response
		   .status(200)
		   .entity("Year: " + year + ", Author: " + author
			+ ", Country" + country).build();
	}

}
