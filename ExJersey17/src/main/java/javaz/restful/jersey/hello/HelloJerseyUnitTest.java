package javaz.restful.jersey.hello;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.MediaType;

import org.junit.Test;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.test.framework.JerseyTest;

public class HelloJerseyUnitTest extends JerseyTest {

	public HelloJerseyUnitTest() throws Exception {
		super("javaz.restful.jersey.hello");
	}

	@Test
	public void sayPlainTextHello() {
		String responseMsg = resource().path("hello")
				.accept(MediaType.TEXT_PLAIN).get(String.class);
		assertEquals("Hello Jersey", responseMsg);
	}

	@Test
	public void sayXMLHello() {
		ClientResponse response = resource().path("hello")
				.accept(MediaType.TEXT_XML).get(ClientResponse.class);
		assertEquals(ClientResponse.Status.OK.getStatusCode(), response.getStatus());
	}
	
	@Test
	public void sayHtmlHello() {
		ClientResponse response = resource().path("hello")
				.accept(MediaType.TEXT_HTML).get(ClientResponse.class);
		assertEquals(ClientResponse.Status.OK.getStatusCode(), response.getStatus());
	}
	
}
