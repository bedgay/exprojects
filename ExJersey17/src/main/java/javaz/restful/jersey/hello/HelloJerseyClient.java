package javaz.restful.jersey.hello;

import javax.ws.rs.core.MediaType;

import javaz.restful.jersey.ClientUtil;

import com.sun.jersey.api.client.ClientResponse;

public class HelloJerseyClient {

	public static void main(String[] args) {

		// Flurent interface
		System.out.println(ClientUtil.getService().path("rest").path("hello")
				.accept(MediaType.TEXT_PLAIN).get(ClientResponse.class)
				.toString());
		// Get plain text
		System.out.println(ClientUtil.getService().path("rest").path("hello")
				.accept(MediaType.TEXT_PLAIN).get(String.class));
		// Get XML
		System.out.println(ClientUtil.getService().path("rest").path("hello")
				.accept(MediaType.TEXT_XML).get(String.class));
		// The HTML
		System.out.println(ClientUtil.getService().path("rest").path("hello")
				.accept(MediaType.TEXT_HTML).get(String.class));

	}

}
