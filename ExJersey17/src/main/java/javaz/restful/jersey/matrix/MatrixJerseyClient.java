package javaz.restful.jersey.matrix;

import javax.ws.rs.core.MediaType;

import javaz.restful.jersey.ClientUtil;

public class MatrixJerseyClient {
	public static void main(String[] args) {
		ClientUtil.get("http://localhost:8080/RestFulJersey17/rest/matrix/2013;author=TuNgo;country=VietName", MediaType.TEXT_PLAIN);
	}
}
