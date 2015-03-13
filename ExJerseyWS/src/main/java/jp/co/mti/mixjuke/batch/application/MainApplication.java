package jp.co.mti.mixjuke.batch.application;

import java.sql.SQLException;
import java.util.concurrent.Executors;

import jp.co.mti.mixjuke.batch.adapter.MSSQLConnector;
import jp.co.mti.mixjuke.batch.adapter.MySQLConnector;
import jp.co.mti.mixjuke.batch.controller.DataTransfer;
import jp.co.mti.mixjuke.util.MixjukeConstants;
import jp.co.mti.mixjuke.util.PropertyUtil;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * User: naminh
 * Date: 10/11/13
 * Time: 1:47 PM
 */
public class MainApplication {
    private static final Logger LOGGER = LogManager.getLogger(Configuration.SYNC_LOGGER_NAME);
    public void syncTask() {
        if(!Boolean.valueOf(PropertyUtil.getProperty(MixjukeConstants.ACTIVE_SCHEDULE))){
            LOGGER.info("No job schedule (standby)");
            return;
        }
        LOGGER.info("Run jobs (active)");
        
        Configuration config = new Configuration();
        MySQLConnector mycon = new MySQLConnector(config);
        MSSQLConnector mscon = new MSSQLConnector(config);
        try {
			mycon.getConnection().setNetworkTimeout(Executors.newFixedThreadPool(20), Integer.MAX_VALUE);
		} catch (SQLException e) {
            LOGGER.error("Cannot set connect timeout.");
	        mscon.close();
	        mycon.close();
		}
        
        if ((mycon.getConnection() != null) && (mscon.getConnection() != null)) {
            DataTransfer dt = new DataTransfer(mscon, mycon);
            dt.transfer();
        } else {
            LOGGER.error("Cannot connect databases.");
            if (mycon.getConnection() == null)
                LOGGER.info("Error MySQL connection.");
            if (mscon.getConnection() == null)
                LOGGER.info("Error MSSQL connection.");
        }
        mscon.close();
        mycon.close();
    }
}