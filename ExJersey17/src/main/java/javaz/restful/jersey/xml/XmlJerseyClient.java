package javaz.restful.jersey.xml;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import javaz.restful.jersey.ClientUtil;

public class XmlJerseyClient {
	
	public static void main(String[] args) {

		System.out.println(ClientUtil.getService().path("rest").path("xml").path("country").path("au")
				.accept(MediaType.APPLICATION_XML).get(String.class));
		
		String data = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><Country Name=\"au's name\"><Code>au</Code></Country>";
		
		try {

			WebResource webResource = ClientUtil.getClient()
					.resource("http://localhost:8080/RestFulJersey17/rest/xml/country");

			ClientResponse response = webResource.type("application/xml")
					.post(ClientResponse.class, data);

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
