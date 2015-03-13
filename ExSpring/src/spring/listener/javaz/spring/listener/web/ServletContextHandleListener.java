package javaz.spring.listener.web;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

import javaz.common.consts.SecurityConst;
import javaz.common.logger.CustomLogger;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
public class ServletContextHandleListener implements ServletContextAttributeListener, // ServletContextListener
		ApplicationContextAware {
	
	private CustomLogger log = CustomLogger.getLogger(this.getClass());

//	@Override
//	public void contextInitialized(ServletContextEvent arg0) {
//		log.info("SERVLET_CONTEXT_LISTENER: contextInitialized");
//	}
//
//	@Override
//	public void contextDestroyed(ServletContextEvent arg0) {
//		log.info("SERVLET_CONTEXT_LISTENER: contextDestroyed");
//	}
	
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
	public void attributeAdded(ServletContextAttributeEvent event) {
		log.info("SERVLET_CONTEXT_LISTENER: attributeAdded: " + event.getServletContext().getAttribute(SecurityConst.CONTEXT_USER));
	}

	@Override
	public void attributeRemoved(ServletContextAttributeEvent event) {
		log.info("SERVLET_CONTEXT_LISTENER: attributeRemoved: " + event.getServletContext().getAttribute(SecurityConst.CONTEXT_USER));
	}

	@Override
	public void attributeReplaced(ServletContextAttributeEvent event) {
		log.info("SERVLET_CONTEXT_LISTENER: attributeReplaced: " + event.getServletContext().getAttribute(SecurityConst.CONTEXT_USER));
	}

}
