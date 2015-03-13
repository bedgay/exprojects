package javaz.spring.listener.context;

import javaz.common.logger.CustomLogger;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;

@Component
public class ContextStartedListener implements ApplicationListener<ContextStartedEvent>{

	private CustomLogger log = CustomLogger.getLogger(this.getClass());
	
	@Override
	public void onApplicationEvent(ContextStartedEvent event) {
		log.info("LISTENER: ContextStartedEvent");
	}

}
