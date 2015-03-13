package javaz.spring.listener.context;

import javaz.common.logger.CustomLogger;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

@Component
public class ContextClosedListener implements ApplicationListener<ContextClosedEvent> {

	private CustomLogger log = CustomLogger.getLogger(this.getClass());

	@Override
	public void onApplicationEvent(ContextClosedEvent event) {
		log.info("LISTENER: ContextClosedEvent");
	}

}
