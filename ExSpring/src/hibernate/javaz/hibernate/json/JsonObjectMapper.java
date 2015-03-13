package javaz.hibernate.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

/**
 * @author SUCCESS\tungo
 * @date Aug 6, 2014 10:00:35 AM
 */
public class JsonObjectMapper extends ObjectMapper {
	
	private static final long serialVersionUID = 1L;

	public JsonObjectMapper() {
        Hibernate4Module hm = new Hibernate4Module();
        registerModule(hm); 
        hm.configure(Hibernate4Module.Feature.FORCE_LAZY_LOADING, true);
    }

}
