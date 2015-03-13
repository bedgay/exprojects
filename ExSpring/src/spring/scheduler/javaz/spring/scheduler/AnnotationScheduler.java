package javaz.spring.scheduler;

import java.util.Date;

import javaz.common.logger.CustomLogger;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author SUCCESS\tungo
 * @date Aug 22, 2014 5:59:52 PM
 */
@Component
public class AnnotationScheduler {
	
	private CustomLogger log = CustomLogger.getLogger(this.getClass());
	
	@Scheduled(fixedDelay = 5000)
	public void fixedDelay() {
		//log.info("fixedDelay() at " + new Date());
	}

	@Scheduled(fixedRate = 5000)
	public void fixedRate() {
		//log.info("fixedRate() at " + new Date());
	}
	
	@Scheduled(initialDelay = 1000, fixedRate = 5000)
	public void initialDelay() {
		//log.info("initialDelay() at " + new Date());
	}

	@Scheduled(cron = "*/5 * * * * MON-FRI")
	public void cron() {
		//log.info("cron() at " + new Date());
	}

	@Scheduled(cron = "*/5 * * * * MON-FRI")
	@Async(value = "asyncAnnotationScheduler")
	public void async() {
		log.info("Async() at " + new Date());
	}
	
}
