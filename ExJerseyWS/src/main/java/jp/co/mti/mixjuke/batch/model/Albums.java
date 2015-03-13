package jp.co.mti.mixjuke.batch.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @User: naminh 
 * @Date: 10/15/13 
 * @Time: 9:55 AM 
 * @Edited 11/12/13 by nhphuoc
 */
public class Albums extends AbstractSync {
	private static final int MJ_ALBUMS_TABLE_ID = 1;

	public Albums(Connection con1, Connection con2, boolean isFirstTime) {
		super(con1, con2, isFirstTime);
	}

	public void run() {
		LOGGER.info("Starting synchronization MJ_ALBUMS table");
		super.run();
	}

	protected void sync() throws SQLException {
		// Query to the MixJuke database the get the last syncronization
		// timestamp for MJ_ALBUMS table
		Statement myStatement = this.con2.createStatement();
		ResultSet myRS = myStatement
				.executeQuery("SELECT last_sync_datetime FROM MJ_SYNC WHERE id="
						+ Albums.MJ_ALBUMS_TABLE_ID);
		if (myRS.next()) {
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			Timestamp lastSync = myRS.getTimestamp("last_sync_datetime");
			String text = df.format(lastSync);
			LOGGER.info("Last sychronized timestamp is " + text);
			myRS.close();
			// Store the current timestamp to local variable.
			Timestamp currentDate = new Timestamp(Calendar.getInstance()
					.getTimeInMillis());
			text = df.format(currentDate);
			LOGGER.info("Current timestamp is " + text);
			// Query to MICS database to get new update record since the
			// last synchronization.
			String msSQL = "SELECT PACKAGE_ID,PACKAGE_TITLE,PACKAGE_TITLE_KANA,JACKET_IMG_FN,ENABLE_FLG,SALE_DATE FROM dbo.V_SITESTG_METALINK_PACKAGE_TBL WHERE LAST_UPDATE_DATETIME >=?";
			PreparedStatement msStatement = con1.prepareStatement(msSQL);
			msStatement.setTimestamp(1, lastSync);
			ResultSet msRS = msStatement.executeQuery();
			String mySQL = "INSERT INTO MJ_ALBUMS (ALBUM_ID, TITLE, TITLE_KANA, JACKET_IMG,SALE_DATE) VALUES (?,?,?,?,?) ON DUPLICATE KEY UPDATE "
					+ "TITLE=VALUES(TITLE),TITLE_KANA=VALUES(TITLE_KANA),JACKET_IMG=VALUES(JACKET_IMG),SALE_DATE=VALUES(SALE_DATE)";
			PreparedStatement pmyStatement = con2.prepareStatement(mySQL);
			int count = 0;
			while (msRS.next()) {
				count++;
				String log = "ALBUM_ID =" + msRS.getInt("PACKAGE_ID");
				int enableFlg = msRS.getInt("ENABLE_FLG");
				if (enableFlg == 0) {
					// Delete the corelative row in the MixJuke DB if any.
					myStatement
							.execute("DELETE FROM MJ_ALBUMS WHERE album_id="
									+ msRS.getInt("PACKAGE_ID"));
					log = log + " is deleted";
				} else {
					// Insert or update the corelative row into MixJuke DB.
					pmyStatement.setInt(1, msRS.getInt("PACKAGE_ID"));
					pmyStatement.setString(2,
							msRS.getString("PACKAGE_TITLE"));
					pmyStatement.setString(3,
							msRS.getString("PACKAGE_TITLE_KANA"));
					pmyStatement.setString(4,
							msRS.getString("JACKET_IMG_FN"));
					pmyStatement.setTimestamp(5,
							msRS.getTimestamp("SALE_DATE"));
					pmyStatement.execute();
					log = log + " is inserted/updated";
				}
				LOGGER.info(log);
			}
			pmyStatement.close();
			pmyStatement = con2
					.prepareStatement("UPDATE MJ_SYNC SET last_sync_datetime=? WHERE id="
							+ Albums.MJ_ALBUMS_TABLE_ID);
			pmyStatement.setTimestamp(1, currentDate);
			pmyStatement.execute();
			pmyStatement.close();
			LOGGER.info("There are " + count + " rows updated in MICS DB.");
		}
	}

}