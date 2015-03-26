package javaz.restful.jersey.queryparam;

import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/query")
public class QueryParamJerseyRestful {

	@GET
	@Path("/param")
	public Response param(@QueryParam("from") int from,
			@QueryParam("to") int to,
			@QueryParam("orderBy") List<String> orderBy) {
		return Response
				   .status(200)
				   .entity("getCountries by queryparam is called, from : " + from + ", to : " + to
					+ ", orderBy" + orderBy.toString()).build();
	}
	
	@GET
	@Path("/default")
	public Response defaultParam(@DefaultValue("50") @QueryParam("from") int from,
			@DefaultValue("70") @QueryParam("to") int to,
			@DefaultValue("name") @QueryParam("orderBy") List<String> orderBy) {
		return Response
				   .status(200)
				   .entity("getCountries by queryparam is called, from : " + from + ", to : " + to
					+ ", orderBy" + orderBy.toString()).build();
	}
	
	@GET
	@Path("/context")
	public Response context(@Context UriInfo info) {
		String from = info.getQueryParameters().getFirst("from");
		String to = info.getQueryParameters().getFirst("to");
		List<String> orderBy = info.getQueryParameters().get("orderBy");
 
		return Response
		   .status(200)
		   .entity("getCountries by context is called, from : " + from + ", to : " + to
			+ ", orderBy" + orderBy.toString()).build();
	}
	
}
