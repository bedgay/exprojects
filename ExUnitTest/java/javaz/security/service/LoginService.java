package javaz.security.service;

import java.util.HashMap;
import java.util.Map;

public interface LoginService {

	public static Map<String, String> user = new HashMap<String, String>();

	public String login(String username, String password);

	public Boolean changePassword(String username, String oldPassword,
			String newPassword);
}
