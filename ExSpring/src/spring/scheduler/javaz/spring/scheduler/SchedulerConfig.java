package javaz.spring.scheduler;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author SUCCESS\tungo
 * @date Aug 22, 2014 6:18:55 PM
 */
@Configuration
@EnableAsync
@EnableScheduling
//@PropertySource("classpath:scheduler.properties")
public class SchedulerConfig {

}
