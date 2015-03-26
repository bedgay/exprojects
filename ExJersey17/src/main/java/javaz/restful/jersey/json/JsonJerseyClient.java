package javaz.restful.jersey.json;

import javax.ws.rs.core.MediaType;

import javaz.restful.jersey.ClientUtil;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * @author SUCCESS\tungo
 *         http://www.mkyong.com/webservices/jax-rs/restful-java-client
 *         -with-jersey-client/
 */
public class JsonJerseyClient {

	public static void main(String[] args) {
		// The JSON
		System.out.println(ClientUtil.getService().path("rest").path("json").path("ex")
				.accept(MediaType.APPLICATION_JSON).get(String.class));
		try {

			WebResource webResource = ClientUtil.getClient()
					.resource("http://localhost:8080/RestFulJersey17/rest/json/post");

			String input = "{\"code\":\"en\", \"name\":\"English\"}";

			ClientResponse response = webResource.type("application/json")
					.post(ClientResponse.class, input);

			if (response.getStatus() != 201) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}

			System.out.println("Output from Server .... \n");
			String output = response.getEntity(String.class);
			System.out.println(output);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
