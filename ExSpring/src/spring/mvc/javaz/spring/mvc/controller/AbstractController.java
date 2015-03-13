package javaz.spring.mvc.controller;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractController {

	private static final String MODEL_PERMISSION = "PERMISSION";
	private static final String MODEL_STATUS = "STATUS";
	protected static final String MODEL_DATA = "DATA";
	
	protected Map<String, Object> createModel(boolean status, boolean permission) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put(MODEL_STATUS, status);
		model.put(MODEL_PERMISSION, permission);
		return model;
	}

}
