package javaz.common.logger;

import java.util.ArrayList;
import java.util.List;

import javaz.spring.aop.CustomAOP;
import javaz.spring.aop.CustomTransaction;
import javaz.spring.aop.LoggingAspect;
import javaz.spring.event.listener.CustomEventListener;
import javaz.spring.listener.context.ContextClosedListener;
import javaz.spring.listener.context.ContextRefreshedListerner;
import javaz.spring.listener.context.ContextStartedListener;
import javaz.spring.listener.context.ContextStoppedListener;
import javaz.spring.listener.security.AuthenticationSuccessListener;
import javaz.spring.listener.web.HttpServletRequestListener;
import javaz.spring.listener.web.HttpSessionHandledListener;
import javaz.spring.listener.web.RequestHandledListener;
import javaz.spring.listener.web.ServletContextHandleListener;
import javaz.spring.listener.web.ServletRequestHandledListener;
import javaz.spring.mvc.controller.SecurityController;
import javaz.spring.scheduler.AnnotationScheduler;
import javaz.spring.scheduler.XmlScheduler;
import javaz.spring.service.impl.BusinessServiceImpl;
import javaz.spring.service.impl.DatabaseServiceImpl;
import javaz.spring.service.impl.SecurityServiceImpl;

import org.apache.log4j.Logger;

public class CustomLogger {

	private Logger log;
	private Class<?> clazz;
	private boolean isIgnore = Boolean.TRUE;
	private static List<Class<?>> ignoreClass = new ArrayList<Class<?>>();
	
	static {
		// Custom event handle
		ignoreClass.add(CustomEventListener.class);
		
		// Context event handle
		ignoreClass.add(ContextRefreshedListerner.class);
		ignoreClass.add(ContextStartedListener.class);
		ignoreClass.add(ContextStoppedListener.class);
		ignoreClass.add(ContextClosedListener.class);
		
		// HTTP event handle
		ignoreClass.add(ServletRequestHandledListener.class);
		ignoreClass.add(HttpServletRequestListener.class);
		ignoreClass.add(RequestHandledListener.class);
		ignoreClass.add(HttpSessionHandledListener.class);
		ignoreClass.add(ServletContextHandleListener.class);

		// Security event handle
		ignoreClass.add(AuthenticationSuccessListener.class);
		
		// Service
		ignoreClass.add(BusinessServiceImpl.class);
		ignoreClass.add(DatabaseServiceImpl.class);
		ignoreClass.add(SecurityServiceImpl.class);
		
		// Controller
		ignoreClass.add(SecurityController.class);
		
		// AOP
		ignoreClass.add(LoggingAspect.class);
		ignoreClass.add(CustomTransaction.class);
		ignoreClass.add(CustomAOP.class);
		
		// Scheduler
		ignoreClass.add(XmlScheduler.class);
//		ignoreClass.add(AnnotationScheduler.class);
		
	}

	public static CustomLogger getLogger(Class<?> clazz) {
		return new CustomLogger(clazz);
	}
	
	public CustomLogger(Class<?> clazz) {
		this.clazz = clazz;
		log = Logger.getLogger(clazz);
	}
	
	public CustomLogger(String name) {
		log = Logger.getLogger(name);
	}
	
	private boolean checkIgnore() {
		if (isIgnore && ignoreClass.indexOf(clazz) >= 0) {
			return true;
		}
		return false;
	}

	public void info(Object message) {
		if (!checkIgnore())
			log.info(message);
	}

	public void info(Object message, Throwable t) {
		if (!checkIgnore())
			log.info(message, t);
	}

	public void error(Object message) {
		if (!checkIgnore())
			log.info(message);
	}

	public void error(Object message, Throwable t) {
		if (!checkIgnore())
			log.info(message, t);
	}

}
