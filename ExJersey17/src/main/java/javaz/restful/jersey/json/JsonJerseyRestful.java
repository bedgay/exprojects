package javaz.restful.jersey.json;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import javaz.restful.jersey.entity.Country;

/**
 * @author SUCCESS\tungo
 * http://www.mkyong.com/webservices/jax-rs/jersey-hello-world-example/
 * http://www.mkyong.com/webservices/jax-rs/json-example-with-jersey-jackson/
 */
@Path("/json")
public class JsonJerseyRestful {

	@GET
	@Path("/{param}")
	@Produces(MediaType.APPLICATION_JSON)
	public Country jsonGet(@PathParam("param") String code) {
		return new Country(code, "Viet name");
	}
	
	@POST
	@Path("/post")
	@Produces(MediaType.APPLICATION_JSON) 
	public Response jsonPost(Country country) {
		System.out.println("rest/hello/json-post");
		return Response.status(201).entity(country.toString()).build();
	}

}
