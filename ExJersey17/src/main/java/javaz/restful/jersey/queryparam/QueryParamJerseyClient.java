package javaz.restful.jersey.queryparam;

import javax.ws.rs.core.MediaType;

import javaz.restful.jersey.ClientUtil;

public class QueryParamJerseyClient {
	
	public static void main(String[] args) {

		ClientUtil.get("http://localhost:8080/RestFulJersey17/rest/query/param?from=1&to=20&orderBy=code&orderBy=name", MediaType.TEXT_PLAIN);
		ClientUtil.get("http://localhost:8080/RestFulJersey17/rest/query/default", MediaType.TEXT_PLAIN);
		ClientUtil.get("http://localhost:8080/RestFulJersey17/rest/query/context?from=10&to=200&orderBy=code&orderBy=name", MediaType.TEXT_PLAIN);
		
	}

}
