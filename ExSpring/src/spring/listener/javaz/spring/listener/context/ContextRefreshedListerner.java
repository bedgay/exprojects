package javaz.spring.listener.context;

import javaz.common.logger.CustomLogger;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class ContextRefreshedListerner implements ApplicationListener<ContextRefreshedEvent> {

	private CustomLogger log = CustomLogger.getLogger(this.getClass());
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		log.info("LISTENER: ContextRefreshedEvent");
	}

}
