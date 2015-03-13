package jp.co.mti.mixjuke.batch.adapter;

/**
 * User: naminh
 * Date: 10/8/13
 * Time: 1:48 PM
 */

import jp.co.mti.mixjuke.batch.application.Configuration;

import org.apache.log4j.LogManager;

public class MSSQLConnector extends  DBConnector
{
    private static final org.apache.log4j.Logger LOGGER = LogManager.getLogger(Configuration.SYNC_LOGGER_NAME);
    private Configuration config;
   public MSSQLConnector(Configuration config){
     this.config = config;
       LOGGER.info(config.getDbSource() + " " + config.getDbSourceUsername());
      this.dbConnect(config.getDbSource(),config.getDbSourceUsername(),config.getDbSourcePassword());
   }
    public String getDriverClass(){
        //return "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        //jDTS driver for MS SQL Server
        return this.config.getDbSourceDriver();
    }
}