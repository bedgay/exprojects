package org.shitoryu.common.log;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * @author SUCCESS\tungo
 * @date Oct 20, 2014 1:43:19 PM
 */
public class CommonLogger {

	private Logger log;
	private Class<?> clazz;
	private boolean isIgnore = Boolean.TRUE;
	private static List<Class<?>> ignoreClass = new ArrayList<Class<?>>();
	
	static {
		// Custom event handle
//		ignoreClass.add(CustomEventListener.class);
	}

	public static CommonLogger getLogger(Class<?> clazz) {
		return new CommonLogger(clazz);
	}
	
	public CommonLogger(Class<?> clazz) {
		this.clazz = clazz;
		log = Logger.getLogger(clazz);
	}
	
	public CommonLogger(String name) {
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
