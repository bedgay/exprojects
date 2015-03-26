package javaz.restful.jersey;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class ClientUtil {
	
	public static Client getClient() {
		ClientConfig congif = new DefaultClientConfig();
		Client client = Client.create(congif);
		return client;
	}
	
	public static WebResource getService() {
		WebResource service = getClient().resource(getBaseURI());
		return service;
	}
	
	public static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:8080/RestFulJersey17")
				.build();
	}
	
	public static void get(String uri, String mediaType) {
		try {

			WebResource webResource = ClientUtil.getClient().resource(uri);

			ClientResponse response = webResource.type(mediaType).get(ClientResponse.class);

			if (response.getStatus() != 200) {
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
