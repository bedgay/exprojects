package jp.co.mti.mixjuke.batch.adapter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import jp.co.mti.mixjuke.batch.application.Configuration;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * User: naminh
 * Date: 10/8/13
 * Time: 11:20 AM
 */
public abstract class DBConnector {
    private static final Logger LOGGER = LogManager.getLogger(Configuration.SYNC_LOGGER_NAME);
    private Connection conn;

    public DBConnector() {
    }
    public void close() {
        try {
            if(this.conn != null){
            this.conn.close();
            }
        } catch(SQLException e) {
             e.printStackTrace();
        }
    }
    public abstract String getDriverClass();
    protected void dbConnect(String db_connect_string,
                                String db_userid,
                                String db_password)
    {
        //Connection conn = null;
        try {
            LOGGER.info("Driver="+this.getDriverClass());
            Class.forName(this.getDriverClass());
            this.conn = DriverManager.getConnection(db_connect_string,db_userid,db_password);
            LOGGER.info(this.getDriverClass() + " : Connected.");
        } catch (Exception e) {
        	LOGGER.error("Connection error:" + db_connect_string, e);
            e.printStackTrace();
        }

    }
    public Connection getConnection(){
        return this.conn;
    }
}