package jp.co.mti.mixjuke.batch.archive;

import jp.co.mti.mixjuke.batch.adapter.DBConnector;

import org.apache.log4j.LogManager;

/**
 * Database connection with many types
 * @author natu
 *
 */
public class ArchiveDBConnector extends DBConnector {
	
	private static final org.apache.log4j.Logger LOGGER = LogManager
			.getLogger(ArchiveConfiguration.ARCHIVE_LOGGER_NAME);
	
	private ArchiveConfiguration config;

	public ArchiveDBConnector(ArchiveConfiguration config) {
		this.config = config;
		LOGGER.info("Create db connection: " + config.getUrl());
		this.dbConnect(config.getUrl(), config.getUsername(),
				config.getPassword());
	}

	public String getDriverClass() {
		return this.config.getDriverClassName();
	}

}
