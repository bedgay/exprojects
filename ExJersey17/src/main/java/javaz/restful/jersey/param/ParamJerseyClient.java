package javaz.restful.jersey.param;

import javax.ws.rs.core.MediaType;

import javaz.restful.jersey.ClientUtil;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;


public class ParamJerseyClient {

	public static void main(String[] args) {
		// Flurent interface
		System.out.println(ClientUtil.getService().path("rest").path("param")
				.accept(MediaType.TEXT_PLAIN).get(ClientResponse.class)
				.toString());
		
		System.out.println(ClientUtil.getService().path("rest").path("param").path("ex")
				.accept(MediaType.APPLICATION_JSON).get(String.class));

		System.out.println(ClientUtil.getService().path("rest").path("param").path("101")
				.accept(MediaType.TEXT_PLAIN).get(String.class));
		
		System.out.println(ClientUtil.getService().path("rest").path("param").path("user").path("root")
				.accept(MediaType.TEXT_PLAIN).get(String.class));

		try {
			WebResource webResource = ClientUtil.getClient()
					.resource("http://localhost:8080/RestFulJersey17/rest/param/fr");

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
