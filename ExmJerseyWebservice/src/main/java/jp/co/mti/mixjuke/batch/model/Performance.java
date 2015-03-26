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
 * @Time: 10:26 AM 
 * @Edited 11/14/13 by nhphuoc
 */
public class Performance extends AbstractSync {
	private static final int MJ_PERFORMANCE_TABLE_ID = 4;

	public Performance(Connection con1, Connection con2, boolean isFirstTime) {
		super(con1, con2, isFirstTime);
	}

	public void run() {
		LOGGER.info("Starting synchronization MJ_PERFORMANCE table");
		super.run();
	}

	protected void sync() throws SQLException {
		// Query to the MixJuke database the get the last syncronization
		// timestamp for MJ_PERFORMANCE table
		Statement myStatement = this.con2.createStatement();
		ResultSet myRS = myStatement
				.executeQuery("SELECT last_sync_datetime FROM MJ_SYNC WHERE id="
						+ Performance.MJ_PERFORMANCE_TABLE_ID);
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
			LOGGER.info("V_SITESTG_METALINK_CONTENT_TBL");
			String msSQL = "SELECT REGION_ID,ARTIST_ID,PACKAGE_ID,ENABLE_FLG FROM dbo.V_SITESTG_METALINK_CONTENT_TBL WHERE LAST_UPDATE_DATETIME >=? AND REGION_ID IN (SELECT REGION_ID FROM dbo.V_SITESTG_METALINK_MIXJUKE_STREAMING_TBL)";
			PreparedStatement msStatement = con1.prepareStatement(msSQL);
			msStatement.setTimestamp(1, lastSync);
			ResultSet msRS = msStatement.executeQuery();
			// Delete all songs which is not in MJ_STR_AUTH
			// The synchronization for MJ_STR_AUTH is finished before.
			java.sql.Statement delStatement = con2.createStatement();
			delStatement
					.execute("DELETE FROM MJ_PERFORMANCE WHERE sid NOT IN (SELECT region_id FROM MJ_STR_AUTH)");
			delStatement.close();

			String mySQL = "INSERT INTO MJ_PERFORMANCE (sid,aid,album_id) VALUES (?,?,?)";
			PreparedStatement pmyStatement = con2.prepareStatement(mySQL);
			while (msRS.next()) {
				String msSubSQL = "SELECT S_ARTIST_ID FROM dbo.V_SITESTG_METALINK_SUGGEST_ARTIST_TBL WHERE ARTIST_ID=?";
				PreparedStatement msSubStatement = con1
						.prepareStatement(msSubSQL);
				msSubStatement.setInt(1, msRS.getInt("ARTIST_ID"));
				ResultSet msSubRS = msSubStatement.executeQuery();
				while (msSubRS.next()) {
					if (msRS.getInt("ENABLE_FLG") == 0) {
						// Delete from MJ DB if any
						myStatement
								.executeUpdate("DELETE FROM MJ_PERFORMANCE WHERE sid="
										+ msRS.getInt("REGION_ID")
										+ " AND aid="
										+ msSubRS.getInt("S_ARTIST_ID")
										+ " AND album_id="
										+ msRS.getInt("PACKAGE_ID"));
					} else {
						// Insert if can
						int sid = msRS.getInt("REGION_ID");
						int aid = msSubRS.getInt("S_ARTIST_ID");
						int albumid = msRS.getInt("PACKAGE_ID");
						try {
							pmyStatement.setInt(1, sid);
							pmyStatement.setInt(2, aid);
							pmyStatement.setInt(3, albumid);
							pmyStatement.execute();

						} catch (SQLException ex) {
							// ignore exception.
							LOGGER.info("Insert failed sid=" + sid
									+ " aid=" + aid + " album_id="
									+ albumid, ex);
						}
					}
				}// inner while
				msSubRS.close();
			}// outer white
			msRS.close();
			pmyStatement = con2
					.prepareStatement("UPDATE MJ_SYNC SET last_sync_datetime=? WHERE id="
							+ Performance.MJ_PERFORMANCE_TABLE_ID);
			pmyStatement.setTimestamp(1, currentDate);
			pmyStatement.execute();
			pmyStatement.close();
		}
	}
	
}
