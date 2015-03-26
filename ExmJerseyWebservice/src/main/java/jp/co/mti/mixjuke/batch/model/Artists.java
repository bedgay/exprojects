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
 * User: naminh Date: 10/15/13 Time: 3:53 PM Edited 11/14/13 by nhphuoc
 */
public class Artists extends AbstractSync {
	private static final int MJ_ARTIST_TABLE_ID = 3;

	public Artists(Connection con1, Connection con2, boolean isFirstTime) {
		super(con1, con2, isFirstTime);
	}

	public void run() {
		LOGGER.info("Starting synchronization MJ_ARTISTS table");
		super.run();
	}

	protected void sync() throws SQLException {
		// Query to the MixJuke database the get the last syncronization
		// timestamp for MJ_ALBUMS table
		Statement myStatement = this.con2.createStatement();
		ResultSet myRS = myStatement
				.executeQuery("SELECT last_sync_datetime FROM MJ_SYNC WHERE id="
						+ Artists.MJ_ARTIST_TABLE_ID);
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
			// V_SITESTG_METALINK_SUGGEST_ARTIST_TBL isn't updated.
			LOGGER.info("Lookup V_SITESTG_METALINK_ARTIST_TBL");
			String msSQL = "SELECT ARTIST_ID FROM dbo.V_SITESTG_METALINK_ARTIST_TBL WHERE LAST_UPDATE_DATETIME >=?";
			PreparedStatement msStatement = con1.prepareStatement(msSQL);
			msStatement.setTimestamp(1, currentDate);
			ResultSet msRS = msStatement.executeQuery();
			String mySQL = "INSERT INTO MJ_ARTISTS(id,name,name_kana, name_alpha,image_url,freeword) VALUES (?,?,?,?,?,?) ON DUPLICATE KEY UPDATE name=VALUES(name),name_kana=VALUES(name_kana),name_alpha=VALUES(name_alpha),image_url=VALUES(image_url),freeword=VALUES(freeword)";
			PreparedStatement pmyStatement = con2.prepareStatement(mySQL);
			// int count = 0;
			while (msRS.next()) {
				// count++;
				int artistId = msRS.getInt("ARTIST_ID");
				LOGGER.info("ARTIST_ID =" + artistId);
				String msSubSQL = "SELECT S_ARTIST_ID, ARTIST_NAME, ARTIST_NAME_ALPHA, ARTIST_NAME_KANA,FREEWORD=STUFF((SELECT ' '+a.ARTIST_FREEWORD FROM dbo.V_SITESTG_METALINK_ARTIST_TBL AS a WHERE (a.ARTIST_ID=?) AND (a.ENABLE_FLG=1) FOR XML PATH('')) , 1 , 1 , ''), ARTIST_IMG_FN = (SELECT TOP (1) a.ARTIST_IMG_FN FROM dbo.V_SITESTG_METALINK_ARTIST_TBL AS a WHERE (a.ARTIST_ID=?) AND (a.ENABLE_FLG=1) ORDER BY a.LAST_UPDATE_DATETIME DESC) FROM dbo.V_SITESTG_METALINK_SUGGEST_ARTIST_TBL AS s WHERE s.ARTIST_ID=?";
				PreparedStatement msSubStatement = con1
						.prepareStatement(msSubSQL);
				msSubStatement.setInt(1, artistId);
				msSubStatement.setInt(2, artistId);
				msSubStatement.setInt(3, artistId);
				ResultSet msSubRS = msSubStatement.executeQuery();
				while (msSubRS.next()) {
					pmyStatement.setInt(1, msSubRS.getInt("S_ARTIST_ID"));
					pmyStatement
							.setString(2, msRS.getString("ARTIST_NAME"));
					pmyStatement.setString(3,
							msRS.getString("ARTIST_NAME_KANA"));
					pmyStatement.setString(4,
							msRS.getString("ARTIST_NAME_ALPHA"));
					pmyStatement.setString(5, msRS.getString("FREEWORD"));
					pmyStatement.setString(6,
							msRS.getString("ARTIST_IMG_FN"));
					pmyStatement.execute();
					LOGGER.info("Insert/update S_ARTIST_ID="
							+ msSubRS.getInt("S_ARTIST_ID"));

				}// while
			}// while
			msRS.close();
			pmyStatement = con2
					.prepareStatement("UPDATE MJ_SYNC SET last_sync_datetime=? WHERE id="
							+ Artists.MJ_ARTIST_TABLE_ID);
			pmyStatement.setTimestamp(1, currentDate);
			pmyStatement.execute();
			pmyStatement.close();
		}
	}
	
}
