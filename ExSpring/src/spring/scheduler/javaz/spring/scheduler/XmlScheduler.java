package javaz.spring.scheduler;

import java.util.Date;

import javaz.common.logger.CustomLogger;

import org.springframework.stereotype.Component;

/**
 * @author SUCCESS\tungo
 * @date Aug 20, 2014 4:19:35 PM
 */
@Component
public class XmlScheduler {
	
	private CustomLogger log = CustomLogger.getLogger(this.getClass());
	
	private static boolean isRunning= Boolean.FALSE;
	
	public void run() {
		
		synchronized (log) {
			if (isRunning) {
				log.info("----------");
			} else {
				log.info(new Date());
			}
			isRunning = !isRunning;
		}
		
	}

}
