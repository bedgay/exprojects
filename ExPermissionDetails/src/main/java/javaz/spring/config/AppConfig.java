package javaz.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppConfig {
	
	@Value("${permission.bypass}")
	private boolean permissionBypass;

	public boolean isPermissionBypass() {
		return permissionBypass;
	}
	
}
