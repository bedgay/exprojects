package javaz.spring.listener.web;

import javaz.common.logger.CustomLogger;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.ServletRequestHandledEvent;

@Component
public class ServletRequestHandledListener implements ApplicationListener<ServletRequestHandledEvent> {

	private CustomLogger log = CustomLogger.getLogger(this.getClass());
	
	@Override
	public void onApplicationEvent(ServletRequestHandledEvent event) {
		log.info("LISTENER: ServletRequestHandledEvent");
	}
	
}
