package jp.co.mti.mixjuke.batch.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import jp.co.mti.mixjuke.batch.adapter.DBConnector;
import jp.co.mti.mixjuke.batch.application.Configuration;
import jp.co.mti.mixjuke.batch.model.Albums;
import jp.co.mti.mixjuke.batch.model.Artists;
import jp.co.mti.mixjuke.batch.model.Performance;
import jp.co.mti.mixjuke.batch.model.Songs;
import jp.co.mti.mixjuke.batch.model.StrAuth;
import jp.co.mti.mixjuke.batch.model.StrDl;
import jp.co.mti.mixjuke.util.DateUtil;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * @User: naminh 
 * @Date: 10/15/13 
 * @Time: 1:16 PM
 */
public class DataTransfer {
	public static final String BEFORE_FIRST_TIME = "1909-09-09 00:00:00";
	public static final String DEFAULT_FIRST_TIME = "1909-09-09 09:09:09";
	public static final int SYNC_THREAD_NUM = 6;

	private static final Logger LOGGER = LogManager
			.getLogger(Configuration.SYNC_LOGGER_NAME);

	private boolean isFirstTimeOfAlbum = Boolean.TRUE; //  Albums.MJ_ALBUMS_TABLE_ID [1]
	private boolean isFirstTimeOfSong = Boolean.TRUE; //  Songs.MJ_SONGS_TABLE_ID [2]
	private boolean isFirstTimeOfArtist = Boolean.TRUE; //  Artists.MJ_ARTIST_TABLE_ID [3]
	private boolean isFirstTimeOfPerformance = Boolean.TRUE; //  Performance.MJ_PERFORMANCE_TABLE_ID [4]
	
	public static Integer syncSuccessCount;
	
	DBConnector src;
	DBConnector dst;

	public DataTransfer(DBConnector src, DBConnector dst) {
		this.src = src;
		this.dst = dst;
		syncSuccessCount = SYNC_THREAD_NUM;
	}

	public void transfer() {
		// The order of running is important.Please don't change it.
		Date date = new Date();
		LOGGER.info("Start to Synchronizing at " + DateUtil.toString(date));
		
		check4FirstTime();

		StrDl strdl = new StrDl(src.getConnection(), dst.getConnection(), Boolean.TRUE);
		strdl.run();
		
		StrAuth strAuth = new StrAuth(src.getConnection(), dst.getConnection(), Boolean.TRUE);
		strAuth.run();
		
		Albums albums = new Albums(src.getConnection(), dst.getConnection(), isFirstTimeOfAlbum);
		albums.run();
		
		Artists artists = new Artists(src.getConnection(), dst.getConnection(), isFirstTimeOfArtist);
		artists.run();
		
		Songs songs = new Songs(src.getConnection(), dst.getConnection(), isFirstTimeOfSong);
		songs.run();
		
		Performance performance = new Performance(src.getConnection(), dst.getConnection(), isFirstTimeOfPerformance);
		performance.run();

		LOGGER.info("Start at " + DateUtil.toString(date));
		LOGGER.info("Completed at " + DateUtil.toString(new Date()));
		LOGGER.info("MJ_STR_DL:" + strdl.isCompleted());
		LOGGER.info("MJ_STR_AUTH:" + strAuth.isCompleted());
		LOGGER.info("MJ_ALBUMS:" + albums.isCompleted());
		LOGGER.info("MJ_ARTISTS:" + artists.isCompleted());
		LOGGER.info("MJ_SONGS:" + songs.isCompleted());
		LOGGER.info("MJ_PERFORMANCE:" + performance.isCompleted());
		if (syncSuccessCount <= 0) {
			LOGGER.info("BATCH - MJDB Synchronise - Synchronise failed.");
		} else if (syncSuccessCount < SYNC_THREAD_NUM) {
			LOGGER.info("BATCH - MJDB Synchronise - Synchronise partially completed.");
		} else {
			LOGGER.info("BATCH - MJDB Synchronise - Synchronise completed.");
		}
	}
	
	public static void subSyncThread() {
		synchronized(syncSuccessCount) {
			syncSuccessCount--;
		}
	}

	private void check4FirstTime() {
		try {
			String sql = " SELECT * FROM MJ_SYNC";
			PreparedStatement pmyStatement = dst.getConnection()
					.prepareStatement(sql);
			ResultSet msRS = pmyStatement.executeQuery();
			while (msRS.next()) {
				int id = msRS.getInt("id");
				switch (id) {
					case 1: isFirstTimeOfAlbum = checkDate4FirstTime(msRS); break;
					case 2: isFirstTimeOfSong = checkDate4FirstTime(msRS); break;
					case 3: isFirstTimeOfArtist = checkDate4FirstTime(msRS); break;
					case 4: isFirstTimeOfPerformance = checkDate4FirstTime(msRS); break;
				}
			}
			pmyStatement.close();
		} catch (SQLException e) { }
	}
	
	private boolean checkDate4FirstTime(ResultSet msRS) {
		try {
			Date date = msRS.getDate("last_sync_datetime");
			return date == null || date.before(DateUtil.stringToDate(DEFAULT_FIRST_TIME, "yyyy-MM-DD HH:mm:ss"));
		} catch (Exception e) { 
			return Boolean.TRUE;
		}
	}

}
