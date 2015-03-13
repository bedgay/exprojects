package javaz.spring.listener.context;

import javaz.common.logger.CustomLogger;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.stereotype.Component;

@Component
public class ContextStoppedListener implements ApplicationListener<ContextStoppedEvent>{

	private CustomLogger log = CustomLogger.getLogger(this.getClass());
	
	@Override
	public void onApplicationEvent(ContextStoppedEvent event) {
		log.info("LISTENER: ContextStoppedEvent");
	}
}
