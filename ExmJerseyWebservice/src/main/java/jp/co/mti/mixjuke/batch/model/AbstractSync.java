package jp.co.mti.mixjuke.batch.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jp.co.mti.mixjuke.batch.application.Configuration;
import jp.co.mti.mixjuke.batch.controller.DataTransfer;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * @author natu
 * @date 2014-02-17
 */
public abstract class AbstractSync {
	protected static final Logger LOGGER = LogManager.getLogger(Configuration.SYNC_LOGGER_NAME);

	protected Connection con1;
	protected Connection con2;
	private boolean isFirstTime;
	private boolean isCompleted;

	protected AbstractSync(Connection con1, Connection con2, boolean isFirstTime) {
		this.con1 = con1;
		this.con2 = con2;
		this.isFirstTime = isFirstTime;
		this.isCompleted = Boolean.TRUE;
	}

	protected void prepare4FirstTime(int id) {
		try {
			String sql = "update MJ_SYNC set last_sync_datetime=? where id = ?";
			PreparedStatement pmyStatement = con2.prepareStatement(sql);
			pmyStatement.setString(1, DataTransfer.BEFORE_FIRST_TIME);
			pmyStatement.setInt(2, id);
			pmyStatement.execute();
			pmyStatement.close();
		} catch (SQLException e) { 
			LOGGER.error("Error when get time for synchronization: " + e.getMessage());
		}
	}
	
	protected void run() {
		if (isFirstTime) {
			syncAtFirstTime();
		} else {
			syncEveryday();
		}
	}
	
	protected abstract void sync() throws SQLException;

	private void syncAtFirstTime() {
		try {
			this.con2.setAutoCommit(Boolean.TRUE);
			sync();
		} catch (SQLException e1) {
			LOGGER.error("Exception when synchronizing", e1);
			setCompleted(Boolean.FALSE);
		}
	}

	private void syncEveryday() {
		try {
			this.con2.setAutoCommit(Boolean.FALSE);
			sync();
			this.con2.commit();
		} catch (SQLException e1) {
			LOGGER.error("Exception when synchronizing", e1);
			setCompleted(Boolean.FALSE);
			try {
				this.con2.rollback();
			} catch (SQLException e2) {
				LOGGER.warn("Exception when rolling back", e2);
			}
		}
	}

	public boolean isCompleted() {
		return isCompleted;
	}

	public void setCompleted(boolean isCompleted) {
		if (!isCompleted) {
			DataTransfer.subSyncThread();
		}
		this.isCompleted = isCompleted;
	}	
	
}
