package javaz.spring.event.listener;

import javaz.common.logger.CustomLogger;
import javaz.spring.event.CustomEvent;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class CustomEventListener implements ApplicationListener<CustomEvent> {

	private CustomLogger log = CustomLogger.getLogger(this.getClass());

	@Override
	public void onApplicationEvent(CustomEvent event) {
		if (event.getType() == null) {
			log.info("Process without event type");
		} else if (event.getType().equals(CustomEvent.Type.A)) {
			log.info("Process for event A");
		} else if (event.getType().equals(CustomEvent.Type.B)) {
			log.info("Process for event B");
		} else if (event.getType().equals(CustomEvent.Type.C)) {
			log.info("Process for event C");
		} else {
			log.info("Process for another event");
		}
		log.info(event.getData());
	}

}
