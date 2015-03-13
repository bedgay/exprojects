package javaz.spring.service.impl;

import javaz.common.logger.CustomLogger;
import javaz.spring.service.BusinessService;

import org.springframework.stereotype.Component;

@Component
public class BusinessServiceImpl implements BusinessService {
	
	private CustomLogger log = CustomLogger.getLogger(this.getClass());
	
	@Override
	public void initCountries() {
		log.info("Initialization for Countries...");
	}

}
