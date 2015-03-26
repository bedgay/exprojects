package jp.co.mti.mixjuke.batch.adapter;

import jp.co.mti.mixjuke.batch.application.Configuration;

import org.apache.log4j.LogManager;

/**
 * User: naminh
 * Date: 10/11/13
 * Time: 5:58 PM
 */
public class MySQLConnector extends DBConnector{
    private static final org.apache.log4j.Logger LOGGER = LogManager.getLogger(Configuration.SYNC_LOGGER_NAME);
    private Configuration config;
    public MySQLConnector(Configuration config){
        this.config = config;
        LOGGER.info(config.getDbDest() + " " + config.getDbDestUsername());
        this.dbConnect(config.getDbDest(), config.getDbDestUsername(), config.getDbDestPassword());
    }
    public String getDriverClass(){
        return this.config.getDbDestDriver();
    }
}