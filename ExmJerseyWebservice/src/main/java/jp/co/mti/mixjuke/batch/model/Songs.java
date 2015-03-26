package jp.co.mti.mixjuke.batch.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

import jp.co.mti.mixjuke.util.DateUtil;

/**
 * @User: naminh
 * @Date: 10/15/13
 * @Time: 3:54 PM
 * @Edit: 11/13/13 by nhphuoc
 */
public class Songs extends AbstractSync {
	private static final String DATE_TIME_FORMAT = "MM/dd/yyyy HH:mm:ss";
	private static final int MJ_SONGS_TABLE_ID = 2;

	public Songs(Connection con1, Connection con2, boolean isFirstTime) {
		super(con1, con2, isFirstTime);
	}

	public void run() {
		LOGGER.info("Starting synchronization MJ_SONGS table");
		super.run();
	}

	protected void sync() throws SQLException {
		Statement myStatement = con2.createStatement();
		ResultSet myRS = myStatement
				.executeQuery("SELECT last_sync_datetime FROM MJ_SYNC WHERE id="
						+ Songs.MJ_SONGS_TABLE_ID);
		if (myRS.next()) {
			/*********** Get the arrange date **********/
			Timestamp lastSyncDate = myRS
					.getTimestamp("last_sync_datetime");
			myRS.close();
			LOGGER.info("Last sychronized timestamp is "
					+ DateUtil.toString(lastSyncDate, DATE_TIME_FORMAT));
			Timestamp currentDate = new Timestamp(Calendar.getInstance()
					.getTimeInMillis());
			LOGGER.info("Current timestamp is "
					+ DateUtil.toString(currentDate, DATE_TIME_FORMAT));

			/********** Get data from MSSQL **********/
			LOGGER.info("Lookup V_SITESTG_METALINK_CONTENT_TBL");
			String msSQL = "SELECT REGION_ID,CONTENT_TITLE,CONTENT_TITLE_KANA,CONTENT_TITLE_ALPHA,PRODUCT_ID,AUDITION_ENABLE_FLG,CONTENT_FILE_NAME,ENABLE_FLG,CONTENT_PLAY_TIME FROM dbo.V_SITESTG_METALINK_CONTENT_TBL WHERE LAST_UPDATE_DATETIME >=? AND REGION_ID IN (SELECT REGION_ID FROM dbo.V_SITESTG_METALINK_MIXJUKE_STREAMING_TBL)";
			PreparedStatement msStatement = con1.prepareStatement(msSQL);
			msStatement.setTimestamp(1, lastSyncDate);
			ResultSet msRS = msStatement.executeQuery();

			/********** Save data to MySQL **********/
			String mySQL = "INSERT INTO MJ_SONGS(PID, TITLE, TITLE_KANA, TITLE_ALPHA, PRODUCT_ID, FILE_NAME, TRIAL_LENGTH, LENGTH) VALUES (?,?,?,?,?,?,?,?) ON DUPLICATE KEY UPDATE TITLE=VALUES(TITLE),TITLE_KANA=VALUES(TITLE_KANA),TITLE_ALPHA=VALUES(TITLE_ALPHA),PRODUCT_ID=VALUES(PRODUCT_ID),FILE_NAME=VALUES(FILE_NAME),TRIAL_LENGTH=VALUES(TRIAL_LENGTH),LENGTH=VALUES(LENGTH)";
			PreparedStatement pmyStatement = con2.prepareStatement(mySQL);
			// Delete all songs which is not in MJ_STR_AUTH
			// The synchronization for MJ_STR_AUTH is finished before.
			java.sql.Statement delStatement = con2.createStatement();
			delStatement
					.execute("DELETE FROM MJ_SONGS WHERE pid NOT IN (SELECT region_id FROM MJ_STR_AUTH)");
			delStatement.close();
			while (msRS.next()) {
				String log = "REGION_ID =" + msRS.getInt("REGION_ID");
				int enableFlg = msRS.getInt("ENABLE_FLG");
				if (enableFlg == 0) {
					// Delete the corelation row in the MixJuke DB if any
					int result = myStatement
							.executeUpdate("DELETE FROM MJ_SONGS WHERE pid="
									+ msRS.getInt("REGION_ID"));
					log = log + " is deleted "
							+ ((result == 1) ? "sucessfully" : "failed");
				} else {
					// Insert or update
					pmyStatement.setInt(1, msRS.getInt("REGION_ID"));
					pmyStatement.setString(2,
							msRS.getString("CONTENT_TITLE"));
					pmyStatement.setString(3,
							msRS.getString("CONTENT_TITLE_KANA"));
					pmyStatement.setString(4,
							msRS.getString("CONTENT_TITLE_ALPHA"));
					pmyStatement.setString(5, msRS.getString("PRODUCT_ID"));
					pmyStatement.setString(6,
							msRS.getString("CONTENT_FILE_NAME"));
					pmyStatement.setInt(7, msRS
							.getInt("AUDITION_ENABLE_FLG") == 1 ? 30 : 0);
					Time strLength = msRS.getTime("CONTENT_PLAY_TIME");
					int secLength = 0;
					if (strLength != null) {
						secLength = (int) (strLength.getTime() / 1000);
					}
					pmyStatement.setInt(8, secLength);
					pmyStatement.execute();
					log = log + " is updated/inserted";
				}
				LOGGER.info(log);
			}
			msRS.close();

			/********** Sate current date to the last sync date **********/
			pmyStatement = con2
					.prepareStatement("UPDATE MJ_SYNC SET last_sync_datetime=? WHERE id="
							+ Songs.MJ_SONGS_TABLE_ID);
			pmyStatement.setTimestamp(1, currentDate);

			pmyStatement.execute();
			pmyStatement.close();
		}
	}
	
}
