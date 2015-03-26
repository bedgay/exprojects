package javaz.restful.jersey.param;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import javaz.restful.jersey.entity.Country;

@Path("/param")
public class ParamJerseyRestful {

	@GET
	public String jsonGet() {
		return "RestFul with param.";
	}
	
	@GET
	@Path("/{param}")
	@Produces(MediaType.APPLICATION_JSON)
	public Country jsonGet(@PathParam("param") String code) {
		return new Country(code, "Viet name");
	}

	@GET
	@Path("/{id : \\d+}")
	public Response paramId(@PathParam("id") Integer id) {
		return Response.status(200).entity("ID : " + id).build();
	}
	
	@GET
	@Path("/user/{usn : [a-zA-Z0-9]{4,8}}")
	public Response paramUser(@PathParam("usn") String usn) {
		return Response.status(200).entity("Username : " + usn).build();
	}

	@POST
	@Path("/{param}")
	@Produces(MediaType.APPLICATION_JSON) 
	public Response jsonPost(@PathParam("param") String code, Country country) {
		System.out.println("rest/param/json-post");
		country.setCode(code);
		return Response.status(201).entity(country.toString()).build();
	}
	
}
