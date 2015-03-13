package javaz.spring.listener.web;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import javaz.common.consts.SecurityConst;
import javaz.common.logger.CustomLogger;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
public class HttpSessionHandledListener implements HttpSessionListener, HttpSessionAttributeListener, 
		HttpSessionActivationListener, HttpSessionBindingListener, ApplicationContextAware {

	private CustomLogger log = CustomLogger.getLogger(this.getClass());

	// Define by: HttpSessionListener
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		log.info("SESSION_LISTENER: sessionCreated: " + event.getSession().getId());
	}

	// Define by: HttpSessionListener
	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		log.info("SESSION_LISTENER: sessionDestroyed: " + event.getSession().getId());
	}

	// Define by: HttpSessionAttributeListener
	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		log.info("SESSION_LISTENER: attributeAdded");
		HttpSession session = event.getSession();
		if (session != null && session.getAttribute(SecurityConst.SESSION_USER) != null) {
			log.info("value: " + event.getSession().getAttribute(SecurityConst.SESSION_USER));
		}
	}

	// Define by: HttpSessionAttributeListener
	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		log.info("SESSION_LISTENER: attributeRemoved");
		HttpSession session = event.getSession();
		if (session != null && session.getAttribute(SecurityConst.SESSION_USER) != null) {
			log.info("value: " + event.getSession().getAttribute(SecurityConst.SESSION_USER));
		}
	}

	// Define by: HttpSessionAttributeListener
	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		log.info("SESSION_LISTENER: attributeReplaced");
		HttpSession session = event.getSession();
		if (session != null && session.getAttribute(SecurityConst.SESSION_USER) != null) {
			log.info("value: " + event.getSession().getAttribute(SecurityConst.SESSION_USER));
		}
	}
	
	// Define by: HttpSessionActivationListener
	@Override
	public void sessionWillPassivate(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		
	}

	// Define by: HttpSessionActivationListener
	@Override
	public void sessionDidActivate(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		
	}

	// Define by: HttpSessionBindingListener
	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		
	}

	// Define by: HttpSessionBindingListener
	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		
	}

	// Add context in Sevlet3.0 instead of xml-config in Servlet2.5 
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

}
