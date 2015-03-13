package jp.co.mti.mixjuke.batch.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by nhphuoc on 12/11/13.
 */
public class StrAuth extends AbstractSync {
	
    public StrAuth(Connection con1, Connection con2, boolean isFirstTime) {
    	super(con1, con2, isFirstTime);
    }

    public void run() {
        LOGGER.info("Starting synchronization MJ_STR_AUTH table");
        super.run();
    }

	protected void sync() throws SQLException {
		String msSQL = "SELECT * FROM " +
		        "(SELECT Row_Number() OVER (ORDER BY REGION_ID) as RowIndex,REGION_ID,CONTENT_RELEASE_DATE,WINDOW_DAYS,ANDROID_DELIVERY_ENABLE_FLG," +
		        "               ANDROID_SEARCH_FLG,ANDROID_RELEASE_START_DATETIME,ANDROID_RELEASE_END_DATETIME,ANDROID_FREE_ENABLE_FLG,ANDROID_TRIAL_ENABLE_FLG," +
		        "               ANDROID_PAYING_ENABLE_FLG, IOS_DELIVERY_ENABLE_FLG, IOS_SEARCH_FLG, IOS_RELEASE_START_DATETIME, IOS_RELEASE_END_DATETIME,IOS_FREE_ENABLE_FLG," +
		        "               IOS_TRIAL_ENABLE_FLG, IOS_PAYING_ENABLE_FLG  FROM dbo.V_SITESTG_METALINK_MIXJUKE_STREAMING_TBL) AS Sub " +
		        "              WHERE Sub.RowIndex >= ? AND Sub.RowIndex < ?";
		PreparedStatement msStatement = con1.prepareStatement(msSQL);
		String mySQL = "INSERT INTO `MJ_STR_AUTH`(`region_id`, `content_release_date`, `window_days`, `android_delivery_enable_flg`," +
		        "  `android_search_flg`,`android_release_start_datetime`,`android_release_end_datetime`,`android_free_enable_flg`," +
		        "  `android_trial_enable_flg`,`android_paying_enable_flg`,`ios_delivery_enable_flg`,`ios_search_flg`," +
		        "  `ios_release_start_datetime`,`ios_release_end_datetime`,`ios_free_enable_flg`,`ios_trial_enable_flg`," +
		        "  `ios_paying_enable_flg`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pmyStatement = con2.prepareStatement(mySQL);
		int page = 0;
		boolean repeat;
		int pageSize = 1000;
		int count;
		//Delete all data from table MJ_STR_AUTH
		java.sql.Statement delStatement = con2.createStatement();
		delStatement.execute("TRUNCATE TABLE MJ_STR_AUTH");
		delStatement.close();
		//Copy data from V_SITESTG_METALINK_MIXJUKE_STREAMING_TBL
		do {
		    msStatement.setInt(1, page * pageSize);
		    msStatement.setInt(2, (page + 1) * pageSize);
		    ResultSet msRS = msStatement.executeQuery();
		    count = 0;
		    while (msRS.next()) {
		        count++;
		        pmyStatement.setInt(1, msRS.getInt("REGION_ID"));
		        pmyStatement.setTimestamp(2, msRS.getTimestamp("CONTENT_RELEASE_DATE"));
		        pmyStatement.setInt(3, msRS.getInt("WINDOW_DAYS"));
		        pmyStatement.setBoolean(4, msRS.getBoolean("ANDROID_DELIVERY_ENABLE_FLG"));
		        pmyStatement.setBoolean(5, msRS.getBoolean("ANDROID_SEARCH_FLG"));
		        pmyStatement.setTimestamp(6, msRS.getTimestamp("ANDROID_RELEASE_START_DATETIME"));
		        pmyStatement.setTimestamp(7, msRS.getTimestamp("ANDROID_RELEASE_END_DATETIME"));
		        pmyStatement.setBoolean(8, msRS.getBoolean("ANDROID_FREE_ENABLE_FLG"));
		        pmyStatement.setBoolean(9, msRS.getBoolean("ANDROID_TRIAL_ENABLE_FLG"));
		        pmyStatement.setBoolean(10, msRS.getBoolean("ANDROID_PAYING_ENABLE_FLG"));
		        pmyStatement.setBoolean(11, msRS.getBoolean("IOS_DELIVERY_ENABLE_FLG"));
		        pmyStatement.setBoolean(12, msRS.getBoolean("IOS_SEARCH_FLG"));
		        pmyStatement.setTimestamp(13, msRS.getTimestamp("IOS_RELEASE_START_DATETIME"));
		        pmyStatement.setTimestamp(14, msRS.getTimestamp("IOS_RELEASE_END_DATETIME"));
		        pmyStatement.setBoolean(15, msRS.getBoolean("IOS_FREE_ENABLE_FLG"));
		        pmyStatement.setBoolean(16, msRS.getBoolean("IOS_TRIAL_ENABLE_FLG"));
		        pmyStatement.setBoolean(17, msRS.getBoolean("IOS_PAYING_ENABLE_FLG"));
		        pmyStatement.execute();
		    }
		    if (count > 0) {
		        page++;
		        repeat = true;
		    } else {
		        repeat = false;
		    }
		} while (repeat);
		pmyStatement.close();
	}
    
}
