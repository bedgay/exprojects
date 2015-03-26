package jp.co.mti.mixjuke.batch.archive;

import jp.co.mti.mixjuke.batch.adapter.DBConnector;
import jp.co.mti.mixjuke.util.MixjukeConstants;
import jp.co.mti.mixjuke.util.PropertyUtil;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Archive Old Mdl data scheduler task
 * @author natu
 *
 */
public class ArchiveOldMdlData {
	private static final Logger LOGGER = LogManager.getLogger(ArchiveConfiguration.ARCHIVE_LOGGER_NAME);
	
	public void archiveTask() {
        if(!Boolean.valueOf(PropertyUtil.getProperty(MixjukeConstants.ACTIVE_SCHEDULE))){
            LOGGER.info("No job schedule (standby)");
            return;
        }
        LOGGER.info("Run jobs (active)");
        
		ArchiveConfiguration configSrc = new ArchiveConfiguration();
		ArchiveConfiguration configDict = new ArchiveConfiguration(ArchiveConfiguration.MDL_PREFIX);

		DBConnector conSrc = new ArchiveDBConnector(configSrc);
		DBConnector conDict = new ArchiveDBConnector(configDict);
		
		if (conSrc.getConnection() == null) {
			LOGGER.info("Can not connect to database to archive with url: " + configSrc.getUrl());
		} else if (conDict.getConnection() == null) {
			LOGGER.info("Can not connect to database to archive with url: " + configDict.getUrl());
		} else {
			try {
				ArchiveTableStreaminglog archive = new ArchiveTableStreaminglog(conSrc, conDict);
				int count = archive.transfer();
				if (count > 0) {
					LOGGER.info("Archive data successfully.");
				} else {
					LOGGER.info("Does not have data to archive.");
				}
			} catch (Exception e) {
				LOGGER.info("Can not archive data.", e);
			}
		}
	}
}
