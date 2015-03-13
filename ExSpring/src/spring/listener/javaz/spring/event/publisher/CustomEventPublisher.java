package javaz.spring.event.publisher;

import javaz.spring.event.CustomEvent;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

@Component
public class CustomEventPublisher implements ApplicationEventPublisherAware {

	private ApplicationEventPublisher publisher;

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
		this.publisher = publisher;
	}

	public void publish() {
		publisher.publishEvent(new CustomEvent(this));
	}
	
	public void publish(final Object data) {
		publisher.publishEvent(new CustomEvent(this, data));
	}
	
	public void publish(final CustomEvent.Type type, final Object data) {
		publisher.publishEvent(new CustomEvent(this, type, data));
	}

	public void asynPublish() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				publisher.publishEvent(new CustomEvent(this));
			}
		}).start();
	}
	
	public void asynPublish(final Object data) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				publisher.publishEvent(new CustomEvent(this, data));
			}
		}).start();
	}
	
	public void asynPublish(final CustomEvent.Type type, final Object data) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				publisher.publishEvent(new CustomEvent(this, type, data));
			}
		}).start();
	}

}
