package javaz.spring.listener.web;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

import javaz.common.logger.CustomLogger;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
public class HttpServletRequestListener implements ServletRequestListener, ServletRequestAttributeListener,
		ApplicationContextAware {
	
	private CustomLogger log = CustomLogger.getLogger(this.getClass());

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		if (applicationContext instanceof WebApplicationContext) {
			((WebApplicationContext) applicationContext).getServletContext()
					.addListener(this);
		} else {
			// Either throw an exception or fail gracefully, up to you
			throw new RuntimeException(
					"Must be inside a web application context");
		}
	}

	@Override
	public void requestInitialized(ServletRequestEvent event) {
		log.info("REQUEST_LISTENER: requestInitialized" + event.getServletRequest().getRemoteAddr());
	}
	
	@Override
	public void requestDestroyed(ServletRequestEvent event) {
		log.info("REQUEST_LISTENER: requestDestroyed" + event.getServletRequest().getRemoteAddr());
	}

	@Override
	public void attributeAdded(ServletRequestAttributeEvent event) {
		log.info("REQUEST_LISTENER: attributeAdded" + event.getServletRequest().getRemoteAddr());
	}

	@Override
	public void attributeRemoved(ServletRequestAttributeEvent event) {
		log.info("REQUEST_LISTENER: attributeRemoved" + event.getServletRequest().getRemoteAddr());
	}

	@Override
	public void attributeReplaced(ServletRequestAttributeEvent event) {
		log.info("REQUEST_LISTENER: attributeReplaced" + event.getServletRequest().getRemoteAddr());
	}

}
