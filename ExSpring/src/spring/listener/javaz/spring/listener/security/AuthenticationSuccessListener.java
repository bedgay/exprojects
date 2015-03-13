package javaz.spring.listener.security;

import javaz.common.logger.CustomLogger;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {

	private CustomLogger log = CustomLogger.getLogger(this.getClass());
	
	@Override
	public void onApplicationEvent(AuthenticationSuccessEvent event) {
		log.info("LISTENER: AuthenticationSuccessEvent");
	}
}
