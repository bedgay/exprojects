package javaz.restful.jersey.formparam;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/form")
public class FormParamJerseyRestful {

	@POST
	@Path("/post")
	public Response post(@FormParam("code") String code,
			@FormParam("name") String name) {
		return Response.status(200).entity("Code: " + code + ", Name: " + name)
				.build();
	}
}
