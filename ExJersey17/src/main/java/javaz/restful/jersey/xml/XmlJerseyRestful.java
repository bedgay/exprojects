package javaz.restful.jersey.xml;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import javaz.restful.jersey.entity.Country;

@Path("/xml/country")
public class XmlJerseyRestful {

	@GET
	@Path("/{code}")
	@Produces(MediaType.APPLICATION_XML)
	public Country get(@PathParam("code") String code) {
		return new Country(code, code + "'s name");
	}
	
	@POST
	@Produces(MediaType.APPLICATION_XML)
	public Response post(Country country) {
		System.out.println("rest/xml/country");
		return Response.status(201).entity(country.toString()).build();
	}

}
