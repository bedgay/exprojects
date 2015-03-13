package test.json;

import java.util.Map;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonTest {
	
	@Test
	public void readValue() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			Map map = mapper.readValue("{\"com.simsol.ct4.broadcastmanagement.core.entities\":[\"ROLE_BROADCAST_ALL_ACCESS\"],\"com.simsol.ct4.core.profilegroup.domain.ProfileGroup\":[\"ROLE_PROFILE_ALL_ACCESS\"],\"com.simsol.ct4.event.core.entities.Event\":[\"ROLE_EVENT_ALL_ACCESS\"],\"com.simsol.ct4.broadcasttemplate.core.entities\":[\"ROLE_BROADCAST_ALL_ACCESS\"]}", Map.class);
			map = null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
