package javaz.spring.listener.web;

import javaz.common.logger.CustomLogger;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.RequestHandledEvent;

@Component
public class RequestHandledListener implements ApplicationListener<RequestHandledEvent> {

	private CustomLogger log = CustomLogger.getLogger(this.getClass());
	
	@Override
	public void onApplicationEvent(RequestHandledEvent event) {
		log.info("LISTENER: RequestHandledEvent");
	}
}
